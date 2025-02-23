package logic;

import javafx.scene.input.KeyCode;

public class KeyboardController {
	static boolean upPressed = false;
	static boolean downPressed = false;
	static boolean leftPressed = false;
	static boolean rightPressed = false;
	static boolean Pressed = true;
	private static KeyboardController keyboard;

	public KeyboardController() {

	}

	public void handleKeyPress(KeyCode code, boolean pressed) {
		Pressed = true;
		switch (code) {
		case W -> upPressed = pressed;
		case S -> downPressed = pressed;
		case A -> leftPressed = pressed;
		case D -> rightPressed = pressed;
		default -> Pressed = false;
		}
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isPressed() {
		return Pressed;
	}

	public static KeyboardController getInstance() {
		return keyboard;
	}
}
