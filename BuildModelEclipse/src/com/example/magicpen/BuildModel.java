//package com.example.magicpen;
//
//import min3d.core.Object3dContainer;
//import min3d.core.RendererActivity;
//import min3d.parser.IParser;
//import min3d.parser.Parser;
//import min3d.vos.Light;
//
///**
// * How to load a model from a .obj file
// * 
// * @author dennis.ippel, modified by Jin Yao
// * 
// */
//public class BuildModel extends RendererActivity {
//	private Object3dContainer objModel;
//
//	@Override
//	public void initScene() {
//		
//		scene.lights().add(new Light());
//
//		// Model#1
//		IParser parser = Parser.createParser(Parser.Type.OBJ,
//				getResources(), "com.example.magicpen:raw/camaro_obj", true);
//		parser.parse();
//
//		objModel = parser.getParsedObject();
//		objModel.position().x = 0f; 
//		objModel.position().y = 0f;
//		objModel.position().z = -5f;
//		objModel.scale().x = objModel.scale().y = objModel.scale().z = .3f;
//		scene.addChild(objModel);
//			
//	}
//
//	@Override
//	public void updateScene() {
//		objModel.rotation().x++;
//		objModel.rotation().z++;
//	}
//}
//
