package Host.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener{

    public boolean is_W_Pressed;
    public boolean is_S_Pressed;

    public boolean is_Up_Pressed;
    public boolean is_Down_Pressed;

    public boolean upPressed;
    public boolean downPressed;

    public Key(){

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 87){
            is_W_Pressed = true;
            upPressed = true;
        }
        if(code == 83){
            is_S_Pressed = true;
            downPressed = true;
        }
        if(code == 38){
            is_Up_Pressed = true;
            upPressed = true;
        }
        if(code == 40){
            is_Down_Pressed = true;
            downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 87){
            is_W_Pressed = false;
            upPressed = false;
        }
        if(code == 83){
            is_S_Pressed = false;
            downPressed = false;
        }
        if(code == 38){
            is_Up_Pressed = false;
            upPressed = false;
        }
        if(code == 40){
            is_Down_Pressed = false;
            downPressed = false;
        }
    }
    
}
