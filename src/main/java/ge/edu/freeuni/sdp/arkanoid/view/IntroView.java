package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.IntroPresenter;

import java.awt.event.KeyEvent;

public abstract class IntroView extends View<IntroPresenter> {

    protected IntroView(IntroPresenter presenter) {
        super(presenter);
    }

    @Override
    protected void keyPressed(KeyEvent key) {

    }

    @Override
    protected void keyReleased(KeyEvent key) {

    }
}
