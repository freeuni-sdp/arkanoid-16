package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Ball;

public class BallPresenter extends GobjPresenter<Ball> {

    BallPresenter(Ball gameObject) {
        super(gameObject);
    }

    @Override
    CellContent getContent() {
        return CellContent.Ball;
    }
}
