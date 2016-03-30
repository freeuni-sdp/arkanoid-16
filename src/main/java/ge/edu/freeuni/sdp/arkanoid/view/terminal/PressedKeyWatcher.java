package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PressedKeyWatcher {
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean spacePressed = false;
    private static boolean escapePressed = false;

    public static boolean isLeftPressed() {
        synchronized (PressedKeyWatcher.class) {
            return leftPressed;
        }
    }

    public static boolean isRightPressed() {
        synchronized (PressedKeyWatcher.class) {
            return rightPressed;
        }
    }

    public static boolean isSpacePressed() {
        synchronized (PressedKeyWatcher.class) {
            return spacePressed;
        }
    }

    public static boolean isEscapePressed() {
        synchronized (PressedKeyWatcher.class) {
            return escapePressed;
        }
    }


    public static void init() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (PressedKeyWatcher.class) {
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
                }
            }
        });
    }

}

