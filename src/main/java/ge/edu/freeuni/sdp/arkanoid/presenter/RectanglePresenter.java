package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

abstract class RectanglePresenter<T extends Gobj> extends GobjPresenter<T> {

    RectanglePresenter(T gameObject, Size size) {
        super(size, gameObject);
    }

    @Override
    void Draw(CellContent[][] cells) {
        CellContent content = getContent();
        Rectangle shape = (Rectangle) getGameObject().getShape();
        Point l = shape.getPosition();
        Point r = shape.getBottomRight();
        for (int i = (int) Math.round(l.X); i < Math.round(r.X); i++) {
            for (int j = (int) Math.round(l.Y); j < Math.round(r.Y); j++) {
                Point current = new Point(i, j);
                Size size = getSize();
                if (size.isInRange(current)) {
                    cells[i][j] = content;
                }
            }
        }
    }


    abstract CellContent getContent();

}
