package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.Paddle;
import ge.edu.freeuni.sdp.arkanoid.model.Size;

public class PaddlePresenter extends GobjPresenter {
    PaddlePresenter(Paddle gameObject, Size size) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {
        return CellContent.Paddle;
    }
}
