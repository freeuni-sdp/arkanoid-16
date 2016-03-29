package ge.edu.freeuni.sdp.arkanoid.model;

public class Level implements GameLevel {

    private final String _name;
    private final String _description;
    private RoomBuilder _builder;


    public Level(String name, String description, RoomBuilder builder) {
        _name = name;
        _description = description;
        _builder = builder;
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
