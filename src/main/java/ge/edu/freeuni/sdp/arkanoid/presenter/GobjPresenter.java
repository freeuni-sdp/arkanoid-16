package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.Point;
import ge.edu.freeuni.sdp.arkanoid.model.Size;

import java.util.Set;

public abstract class GobjPresenter<T extends Gobj> extends Presenter {

    private T _gameObject;
    private Size _size;

    GobjPresenter(T gameObject, Size size) {
        _gameObject = gameObject;
        _size = size;
    }

    void Draw(CellContent[][] cells) {
        CellContent content = getContent();
        Set<Point> points = getGameObject().getOccupied();
        for (Point current : points) {
            Size size = getSize();
            if (size.isInRange(current)) {
                cells[current.X][current.Y] = content;
            }
        }
    }


    abstract CellContent getContent();

    T getGameObject() {
        return _gameObject;
    }

    public Size getSize() {
        return _size;
    }
}
