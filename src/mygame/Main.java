package mygame;

import appstate.InputAppState;
import physics.PhysicsTestHelper;
import characters.MyGameCharacterControl;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import java.util.List;

/**
 * Update author: Gunnar Gorder
 * Updated: 11/08/2015
 * File: Main.java
 * CMSC 325, Project 1, UMUC Fall 2015
 * Description:  The main class is the driving class for the application
 * it sets up the environment and adds the characters and physics attributes
 * to the environment calling the other application classes as needed
 */
public class Main extends SimpleApplication {
    
    protected BulletAppState bulletAppState;
    private Vector3f normalGravity = new Vector3f(0, -9.81f, 0);
    BitmapText hitText;
    public static Material lineMat;
    public static List<Geometry> targets = new ArrayList<Geometry>();
    //added appstate variable to facilitate calling the hit counter
    private InputAppState appStateThis;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    //This method initializes the application
    //Updated to include custom models and additional characters.
    @Override
    public void simpleInitApp() {
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        getFlyByCamera().setMoveSpeed(45f);
        lineMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Node scene = setupWorld();
        
        setupCharacter(scene);
    }
    
    private Node setupWorld(){

        //Load the scene created in Homework 2
        Node scene = (Node) assetManager.loadModel("Scenes/Week2Scene.j3o");
        rootNode.attachChild(scene);
        
        
        
        PhysicsTestHelper.createPhysicsTestWorld(rootNode, assetManager, 
                bulletAppState.getPhysicsSpace());
        
        //Add the Player to the world and use the customer character and input control classes
        
        MyGameCharacterControl charControl = new MyGameCharacterControl(1f,6f,8f);
        charControl.setCamera(cam);
        charControl.setGravity(normalGravity);
        bulletAppState.getPhysicsSpace().add(charControl);
        
        InputAppState appState = new InputAppState();
        this.appStateThis = appState;
        appState.setCharacter(charControl);
        appState.setTargets(PhysicsTestHelper.targets);
        stateManager.attach(appState);
        
     
                
        //Add the "bullets" to the scene to allow the player to shoot the balls
        PhysicsTestHelper.createBallShooter(this,rootNode,bulletAppState.getPhysicsSpace());
        
        //Add a custom font and text to the scene
        BitmapFont myFont = assetManager.loadFont("Interface/Fonts/BasicFont.fnt");

        //Create small x in center of screen for cross-hairs
        BitmapText hudText = new BitmapText(myFont, true);
        hudText.setText("+");
        hudText.setColor(ColorRGBA.White);
        hudText.setSize(guiFont.getCharSet().getRenderedSize());
        
        //Set the text in the middle of the screen
        //Updated centering math for accuracy
        hudText.setLocalTranslation((settings.getWidth()  - hudText.getLineWidth())/2 , settings.getHeight() / 2 + hudText.getLineHeight(), 0f); //Positions text to middle of screen
        guiNode.attachChild(hudText);
        
        //Add title text to the upper left corner of screen
        BitmapText titleText = new BitmapText(myFont, true);
        titleText.setText("Gunnar Gorder - CMSC 325 - Fall 2015");
        titleText.setColor(ColorRGBA.Orange);
        titleText.setSize(guiFont.getCharSet().getRenderedSize());
        
        titleText.setLocalTranslation(1f , settings.getHeight() - hudText.getLineHeight(), 0f);
        guiNode.attachChild(titleText);
        
        //Add hit counter to upper left, below title
        hitText = new BitmapText(myFont, true);
        hitText.setText("Hits = " + appStateThis.hitCount);
        hitText.setColor(ColorRGBA.Orange);
        hitText.setSize(guiFont.getCharSet().getRenderedSize());
        
        hitText.setLocalTranslation(1f , settings.getHeight() - hudText.getLineHeight() - hitText.getLineHeight(), 0f);
        guiNode.attachChild(hitText);
        
        
        return scene;
    }

    private void setupCharacter(Node scene){
        
    }
     
   
    
    
    
    //Update hit counter as required
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
      hitText.setText("Hits = " + appStateThis.hitCount);
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
