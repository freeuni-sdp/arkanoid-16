package ge.edu.freeuni.sdp.arkanoid.view;

import java.awt.event.KeyEvent;

public abstract class KeyListener {

    public void keyEvent(KeyEvent ke) {
        switch (ke.getID()) {
            case KeyEvent.KEY_PRESSED:
                keyPressed(ke);
                break;
            case KeyEvent.KEY_RELEASED:
                keyReleased(ke);
                break;
        }
    }

    protected abstract void keyPressed(KeyEvent key);
    protected abstract void keyReleased(KeyEvent key);

}
