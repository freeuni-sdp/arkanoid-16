package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.Configuration;

public class LevelPresenter extends Presenter {

    private Configuration _config;
    private LevelSelectionListener _listener;

    public LevelPresenter() {
        this(Configuration.getInstance());
    }

    public LevelPresenter(Configuration config) {
        _config = config;
    }

    public void setLevelSelectionListener(LevelSelectionListener listener) {
        _listener = listener;
    }

    public String[] getLevelNames() {
        return _config.getLevelNames();
    }

    public boolean setSelection(int index) {
        if (index < 0 || index >= getLevelNames().length) return false;
        _config.setSelectedLevelIndex(index);
        _listener.levelSelectionChanged();
        return true;
    }

    public int getSelection() {
        return _config.getSelectedLevelIndex();
    }
}
