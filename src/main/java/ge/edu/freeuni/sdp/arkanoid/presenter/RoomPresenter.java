package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;

public class RoomPresenter extends Presenter {

    private final GameFacade _game;
    private final GobjPresenterFactory _gobjPresenterFactory;
    private final CellContent[][] _cellsCache;
    private CellUpdateListener _cellUpdateListener;
    private StatusUpdateListener _statusUpdateListener;
    private Direction _direction;
    private int _lives ;
    private boolean _pause;
    private int _score;
    private boolean _are_levels_over;

    RoomPresenter(GameFacade game, GobjPresenterFactory gobjPresenterFactory) {
        _game = game;
        _gobjPresenterFactory = gobjPresenterFactory;
        _cellsCache = initCells();
        _direction = Direction.NONE;
    }

    public void init() {
        Level level = Configuration.getInstance().getSelectedLevel();
        _game.init(level);
        _lives = _game.geLives();
        _score = _game.getScore();
        _are_levels_over = false;
    }

    public void set_cellUpdateListener(CellUpdateListener listener) {
        _cellUpdateListener = listener;
    }
    public void setStatusUpdateListener(StatusUpdateListener listener){_statusUpdateListener = listener;}
    public boolean isGameOver() {
        return _game.isGameOver() || _are_levels_over;
    }

    public void execute(Command command) {
        scanAndNotify();
        _direction = Direction.NONE;
        _pause = true;
        switch (command) {
            case None:
                break;
            case Stop:
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
            case Pause:
                _game.pause();
                break;
        }



        _game.move(_direction);
        if (_game.isLevelCleared()) {
            Configuration conf = Configuration.getInstance();

            int ind = conf.getSelectedLevelIndex();

            if (ind < conf.getLevelNames().length - 1) {
                conf.setSelectedLevelIndex(ind + 1);
                _game.init(conf.getSelectedLevel());
            } else {
                _are_levels_over = true;
            }

        }

        int lives  = this._game.geLives();
        if (this._lives != lives){
            _statusUpdateListener.updateLives(lives);
            _lives = lives;
        }

        int score = this._game.getScore();
        if (this._score != score){
            _statusUpdateListener.updateScore(score);
            _score = score;
        }

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
                    updateCellListener(i, j, newValue);
                    _cellsCache[i][j] = newValue;
                }
            }
        }
    }

    private void updateCellListener(int i, int j, CellContent newValue) {
        if (_cellUpdateListener == null)
            return;
        CellPosition position = new CellPosition(i, j);
        _cellUpdateListener.updateCell(position, newValue);
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


}