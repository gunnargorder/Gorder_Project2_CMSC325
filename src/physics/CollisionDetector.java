/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.control.GhostControl;
import com.jme3.scene.Spatial;

/**
 *
 * @author Gunnar
 */
public class CollisionDetector extends GhostControl implements PhysicsCollisionListener {
    
    int ball0count, ball1count, ball2count;
    
    
    public CollisionDetector(){
        mygame.Main.bulletAppState.getPhysicsSpace().addCollisionListener(this);
    }
    
     public void collision(PhysicsCollisionEvent event) {
         Spatial nodeA = event.getNodeA();
         Spatial nodeB = event.getNodeB();
         
         
         for(Spatial target: PhysicsTestHelper.getTargets()){
             if((nodeA == target) || (nodeB == target)){
                 for(Spatial wall: PhysicsTestHelper.getWalls()){
                    if((nodeA == wall) || (nodeB == wall)){
                        String updateString = target.getName() + " hit " + wall.getName()+" \r\n"
                                +target.getName()+" collisions = ";
                        
                        if(target.getName().equals("Ball0")){
                            ball0count++;
                            updateString = updateString + ball0count;
                        }else if(target.getName().equals("Ball1")){
                            ball1count++;
                            updateString = updateString + ball1count;
                        }else if(target.getName().equals("Ball2")){
                            ball2count++;
                            updateString = updateString + ball2count;
                        }
                        
                        updateString = updateString  +" \r\n"                              
                                +"Local Translation: " + target.getLocalTranslation().toString()+" \r\n"
                                +"Direction: " + target.getLocalRotation().getRotationColumn(2).toString()+" \r\n";
                        mygame.Main.ballPosOutput = mygame.Main.ballPosOutput + updateString;
                        System.out.println(updateString);
                    }  
                 }
             }
         }

    }
}