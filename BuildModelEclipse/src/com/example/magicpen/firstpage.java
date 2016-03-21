package com.example.magicpen;

import com.example.magicpen.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;

public class firstpage extends RendererActivity {

    private ImageView btn_back;
    private ImageView btn_instruction;
    private Button btn1;
    private Button btn2;
	private Object3dContainer objModel;

    @Override public void onCreate(Bundle savedInstanceState) {

    	/** Called when the activity is first created. */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        
        //TBD Click btn1 to build model#
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
            
        });
        
        //TBD Click btn2 to build model#2
        btn2 = (Button) findViewById(R.id.btn2);
 
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
      FrameLayout model = (FrameLayout) findViewById(R.id.frame3d);
      model.addView(_glSurfaceView);
    }

    
    @Override
	public void initScene() {
		
		scene.lights().add(new Light());

		// Model#1
		IParser parser = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.example.magicpen:raw/camaro_obj", true);
		parser.parse();

		objModel = parser.getParsedObject();
		objModel.position().x = 0f; 
		objModel.position().y = 0f;
		objModel.scale().x = objModel.scale().y = objModel.scale().z = .5f;
		scene.camera().target = objModel.position();
		scene.addChild(objModel);
			
	}

	@Override
	public void updateScene() {
		objModel.rotation().x++;
	}
}
