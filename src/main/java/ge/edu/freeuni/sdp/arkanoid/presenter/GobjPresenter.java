package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

abstract class GobjPresenter<T extends Gobj> extends Presenter {
    private final T _gameObject;
    private final Size _size;

    GobjPresenter(Size size, T gameObject) {
        _size = size;
        _gameObject = gameObject;
    }

    abstract void Draw(CellContent[][] cells);

    T getGameObject() {
        return _gameObject;
    }

    Size getSize() {
        return _size;
    }
}
