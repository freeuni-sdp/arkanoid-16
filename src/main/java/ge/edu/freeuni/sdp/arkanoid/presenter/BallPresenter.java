package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Ball;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class BallPresenter extends GobjPresenter<Ball> {

    BallPresenter(Ball gameObject, Size size) {
        super(size, gameObject);
    }

    void Draw(CellContent[][] cells) {
        Point p = getGameObject().getPosition();
        int i = (int) Math.round(p.X - 0.5);
        int j = (int) Math.round(p.Y - 0.5);

        Point current = new Point(i, j);
        Size size = getSize();
        if (size.isInRange(current)) {
            cells[i][j] = CellContent.Ball;
        }
    }
}
