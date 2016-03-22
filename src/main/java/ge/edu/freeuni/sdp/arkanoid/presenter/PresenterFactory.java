package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;

public class PresenterFactory {

    private GameFacade _gameFacade;

    public PresenterFactory(GameFacade gameFacade) {
        _gameFacade = gameFacade;
    }

    public LevelPresenter getLevelPresenter() {

        return new LevelPresenter();
    }

    public RoomPresenter getRoomPresenter() {
        return new RoomPresenter(_gameFacade, new GobjPresenterFactory());
    }
}