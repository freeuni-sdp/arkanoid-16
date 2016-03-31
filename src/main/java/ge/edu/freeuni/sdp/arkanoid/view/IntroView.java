package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.IntroPresenter;

public abstract class IntroView extends View<IntroPresenter> {

    protected IntroView(IntroPresenter presenter) {
        super(presenter);
    }
}
