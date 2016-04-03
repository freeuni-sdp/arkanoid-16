package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.IntroView;
import ge.edu.freeuni.sdp.arkanoid.view.LevelView;
import ge.edu.freeuni.sdp.arkanoid.view.RoomView;
import ge.edu.freeuni.sdp.arkanoid.view.ViewFactory;

public class TerminalViewFactory implements ViewFactory {

    private final Terminal _terminal;

    public TerminalViewFactory(Terminal terminal) {
        _terminal = terminal;
        PressedKeyWatcher.init();
    }

    public IntroView getIntroView() {
        return new TerminalIntroView(null, _terminal);
    }

    public LevelView getLevelView(LevelPresenter presenter) {
        LevelView lv = new TerminalLevelView(presenter, _terminal);
        PressedKeyWatcher.addWatcher(lv);
        return lv;
    }

    public RoomView getRoomView(RoomPresenter roomPresenter) {
        RoomView rv = new TerminalRoomView(roomPresenter, _terminal);
        PressedKeyWatcher.addWatcher(rv);
        return rv;
    }


}
