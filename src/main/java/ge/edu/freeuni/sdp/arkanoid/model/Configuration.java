package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.List;

public class Configuration {

    private static Configuration _singleton;
    private final List<Level> _levels;
    private final Size _size;
    private int _selectedLevelIndex;

    private Configuration(Size size, List<Level> levels) {
        _size = size;
        _levels = levels;
    }

    public static void init(Size size, List<Level> levels) {
        if (_singleton != null)
            throw new IllegalStateException("Can not initialize twice.");
        _singleton = new Configuration(size, levels);
    }

    public static Configuration getInstance() {
        if (_singleton == null)
            throw new IllegalStateException("You must call init() first.");
        return _singleton;
    }

    public Size getSize() {
        return _size;
    }

    public Level getSelectedLevel() {
        return _levels.get(_selectedLevelIndex);
    }

    public int getSelectedLevelIndex() {
        return _selectedLevelIndex;
    }

    public void setSelectedLevelIndex(int index) {
        _selectedLevelIndex = index;
    }

    public String[] getLevelNames() {
        String[] result = new String[_levels.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = _levels.get(i).getName();
        }
        return result;
    }
}
