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
 * Author: Gunnar Gorder
 * Updated: 11/29/2015
 * CMSC 325, Project 2, UMUC Fall 2015
 * File: CollisionDetector.java
 * Description:  The CollisionDetector class adds a collision detection object
 * to the physics envinronment.  This class is called by the PhysicsTestHelper 
 * class.  Additionally, this class tracks the ball collisions and updates the 
 * ballPosOutput string variable in the Main class.  At the same time, it outputs
 * collision data to the console for user review.
 */
public class CollisionDetector extends GhostControl implements PhysicsCollisionListener {
    
    int ball0count = 0, ball1count = 0, ball2count = 0;
    
    
    public CollisionDetector(){
        mygame.Main.bulletAppState.getPhysicsSpace().addCollisionListener(this);
    }
    
     public void collision(PhysicsCollisionEvent event) {
         Spatial nodeA = event.getNodeA();
         Spatial nodeB = event.getNodeB();
         
         
         for(Spatial target: PhysicsTestHelper.getTargets()){
             if((nodeA == target) || (nodeB == target)){
                 for(Spatial target2: PhysicsTestHelper.getTargets()){
                     if(target != target2){
                         if((nodeA == target2) || (nodeB == target2)){
                             String updateString = target.getName() + " hit " + target2.getName()+" \r\n";
                        
                            if((target.getName().equals("Ball0"))||(target2.getName().equals("Ball0"))){
                                ball0count++;
                                updateString = updateString + "Ball0 collisions = " + ball0count+" \r\n";
                            }
                            if((target.getName().equals("Ball1"))||(target2.getName().equals("Ball1"))){
                                ball1count++;
                                updateString = updateString + "Ball1 collisions = " + ball1count+" \r\n";
                            }
                            if((target.getName().equals("Ball2"))||(target2.getName().equals("Ball2"))){
                                ball2count++;
                                updateString = updateString + "Ball2 collisions = " + ball2count+" \r\n";
                            }

                            updateString = updateString                      
                                    +target.getName() +" local Translation: " + target.getLocalTranslation().toString()+" \r\n"
                                    +target.getName() +" direction: " + target.getLocalRotation().getRotationColumn(2).toString()+" \r\n"
                                    +target2.getName() +" local Translation: " + target2.getLocalTranslation().toString()+" \r\n"
                                    +target2.getName() +" direction: " + target2.getLocalRotation().getRotationColumn(2).toString()+" \r\n";
                            mygame.Main.ballPosOutput = mygame.Main.ballPosOutput + updateString;
                            System.out.println(updateString);
                            return;
                         }
                     }                
                 }
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