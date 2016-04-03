package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;

import java.awt.event.KeyEvent;

public abstract class LevelView extends View<LevelPresenter> {

    protected LevelView(LevelPresenter presenter) {
        super(presenter);
    }

    @Override
    protected void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_UP:
                upKeyPressed();
                break;
            case KeyEvent.VK_DOWN:
                downKeyPressed();
                break;
            case KeyEvent.VK_ENTER:
                enterPressed();
                break;
            default:
                if (isNumeric(key.getKeyCode()))
                    numberPressed(key.getKeyChar());
                break;
        }
    }

    private boolean isNumeric(int keyCode) {
        return keyCode >= 48 && keyCode <= 57;
    }

    @Override
    protected void keyReleased(KeyEvent key) {

    }

    protected abstract void upKeyPressed();

    protected abstract void downKeyPressed();

    protected abstract void enterPressed();

    protected abstract void numberPressed(char keyChar);
}
