package ge.edu.freeuni.sdp.arkanoid.view;

import ge.edu.freeuni.sdp.arkanoid.presenter.Presenter;

abstract class View<T extends Presenter> {

    private final T _presenter;

    View(T presenter) {
        _presenter = presenter;
    }

    protected abstract void show();

    protected T getPresenter() {
        return _presenter;
    }
}
