package ge.edu.freeuni.sdp.arkanoid.view;


import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.PresenterFactory;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;

public class ViewController {

    private final ViewFactory _viewFactory;
    private final PresenterFactory _presenterFactory;

    public ViewController(ViewFactory viewFactory,
                          PresenterFactory presenterRegistry) {
        _viewFactory = viewFactory;
        _presenterFactory = presenterRegistry;
    }

    public void run() {
        boolean gameContinue = true;
        
        while(gameContinue){
            IntroView introView = _viewFactory.getIntroView();
            introView.show();
            Memento memento = new Memento();
            new Originator().restoreFromMemento(memento);
            if(!memento.getSavedState().isExistState()) {
                LevelPresenter levelPresenter = _presenterFactory.getLevelPresenter();
                LevelView levelView = _viewFactory.getLevelView(levelPresenter);
                levelView.show();
            }else {
                Configuration.getInstance().setSelectedLevelIndex(memento.getSavedState().getLevelIndex());
            }

            RoomPresenter roomPresenter = _presenterFactory.getRoomPresenter();
            RoomView roomView = _viewFactory.getRoomView(roomPresenter);
            roomView.show();
        
            GameOverPresenter gameOverPresenter = _presenterFactory.getGameOverPresenter();
            GameOverView gameOverView = _viewFactory.getGameOverView(gameOverPresenter);
            gameOverView.show();
            gameContinue = gameOverPresenter.isGameActive();
        }
    }
}