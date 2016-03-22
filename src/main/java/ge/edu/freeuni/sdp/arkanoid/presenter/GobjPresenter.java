package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.Point;

import java.util.Set;

public abstract class GobjPresenter<T extends Gobj> extends Presenter {

    private T _gameObject;

    GobjPresenter(T gameObject) {
        _gameObject = gameObject;
    }

    void Draw(CellContent[][] cells) {
        CellContent content = getContent();
        Set<Point> points = _gameObject.getOccupied();
        for (Point current : points) {
            cells[current.X][current.Y] = content;
        }
    }

    abstract CellContent getContent();

    T getGameObject() {
        return _gameObject;
    }
}
