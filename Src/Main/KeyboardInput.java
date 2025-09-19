package Src.Main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, rPressed;


    //Required method for implementing KeyListener, tho not needed
    @Override
    public void keyTyped(KeyEvent e) {}


    // This method checks for key inputs, in this case up, down, left, & right
    @Override
    public void keyPressed(KeyEvent e) {

    int keyPressed = e.getKeyCode();

    if(keyPressed == KeyEvent.VK_UP){
        upPressed = true;
    }
    if(keyPressed == KeyEvent.VK_DOWN){
        downPressed = true;
    }
    if(keyPressed == KeyEvent.VK_LEFT){
        leftPressed = true;
    }
    if(keyPressed == KeyEvent.VK_RIGHT){
        rightPressed = true;
    }
    if(keyPressed == KeyEvent.VK_R){
        rPressed = true;
    }
    if(keyPressed == KeyEvent.VK_W){
        upPressed = true;
    }
    if(keyPressed == KeyEvent.VK_S){
        downPressed = true;
    }
    if(keyPressed == KeyEvent.VK_A){
        leftPressed = true;
    }
    if(keyPressed == KeyEvent.VK_D){
        rightPressed = true;
    }
    if(keyPressed == KeyEvent.VK_R){
        rPressed = true;
    }
    }

    //This method sets booleans to false when key is released
    @Override
    public void keyReleased(KeyEvent e) {

        int keyPressed = e.getKeyCode();

        if(keyPressed == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(keyPressed == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(keyPressed == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(keyPressed == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(keyPressed == KeyEvent.VK_W){
            upPressed = false;
        }
        if(keyPressed == KeyEvent.VK_S){
            downPressed = false;
        }
        if(keyPressed == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(keyPressed == KeyEvent.VK_D){
            rightPressed = false;
        }             
    }
    
}