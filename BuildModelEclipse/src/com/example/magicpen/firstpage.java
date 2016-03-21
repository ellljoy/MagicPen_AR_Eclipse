package com.example.magicpen;

import com.example.magicpen.R;

import android.content.Intent;
import android.os.Bundle;
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

public class firstpage extends RendererActivity implements OnClickListener {

    private ImageView btn_back;
    private ImageView btn_instruction;
    private Button btn1,btn2;
	private Object3dContainer objModel1;
	private Object3dContainer objModel2;

    @Override public void onCreate(Bundle savedInstanceState) {

    	/** Called when the activity is first created. */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        
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
                Intent intent = new Intent(firstpage.this, SwiptPages.class);
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
    		objModel1.scale().x = objModel1.scale().y = objModel1.scale().z = .2f;

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

//		scene.camera().target = objModel1.position();
		
		// Model#2
		IParser parser2 = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.example.magicpen:raw/camaro_obj", true);
		parser2.parse();

		objModel2 = parser2.getParsedObject();

//		scene.camera().target = objModel2.position();
			
	}

	@Override
	public void updateScene() {
		objModel1.rotation().x++;
		objModel2.rotation().y++;
	}
}
