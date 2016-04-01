package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Ball;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.GridIndex;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class BallPresenter extends GobjPresenter<Ball> {

    BallPresenter(Ball gameObject, Size size) {
        super(size, gameObject);
    }

    void Draw(CellContent[][] cells) {
        GridIndex index = getGameObject().getPosition().toGridIndex();
        Size size = getSize();
        if (size.isInRange(index.toPoint())) {
            cells[index.X][index.Y] = CellContent.Ball;
        }
    }
}
