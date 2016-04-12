package ge.edu.freeuni.sdp.arkanoid.view.Swing;

import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.*;

import javax.swing.*;

/**
 * Created by kaneki on 4/11/16.
 */
public class SwingViewFactroy implements ViewFactory{

    private final JFrame frame;
    
    public SwingViewFactroy(JFrame frame){
        this.frame = frame;
    }

    @Override
    public GameOverView getGameOverView(GameOverPresenter presenter) {
        return new SwingGameOverView(presenter, frame);
    }

    @Override
    public LevelView getLevelView(LevelPresenter presenter) {
        return null;
    }

    @Override
    public RoomView getRoomView(RoomPresenter roomPresenter) {
        return null;
    }

    @Override
    public IntroView getIntroView() {
        return null;
    }
}
