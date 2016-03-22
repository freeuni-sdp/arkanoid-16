package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;

public abstract class RoomView extends View<RoomPresenter> {
    protected RoomView(RoomPresenter presenter) {
        super(presenter);
    }
}
