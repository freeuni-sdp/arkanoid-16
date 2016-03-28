package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.Paddle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class PaddlePresenter extends RectanglePresenter {
    PaddlePresenter(Paddle gameObject, Size size) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {
        return CellContent.Paddle;
    }
}
