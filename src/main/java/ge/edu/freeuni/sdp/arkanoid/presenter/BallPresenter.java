package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Ball;
import ge.edu.freeuni.sdp.arkanoid.model.Size;

public class BallPresenter extends GobjPresenter<Ball> {

    BallPresenter(Ball gameObject, Size size) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {
        return CellContent.Ball;
    }
}
