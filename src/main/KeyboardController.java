package main;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyboardController {
   static boolean upPressed = false;
    static boolean downPressed = false;
    static boolean leftPressed = false;
    static boolean rightPressed = false;
    static boolean pPressed = false;
    private static KeyboardController keyboard;

    public KeyboardController() {
 
    }


    public  void handleKeyPress(KeyCode code, boolean pressed) {
 
        switch (code) {
            case W -> upPressed = pressed;
            case S -> downPressed = pressed;
            case A -> leftPressed = pressed;
            case D -> rightPressed = pressed;
            case P -> pPressed = pressed;
        }
    }

    public static boolean isUpPressed() { return upPressed; }
    public static boolean isDownPressed() { return downPressed; }
    public static boolean isLeftPressed() { return leftPressed; }
    public static boolean isRightPressed() { return rightPressed; }
    public static boolean isPPressed() { return pPressed; }
    
 
    public static KeyboardController getInstance () {
    	return keyboard;
    }
}

