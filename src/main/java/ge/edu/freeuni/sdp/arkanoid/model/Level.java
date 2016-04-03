package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.ArrayList;

public class Level implements GameLevel {

    private final String _name;
    private final String _description;
    private final RoomBuilder _builder;


    public Level(String name, String description, RoomBuilder builder) {
        _name = name;
        _description = description;
        _builder = builder;
    }

    public void setLevelOverListener(LevelOverListener listener) {
        _builder.setLevelOverListener(listener);
    }

    public String getDescription() {
        return _description;
    }

    public String getName() {
        return _name;
    }

    public void build(Room room, ScoreCounter _scoreCounter) {
        _builder.build(room, _scoreCounter);
    }
}
