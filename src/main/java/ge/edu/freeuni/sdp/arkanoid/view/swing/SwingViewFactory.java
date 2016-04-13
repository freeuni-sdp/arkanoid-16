package ge.edu.freeuni.sdp.arkanoid.view.swing;


import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.GameOverView;
import ge.edu.freeuni.sdp.arkanoid.view.IntroView;
import ge.edu.freeuni.sdp.arkanoid.view.LevelView;
import ge.edu.freeuni.sdp.arkanoid.view.RoomView;
import ge.edu.freeuni.sdp.arkanoid.view.ViewFactory;

import javax.swing.*;

public class SwingViewFactory implements ViewFactory {

    private final JFrame _frame;

    public SwingViewFactory(JFrame frame) {
        _frame = frame;
    }

    public IntroView getIntroView() {
        return new SwingIntroView(null, _frame);
    }

    public LevelView getLevelView(LevelPresenter presenter) {

        return new SwingLevelView(presenter, _frame);
    }

    public RoomView getRoomView(RoomPresenter roomPresenter) {
        return null; // new SwingRoomView(roomPresenter, _frame);
    }

    @Override
    public GameOverView getGameOverView(GameOverPresenter presenter) {
        return null; // new SwingGameOverView(presenter, _frame);
    }


}
