package ge.edu.freeuni.sdp.arkanoid.model.geometry;

import java.util.Random;

public class Direction {

    // Note there is no Direction.NONE
    public static Direction LEFT = new Direction(-1, 0);
    public static Direction RIGHT = new Direction(1, 0);
    public static Direction UP = new Direction(0, -1);
    public static Direction DOWN = new Direction(0, 1);
    public static Direction NONE = new Direction(0, 0);

    private static Direction[] ALL = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};


    private int _dx;
    private int _dy;

    private Direction(int dx, int dy) {
        _dx = dx;
        _dy = dy;
    }

    public static Direction getDirection(Point from, Point to) {
        int newDx = (int) Math.signum(to.X - from.X);
        int newDy = (int) Math.signum(to.Y - from.Y);
        if (newDx == -1 && newDy == 0) {
            return Direction.LEFT;
        } else if (newDx == 1 && newDy == 0) {
            return Direction.RIGHT;
        } else if (newDx == 0 && newDy == -1) {
            return Direction.UP;
        } else if (newDx == 0 && newDy == 1) {
            return Direction.DOWN;
        }
        throw new IllegalStateException("no such direction");
    }

    public Point addTo(Point point) {
        return new Point(point.X + _dx, point.Y + _dy);
    }

    public boolean isOppositeTo(Direction other) {
        return (this == LEFT && other == RIGHT)
                || (this == DOWN && other == UP)
                || (other == LEFT && this == RIGHT)
                || (other == DOWN && this == UP);
    }

    public Direction getNextRandomDirection(Random random) {
        Direction candidate = this;
        while (candidate == this || candidate.isOppositeTo(this)) {
            int index = random.nextInt(4);
            candidate = Direction.ALL[index];
        }
        return candidate;
    }

    public Point toPoint() {
        return new Point(this._dx, this._dy);
    }

    public Speed toSpeed() {
        return new Speed(this.toPoint());
    }
}