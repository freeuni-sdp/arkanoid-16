package ge.edu.freeuni.sdp.arkanoid.view.terminal;


import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.*;
import ge.edu.freeuni.sdp.arkanoid.view.RoomView;

class TerminalRoomView extends RoomView implements CellUpdateListener, StatusUpdateListener {


    private final Terminal _terminal;

    private String _currentScore;

    private DefaultPressedKeyWatcher _pressedKeyWatcher;
    private long _millis = 10;

    TerminalRoomView(RoomPresenter presenter, Terminal terminal) {
        super(presenter);
        _pressedKeyWatcher = DefaultPressedKeyWatcher.getInstance();
        _pressedKeyWatcher.init();
        _terminal = terminal;
        presenter.set_cellUpdateListener(this);
        presenter.setStatusUpdateListener(this);
        _currentScore = null;

    }

    TerminalRoomView(RoomPresenter presenter, Terminal terminal, DefaultPressedKeyWatcher watcher,long millis) {
        this(presenter,terminal);
        _pressedKeyWatcher = watcher;
        _millis = millis;

    }

    public void show() {
        RoomPresenter presenter = getPresenter();
        presenter.init();

        _terminal.clearScreen();
        while (true) {
            Command command = Command.None;
            if (presenter.isGameOver()) break;

            if (_pressedKeyWatcher.isRightPressed()) command = Command.Right;
            if (_pressedKeyWatcher.isLeftPressed()) command = Command.Left;
            if (_pressedKeyWatcher.isSpacePressed()) command = Command.Fire;
            if (_pressedKeyWatcher.isPausedPressed()) {
                command = Command.Pause;
                presenter.execute(command);
                do{
                    sleep();
                }while (_pressedKeyWatcher.isPausedPressed() && !_pressedKeyWatcher.isEscapePressed());
            }
            if (_pressedKeyWatcher.isEscapePressed()) break;
            presenter.execute(command);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(_millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateCell(CellPosition position, CellContent content) {

        _terminal.moveCursor(position.x, position.y + 1);
        switch (content) {

            case None:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter(' ');
                break;
            case GrayBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.YELLOW);
                _terminal.putCharacter('█');
                break;
            case RedBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.RED);
                _terminal.putCharacter('█');
                break;
            case BlueBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.BLUE);
                _terminal.putCharacter('█');
                break;
            case WhiteBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('█');
                break;
            case OrangeBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.MAGENTA);
                _terminal.putCharacter('█');
                break;
            case LightBlueBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.CYAN);
                _terminal.putCharacter('█');
                break;
            case GreenBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.GREEN);
                _terminal.putCharacter('█');
                break;
            case DarkBlueBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.BLUE);
                _terminal.putCharacter('█');
                break;
            case LightOrangeBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.MAGENTA);
                _terminal.putCharacter('█');
                break;
            case YellowBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.YELLOW);
                _terminal.putCharacter('█');
                break;
            case GoldBrick:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.YELLOW);
                _terminal.putCharacter('█');
                break;
            case Paddle:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('▄');
                break;
            case Ball:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('○');
                break;
            case ECapsule:
                _terminal.applyBackgroundColor(Terminal.Color.BLUE);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('E');
                break;
            case BCapsule:
                _terminal.applyBackgroundColor(Terminal.Color.GREEN);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('B');
                break;
            case ACapsule:
                _terminal.applyBackgroundColor(Terminal.Color.RED);
                _terminal.applyBackgroundColor(Terminal.Color.CYAN);
                _terminal.putCharacter('A');
                break;
            case PCapsule:
                _terminal.applyBackgroundColor(Terminal.Color.GREEN);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('P');
                break;
            case DCapsule:
                _terminal.applyBackgroundColor(Terminal.Color.GREEN);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('D');
                break;
            case Beam:
                _terminal.applyBackgroundColor(Terminal.Color.BLACK);
                _terminal.applyForegroundColor(Terminal.Color.RED);
                _terminal.putCharacter('|');
                break;
            case KillCapsule:
                _terminal.applyBackgroundColor(Terminal.Color.RED);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('K');
                break;
            case SCapsule:
                _terminal.applyBackgroundColor(Terminal.Color.MAGENTA);
                _terminal.applyForegroundColor(Terminal.Color.WHITE);
                _terminal.putCharacter('S');
                break;
            case LCapsule:
                _terminal.applyBackgroundColor(Terminal.Color.GREEN);
                _terminal.applyForegroundColor(Terminal.Color.YELLOW);
                _terminal.putCharacter('L');
                break;
            case CCapsule:
                _terminal.applyBackgroundColor(Terminal.Color.GREEN);
                _terminal.applyForegroundColor(Terminal.Color.YELLOW);
                _terminal.putCharacter('C');
                break;
        }
    }

    public void updateScore(int score) {
        _currentScore = "Score: "+score;
        for(int i=_currentScore.length()-1; i>=0; i--){

            _terminal.moveCursor(_terminal.getTerminalSize().getRows() * 3 - _currentScore.length() + i, 0);
            _terminal.putCharacter(_currentScore.charAt(i));
        }
    }



    public void updateLives(int lives) {
        _terminal.applyBackgroundColor(Terminal.Color.BLUE);
        _terminal.applyForegroundColor(Terminal.Color.WHITE);

        for (int i = 0; i < lives; i++) {
            _terminal.moveCursor(i, 0);
            _terminal.putCharacter('*');
        }
    }
}
