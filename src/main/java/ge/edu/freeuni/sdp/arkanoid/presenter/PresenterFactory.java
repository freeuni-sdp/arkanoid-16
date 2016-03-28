package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class PresenterFactory {

    private final GameFacade _gameFacade;
    private final Size _size;

    public PresenterFactory(GameFacade gameFacade, Size size) {
        _gameFacade = gameFacade;
        _size = size;
    }

    public LevelPresenter getLevelPresenter() {

        return new LevelPresenter();
    }

    public RoomPresenter getRoomPresenter() {
        return new RoomPresenter(_gameFacade, new GobjPresenterFactory(_size));
    }
}