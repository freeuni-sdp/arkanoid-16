package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.Configuration;
import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.Level;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;

public class RoomPresenter extends Presenter {

    private final GameFacade _game;
    private final GobjPresenterFactory _gobjPresenterFactory;
    private final CellContent[][] _cellsCache;
    private CellUpdateListener _listener;
    private Direction _direction;

    public RoomPresenter(GameFacade game, GobjPresenterFactory gobjPresenterFactory) {
        _game = game;
        _gobjPresenterFactory = gobjPresenterFactory;
        _cellsCache = initCells();
        _direction = Direction.NONE;
    }

    public void init() {
        Level level = Configuration.getInstance().getSelectedLevel();
        _game.init(level);
    }

    public void setCellUpdateListener(CellUpdateListener listener) {
        _listener = listener;
    }

    public boolean isGameOver() {
        return _game.isGameOver();
    }

    public void execute(Command command) {
        scanAndNotify();
        switch (command) {

            case None:
                break;
            case Stop:
                _direction = Direction.NONE;
                break;
            case Left:
                _direction = Direction.LEFT;
                break;
            case Right:
                _direction = Direction.RIGHT;
                break;
            case Fire:
                _game.fire();
                break;
        }

        _game.move(_direction);
    }

    private void scanAndNotify() {

        CellContent[][] newContent = initCells();
        for (Gobj gobj : _game.getGobjs()) {
            GobjPresenter presenter = _gobjPresenterFactory.getPresenter(gobj);
            presenter.Draw(newContent);
        }

        for (int i = 0; i < _cellsCache.length; i++) {
            for (int j = 0; j < _cellsCache[i].length; j++) {

                CellContent currentValue = _cellsCache[i][j];
                CellContent newValue = newContent[i][j];

                boolean hasChanged = currentValue != newValue;
                if (hasChanged) {
                    updateListener(i, j, newValue);
                    _cellsCache[i][j] = newValue;
                }
            }
        }
    }

    private void updateListener(int i, int j, CellContent newValue) {
        if (_listener == null)
            return;
        CellPosition position = new CellPosition(i, j);
        _listener.updateCell(position, newValue);
    }

    private CellContent[][] initCells() {
        int width = _game.getSize().getWidth();
        int height = _game.getSize().getHeight();
        CellContent[][] result = new CellContent[width][];
        for (int i = 0; i < width; i++) {
            result[i] = new CellContent[height];
            for (int j = 0; j < height; j++) {
                result[i][j] = CellContent.None;
            }
        }
        return result;
    }

    private Direction toDirection(Command key) {
        switch (key) {
            case Left:
                return Direction.LEFT;
            case Right:
                return Direction.RIGHT;
            default:
                return Direction.NONE;
        }
    }
}