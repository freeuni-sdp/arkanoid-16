package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public abstract class GobjPresenter<T extends Gobj> extends Presenter {
    protected final T _gameObject;
    protected final Size _size;

    public GobjPresenter(Size size, T gameObject) {
        _size = size;
        _gameObject = gameObject;
    }

    abstract void Draw(CellContent[][] cells);

    T getGameObject() {
        return _gameObject;
    }

    public Size getSize() {
        return _size;
    }
}
