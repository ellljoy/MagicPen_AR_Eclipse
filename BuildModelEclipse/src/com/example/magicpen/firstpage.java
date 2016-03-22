package com.example.magicpen;

import com.example.magicpen.R;

import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;
import min3d.vos.Number3d;



public class firstpage extends RendererActivity implements OnClickListener, SensorEventListener {
	
	private final float FILTERING_FACTOR = .3f;
    private ImageView btn_back;
    private ImageView btn_instruction;
    private Button btn1,btn2;
	private Object3dContainer objModel1;
	private Object3dContainer objModel2;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private Number3d mAccVals;

    @Override public void onCreate(Bundle savedInstanceState) {

    	/** Called when the activity is first created. */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccVals = new Number3d();
        
        //btn1 to mode#1
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        
        //btn2 to model#2
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        //Click btn Back to go back
        btn_back = (ImageView) findViewById(R.id.btnback);
        btn_back.setOnClickListener(new View.OnClickListener() {

        	@Override
            public void onClick(View v) {
                Intent intent = new Intent(firstpage.this, MainActivity.class);
                startActivity(intent);
                firstpage.this.finish();
            }
        });

        //Click btn Instruction for guides
        btn_instruction = (ImageView) findViewById(R.id.btninstruction);
        btn_instruction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(firstpage.this, Instruction.class);
                startActivity(intent);
                firstpage.this.finish();
            }
        });
      FrameLayout modelSpace = (FrameLayout) findViewById(R.id.frame3d);
      modelSpace.addView(_glSurfaceView);
  
    }

    //Load models basing on btn click
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn1:
    		scene.addChild(objModel1);
    		objModel1.position().x = -0.2f; 
    		objModel1.position().y = -0.2f;
    		objModel1.position().z = 0.5f;
    		objModel1.scale().x = objModel1.scale().y = objModel1.scale().z = .5f;

        	break;
        	
        case R.id.btn2:
    		scene.addChild(objModel2);
    		objModel2.position().x = 0.2f; 
    		objModel2.position().y = 0.2f;
    		objModel2.scale().x = objModel2.scale().y = objModel2.scale().z = .2f;

        	break;

        }
    
    }  
    
    @Override
	public void initScene() {
		
		scene.lights().add(new Light());

		// Model#1
		IParser parser1 = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.example.magicpen:raw/camaro_obj", true);
		parser1.parse();
		objModel1 = parser1.getParsedObject();
		
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
//		scene.camera().target = objModel1.position();
		
		// Model#2
		IParser parser2 = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.example.magicpen:raw/camaro_obj", true);
		parser2.parse();

		objModel2 = parser2.getParsedObject();

//		scene.camera().target = objModel2.position();
			
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
		
		// low-pass filter to make the movement more stable
		mAccVals.x = (float) (-event.values[1] * FILTERING_FACTOR + mAccVals.x * (1.0 - FILTERING_FACTOR));
		mAccVals.y = (float) (event.values[0] * FILTERING_FACTOR + mAccVals.y * (1.0 - FILTERING_FACTOR));
		
		scene.camera().position.x = mAccVals.x * .2f;
        scene.camera().position.y = mAccVals.y * .2f;
        
        scene.camera().target.x = -scene.camera().position.x;
        scene.camera().target.y = -scene.camera().position.y;
	}
    
	@Override
	public void updateScene() {
//		objModel1.rotation().x++;
//		objModel2.rotation().y++;
		objModel1.rotation().z= -180;
		objModel1.rotation().x= -90;
	}
	
	
}
