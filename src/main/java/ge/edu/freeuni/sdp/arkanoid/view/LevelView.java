package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;

public abstract class LevelView extends View<LevelPresenter> {

    protected LevelView(LevelPresenter presenter) {
        super(presenter);
    }
}
