package ge.edu.freeuni.sdp.arkanoid.view.terminal;


import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.*;
import ge.edu.freeuni.sdp.arkanoid.view.RoomView;

public class TerminalRoomView extends RoomView implements CellUpdateListener {


    private Terminal _terminal;

    protected TerminalRoomView(RoomPresenter presenter, Terminal terminal) {
        super(presenter);
        _terminal = terminal;
        presenter.setCellUpdateListener(this);
    }

    public void show() {
        RoomPresenter presenter = getPresenter();
        presenter.init();

        _terminal.clearScreen();
        while (true) {
            Command command = Command.None;
            Key key = _terminal.readInput();
            if (presenter.isGameOver()) break;

            if (key == null) {
                command = Command.None;
            } else {
                if (key.getCharacter() == ' ') command = Command.Fire;
                if (key.getKind() == Key.Kind.Escape) break;
                if (key.getKind() == Key.Kind.ArrowRight) command = Command.Right;
                if (key.getKind() == Key.Kind.ArrowLeft) command = Command.Left;
                if (key.getKind() == Key.Kind.ArrowDown) command = Command.Stop;
            }

            presenter.execute(command);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCell(CellPosition position, CellContent content) {

        _terminal.moveCursor(position.x, position.y);
        switch (content) {

            case None:
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter(' ');
                break;
            case RedBrick:
                _terminal.applyForegroundColor(Terminal.Color.RED);
                _terminal.putCharacter('█');
                break;
            case Paddle:
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('▄');
                break;
            case Ball:
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('○');
                break;
            case ECapsule:
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('E');
                break;
        }
    }
}
