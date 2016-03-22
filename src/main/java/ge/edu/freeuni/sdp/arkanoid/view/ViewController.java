package ge.edu.freeuni.sdp.arkanoid.view;


import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.PresenterFactory;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;

public class ViewController {

    private ViewFactory _viewFactory;
    private PresenterFactory _presenterFactory;

    public ViewController(ViewFactory viewFactory,
                          PresenterFactory presenterRegistry) {
        _viewFactory = viewFactory;
        _presenterFactory = presenterRegistry;
    }

    public void run() {

        while (true) {
            LevelPresenter levelPresenter = _presenterFactory.getLevelPresenter();
            LevelView levelView = _viewFactory.getLevelView(levelPresenter);
            levelView.show();

            RoomPresenter roomPresenter = _presenterFactory.getRoomPresenter();
            RoomView roomView = _viewFactory.getRoomView(roomPresenter);
            roomView.show();

            break;
        }
    }
}