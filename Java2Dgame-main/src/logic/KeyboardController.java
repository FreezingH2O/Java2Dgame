package logic;

import javafx.scene.input.KeyCode;

public class KeyboardController {
	static boolean upPressed = false;
	static boolean downPressed = false;
	static boolean leftPressed = false;
	static boolean rightPressed = false;
	static boolean spacePressed = false;
	static boolean actionPressed = false;
	private static KeyboardController keyboard;

	public KeyboardController() {

	}

	public void handleKeyPress(KeyCode code, boolean pressed) {
		switch (code) {
		case W -> upPressed = pressed;
		case S -> downPressed = pressed;
		case A -> leftPressed = pressed;
		case D -> rightPressed = pressed;
		case E -> actionPressed = pressed;
		case SPACE -> {
			if (pressed && !spacePressed) {
				spacePressed = true;
			}
		}
		default -> {
		}
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

	public boolean isActionPressed() {
		return actionPressed;
	}

	public boolean isSpacePressed() {
		if (spacePressed) {
			spacePressed = false;

			return true;
		}
		return false;
	}

	public static KeyboardController getInstance() {
		return keyboard;
	}
}
