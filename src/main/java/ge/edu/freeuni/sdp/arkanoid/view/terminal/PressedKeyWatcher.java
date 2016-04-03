package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import ge.edu.freeuni.sdp.arkanoid.view.KeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

class PressedKeyWatcher {
    private static KeyListener eventWatcher;
    private static volatile boolean leftPressed = false;
    private static volatile boolean rightPressed = false;
    private static volatile boolean spacePressed = false;
    private static volatile boolean escapePressed = false;

    static boolean isLeftPressed() {
        return leftPressed;
    }

    static boolean isRightPressed() {
        return rightPressed;
    }

    static boolean isSpacePressed() {
        return spacePressed;
    }

    static boolean isEscapePressed() {
        return escapePressed;
    }


    static void init() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ke -> {
            if (eventWatcher != null)
                eventWatcher.keyEvent(ke);

//            switch (ke.getID()) {
//                case KeyEvent.KEY_PRESSED:
//                    if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
//                        leftPressed = true;
//                    }
//                    if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
//                        rightPressed = true;
//                    }
//                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
//                        spacePressed = true;
//                    }
//                    if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                        escapePressed = true;
//                    }
//                    break;
//
//                case KeyEvent.KEY_RELEASED:
//                    if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
//                        leftPressed = false;
//                    }
//                    if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
//                        rightPressed = false;
//                    }
//                    if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
//                        spacePressed = false;
//                    }
//                    if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                        escapePressed = false;
//                    }
//                    break;
//            }
            return false;
        });
    }

    static void addWatcher(KeyListener watcher) {
        eventWatcher = watcher;
    }

}

