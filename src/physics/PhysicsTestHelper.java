
package physics;

import com.jme3.app.Application;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Dome;
import com.jme3.scene.shape.PQTorus;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.texture.Texture;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;

/**
 * Update author: Gunnar Gorder
 * Updated: 11/08/2015
 * CMSC 325, Project 1, UMUC Fall 2015
 * File: PhysicsTestHelper.java
 * Description:  The PhysicsTestHelper class creates the specified
 * physics to be added to the scene.
 */
public class PhysicsTestHelper {

    /**
     * creates a simple physics test world with a floor, an obstacle and some test boxes
     * @param rootNode
     * @param assetManager
     * @param space
     */
    
    //Updated variable targest list provides targets which count as a "hit"
    public static List<Geometry> targets = new ArrayList<Geometry>();
    
    public List<Geometry> getTargets(){
        return targets;
    }
    //createPhysicsTestWorld creates and adds moveable and imoveable objects
    public static void createPhysicsTestWorld(Node rootNode, AssetManager assetManager, PhysicsSpace space) {
        AmbientLight light = new AmbientLight();
        light.setColor(ColorRGBA.LightGray);
        rootNode.addLight(light);
        
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        material.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/splat/grass.jpg"));
        
        Box floorBox = new Box(140, 0.25f, 140);
        Geometry floorGeometry = new Geometry("Floor", floorBox);
        floorGeometry.setMaterial(material);
        floorGeometry.setLocalTranslation(0, 0, 0);
        floorGeometry.addControl(new RigidBodyControl(0));
        floorGeometry.getControl(RigidBodyControl.class).setRestitution(0f);
        rootNode.attachChild(floorGeometry);
        space.add(floorGeometry);
        
        //Create planes to bound field
        
        Material southWallMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        southWallMaterial.setColor("Color", new ColorRGBA(1,0,0,0f));
        southWallMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        Box southWall = new Box(280, 140, 0);
        Geometry southWallGeometry = new Geometry("SouthWall", southWall);
        southWallGeometry.setMaterial(southWallMaterial);
        southWallGeometry.setLocalTranslation(-140, 140, 140);
        southWallGeometry.addControl(new RigidBodyControl(0));
        southWallGeometry.setQueueBucket(Bucket.Transparent);
        southWallGeometry.getControl(RigidBodyControl.class).setRestitution(1f);
        southWallGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(southWall), 0));
        rootNode.attachChild(southWallGeometry);
        space.add(southWallGeometry);
        
        Material northWallMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        northWallMaterial.setColor("Color", new ColorRGBA(0,1,0,0f));
        northWallMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        Box northWall = new Box(280, 140, 0);
        Geometry northWallGeometry = new Geometry("NorthWall", northWall);
        northWallGeometry.setMaterial(northWallMaterial);
        northWallGeometry.setLocalTranslation(-140, 140, -140);
        northWallGeometry.addControl(new RigidBodyControl(0));
        northWallGeometry.setQueueBucket(Bucket.Transparent);
        northWallGeometry.getControl(RigidBodyControl.class).setRestitution(1f);
        northWallGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(northWall), 0));
        rootNode.attachChild(northWallGeometry);
        space.add(northWallGeometry);
        
        Material westWallMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        westWallMaterial.setColor("Color", new ColorRGBA(0,0,1,0f));
        westWallMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        Box westWall = new Box(0, 140, 280);
        Geometry westWallGeometry = new Geometry("WestWall", westWall);
        westWallGeometry.setMaterial(westWallMaterial);
        westWallGeometry.setLocalTranslation(140, 140, -140);
        westWallGeometry.addControl(new RigidBodyControl(0));
        westWallGeometry.setQueueBucket(Bucket.Transparent);
        westWallGeometry.getControl(RigidBodyControl.class).setRestitution(1f);
        westWallGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(westWall), 0));
        rootNode.attachChild(westWallGeometry);
        space.add(westWallGeometry);
        
        Material eastWallMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        eastWallMaterial.setColor("Color", new ColorRGBA(0,1,1,0f));
        eastWallMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        Box eastWall = new Box(0, 140, 280);
        Geometry eastWallGeometry = new Geometry("EastWall", eastWall);
        eastWallGeometry.setMaterial(eastWallMaterial);
        eastWallGeometry.setLocalTranslation(-140, 140, -140);
        eastWallGeometry.addControl(new RigidBodyControl(0));
        eastWallGeometry.setQueueBucket(Bucket.Transparent);
        eastWallGeometry.getControl(RigidBodyControl.class).setRestitution(1f);
        eastWallGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(eastWall), 0));
        rootNode.attachChild(eastWallGeometry);
        space.add(eastWallGeometry);
        
        Material lidMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        lidMaterial.setColor("Color", new ColorRGBA(1,0,1,0f));
        lidMaterial.getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
        Box lidWall = new Box(280, 0, 280);
        Geometry lidGeometry = new Geometry("Lid", lidWall);
        lidGeometry.setMaterial(lidMaterial);
        lidGeometry.setLocalTranslation(-140, 140, -140);
        lidGeometry.addControl(new RigidBodyControl(0));
        lidGeometry.setQueueBucket(Bucket.Transparent);
        lidGeometry.getControl(RigidBodyControl.class).setRestitution(1f);
        lidGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(lidWall), 0));
        rootNode.attachChild(lidGeometry);
        space.add(lidGeometry);
        
        //Creates bouncing ball targets
        for (int i = 0; i < 3; i++) {              
                Material ballMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                ballMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Sky/St Peters/StPeters.jpg"));
                Sphere ball = new Sphere(20, 20, 5);
                Geometry ballGeometry = new Geometry("Ball", ball);
                ballGeometry.setMaterial(ballMaterial);
                ballGeometry.setLocalTranslation((i*4), 5, -3);
                //RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh
                ballGeometry.addControl(new RigidBodyControl(70));
                ballGeometry.getControl(RigidBodyControl.class).setRestitution(1f);
                Random rand = new Random();
                float x = rand.nextFloat()*90 + 50;
                float y = 0;//rand.nextFloat()*20 + 10;
                float z = rand.nextFloat()*90 + 50;
                ballGeometry.getControl(RigidBodyControl.class).setLinearVelocity(new Vector3f(x,y,z));
                rootNode.attachChild(ballGeometry);
                space.add(ballGeometry);                
                mygame.Main.targets.add(ballGeometry);
            
        }

        /*
        //immovable sphere with mesh collision shape
        Material sphereMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        sphereMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/splat/mountains512.png"));
        Sphere sphere = new Sphere(20, 20, 3);
        Geometry sphereGeometry = new Geometry("Sphere", sphere);
        sphereGeometry.setMaterial(sphereMaterial);
        sphereGeometry.setLocalTranslation(4, 10, 40);
        sphereGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(sphere), 0));
        rootNode.attachChild(sphereGeometry);
        space.add(sphereGeometry);
        
        //Imomovable cylinder with mesh collision shape
        Material cylinderMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        cylinderMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/Rock2/rock.jpg"));
        Cylinder cylinder = new Cylinder(20,20,2,5, true);
        Geometry cylinderGeometry = new Geometry("Cylinder", cylinder);
        cylinderGeometry.setMaterial(cylinderMaterial);
        cylinderGeometry.setLocalTranslation(-8, 2, -3);
        cylinderGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(cylinder), 0));
        rootNode.attachChild(cylinderGeometry);
        space.add(cylinderGeometry);
        
        //Immoveable dome with mesh collision shape
        Material domeMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        domeMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Sky/Bright/BrightSky.dds"));
        Dome dome = new Dome(new Vector3f(),20, 20, 5, false);
        Geometry domeGeometry = new Geometry("Dome", dome);
        domeGeometry.setMaterial(domeMaterial);
        domeGeometry.setLocalTranslation(15, 0, 20);
        domeGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(dome), 0));
        rootNode.attachChild(domeGeometry);
        space.add(domeGeometry);
        
        //Immoveable PQTorus with mesh collision shape
        Material pqTorusMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        pqTorusMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/splat/road.jpg"));
        PQTorus pqTorus = new PQTorus(5,3, 2f, 1f, 32, 32);
        Geometry pqTorusGeometry = new Geometry("pqTorus", pqTorus);
        pqTorusGeometry.setMaterial(pqTorusMaterial);
        pqTorusGeometry.setLocalTranslation(-10, 2, 30);
        pqTorusGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(pqTorus), 0));
        rootNode.attachChild(pqTorusGeometry);
        space.add(pqTorusGeometry);
        
        //Immoveable box/wall with mesh collision shape
        Material wallMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        wallMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        Box wall = new Box(1, 8, 8);
        Geometry wallGeometry = new Geometry("Wall", wall);
        wallGeometry.setMaterial(wallMaterial);
        wallGeometry.setLocalTranslation(-50, 8, 20);
        wallGeometry.addControl(new RigidBodyControl(new MeshCollisionShape(wall), 0));
        rootNode.attachChild(wallGeometry);
        space.add(wallGeometry);
        */
        
    }

    /**
     * creates the necessary inputlistener and action to shoot balls from the camera
     * @param app
     * @param rootNode
     * @param space
     */
    public static void createBallShooter(final Application app, final Node rootNode, final PhysicsSpace space) {
        ActionListener actionListener = new ActionListener() {

            public void onAction(String name, boolean keyPressed, float tpf) {
                Sphere bullet = new Sphere(20, 20, 1f, true, false);
                bullet.setTextureMode(TextureMode.Projected);
                Material mat2 = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
                TextureKey key2 = new TextureKey("Textures/Sky/Bright/FullskiesBlueClear03.dds");
                key2.setGenerateMips(true);
                Texture tex2 = app.getAssetManager().loadTexture(key2);
                mat2.setTexture("ColorMap", tex2);
                if (name.equals("shoot") && !keyPressed) {
                    Geometry bulletg = new Geometry("bullet", bullet);
                    bulletg.setMaterial(mat2);
                    bulletg.setShadowMode(ShadowMode.CastAndReceive);
                    bulletg.setLocalTranslation(app.getCamera().getLocation());
                    RigidBodyControl bulletControl = new RigidBodyControl(10);
                    bulletg.addControl(bulletControl);
                    bulletControl.setLinearVelocity(app.getCamera().getDirection().mult(100));
                    bulletControl.setRestitution(1f);
                    bulletg.addControl(bulletControl);
                    rootNode.attachChild(bulletg);
                    space.add(bulletControl);
                }
            }
        };
        app.getInputManager().addMapping("shoot", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        app.getInputManager().addListener(actionListener, "shoot");
    }
}
