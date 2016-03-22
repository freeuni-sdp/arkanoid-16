package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.Presenter;

public abstract class View<T extends Presenter> {

    private T _presenter;

    protected View(T presenter) {
        _presenter = presenter;
    }

    protected abstract void show();

    public T getPresenter() {
        return _presenter;
    }
}
