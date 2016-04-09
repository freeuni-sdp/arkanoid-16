package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.GameOverView;
import ge.edu.freeuni.sdp.arkanoid.view.IntroView;
import ge.edu.freeuni.sdp.arkanoid.view.LevelView;
import ge.edu.freeuni.sdp.arkanoid.view.RoomView;
import ge.edu.freeuni.sdp.arkanoid.view.ViewFactory;

public class TerminalViewFactory implements ViewFactory {

    private final Terminal _terminal;

    public TerminalViewFactory(Terminal terminal) {
        _terminal = terminal;
    }

    public IntroView getIntroView() {
        return new TerminalIntroView(null, _terminal);
    }

    public LevelView getLevelView(LevelPresenter presenter) {
        
        return new TerminalLevelView(presenter, _terminal);
    }

    public RoomView getRoomView(RoomPresenter roomPresenter) {
        return new TerminalRoomView(roomPresenter, _terminal);
    }

    @Override
    public GameOverView getGameOverView(GameOverPresenter presenter) {
        return new TerminalGameOverView(presenter, _terminal);
    }


}
