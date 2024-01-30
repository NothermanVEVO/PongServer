package Host.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener{

    public boolean is_W_Pressed;
    public boolean is_S_Pressed;

    public boolean is_Up_Pressed;
    public boolean is_Down_Pressed;

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
        }
        if(code == 83){
            is_S_Pressed = true;
        }
        if(code == 38){
            is_Up_Pressed = true;
        }
        if(code == 40){
            is_Down_Pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 87){
            is_W_Pressed = false;
        }
        if(code == 83){
            is_S_Pressed = false;
        }
        if(code == 38){
            is_Up_Pressed = false;
        }
        if(code == 40){
            is_Down_Pressed = false;
        }
    }
    
}
