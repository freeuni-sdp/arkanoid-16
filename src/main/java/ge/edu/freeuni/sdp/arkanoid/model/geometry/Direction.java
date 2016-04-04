package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Direction {

    // Note there is no Direction.NONE
    public static final Direction LEFT = new Direction(-1, 0);
    public static final Direction RIGHT = new Direction(1, 0);
    public static final Direction NONE = new Direction(0, 0);
    private static final Direction UP = new Direction(0, -1);
    public static final Direction DOWN = new Direction(0, 1);
    @SuppressWarnings({"MismatchedReadAndWriteOfArray", "unused"})
    private static final Direction[] ALL = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};


    private final int _dx;
    private final int _dy;

    private Direction(int dx, int dy) {
        _dx = dx;
        _dy = dy;
    }

    public Point toPoint() {
        return new Point(this._dx, this._dy);
    }

    public Speed toSpeed() {
        return new Speed(this.toPoint());
    }
}