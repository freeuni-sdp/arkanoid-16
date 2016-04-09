package ge.edu.freeuni.sdp.arkanoid.view;


import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;

public interface ViewFactory {
    
    GameOverView getGameOverView(GameOverPresenter presenter);

    LevelView getLevelView(LevelPresenter presenter);

    RoomView getRoomView(RoomPresenter roomPresenter);

    IntroView getIntroView();
}
