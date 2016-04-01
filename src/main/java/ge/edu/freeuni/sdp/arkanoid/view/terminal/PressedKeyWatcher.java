package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PressedKeyWatcher {
    private static volatile boolean leftPressed = false;
    private static volatile boolean rightPressed = false;
    private static volatile boolean spacePressed = false;
    private static volatile boolean escapePressed = false;

    public static boolean isLeftPressed() {
        return leftPressed;
    }

    public static boolean isRightPressed() {
        return rightPressed;
    }

    public static boolean isSpacePressed() {
        return spacePressed;
    }

    public static boolean isEscapePressed() {
        return escapePressed;
    }


    public static void init() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ke -> {
            switch (ke.getID()) {
                case KeyEvent.KEY_PRESSED:
                    if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftPressed = true;
                    }
                    if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightPressed = true;
                    }
                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        spacePressed = true;
                    }
                    if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        escapePressed = true;
                    }
                    break;

                case KeyEvent.KEY_RELEASED:
                    if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftPressed = false;
                    }
                    if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightPressed = false;
                    }
                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                        spacePressed = false;
                    }
                    if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        escapePressed = false;
                    }
                    break;
            }
            return false;
        });
    }

}

