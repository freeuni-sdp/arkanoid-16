package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.Command;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;

import java.awt.event.KeyEvent;

public abstract class RoomView extends View<RoomPresenter> {
    protected Command command = Command.None;

    protected RoomView(RoomPresenter presenter) {
        super(presenter);
    }

    @Override
    protected void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                command = Command.Left;
                break;
            case KeyEvent.VK_RIGHT:
                command = Command.Right;
                break;
            case KeyEvent.VK_SPACE:
                command = Command.Fire;
                break;
            case KeyEvent.VK_ESCAPE:
                escPressed();
                break;
        }
    }

    @Override
    protected void keyReleased(KeyEvent key) {
        command = Command.None;
    }

    protected abstract void escPressed();
}
