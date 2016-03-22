package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.Paddle;

public class PaddlePresenter extends GobjPresenter {
    PaddlePresenter(Paddle gameObject) {
        super(gameObject);
    }

    @Override
    CellContent getContent() {
        return CellContent.Paddle;
    }
}
