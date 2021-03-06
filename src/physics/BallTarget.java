/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import java.util.Random;

/**
 *
 * @author Gunnar
 */
/**
 * Author: Gunnar Gorder
 * Updated: 11/29/2015
 * CMSC 325, Project 2, UMUC Fall 2015
 * File: BallTarget.java
 * Description:  The BallTarget class creates ball object to act as targets
 * for the AI and adds them to the physics environment as well as the target
 * list field of the PhysicsTestHelper class.
 */
public class BallTarget {

    public BallTarget(Node rootNode, AssetManager assetManager, PhysicsSpace space, int i){
        Material ballMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                ballMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Sky/St Peters/StPeters.jpg"));
                Sphere ball = new Sphere(20, 20, 5);
                String ballName = "Ball" + i;
                Geometry ballGeometry = new Geometry(ballName, ball);
                ballGeometry.setName(ballName);
                ballGeometry.setMaterial(ballMaterial);
                ballGeometry.setLocalTranslation((i*4), 5, -3);
                //RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh
                ballGeometry.addControl(new RigidBodyControl(70));
                ballGeometry.getControl(RigidBodyControl.class).setRestitution(1f);
                ballGeometry.getControl(RigidBodyControl.class).setFriction(0f);
                Random rand = new Random();
                float x = rand.nextFloat()*5;
                float y = rand.nextFloat()+1;
                float z = rand.nextFloat()*5;
                ballGeometry.getControl(RigidBodyControl.class).setLinearVelocity(new Vector3f(x,y,z));
                rootNode.attachChild(ballGeometry);
                space.add(ballGeometry);                
                PhysicsTestHelper.targets.add(ballGeometry);
    }
}
