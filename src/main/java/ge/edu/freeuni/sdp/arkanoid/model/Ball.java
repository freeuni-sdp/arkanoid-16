package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public class Ball extends Gobj {

    private static boolean[][] _shape = {
            {true}
    };
    private static Speed _initialSpeed = new Speed(1, -1);
    private final Room _room;
    private final Point _initialPosition;
    private Size _size;
    private int _lives;

    protected Ball(Point position, Room room, Size size) {

        super(position);
        _initialPosition = position;
        _size = size;
        _lives = 3;
        _room = room;
        init();
    }

    private void init() {
        setSpeed(_initialSpeed);
        setPosition(_initialPosition);
    }

    @Override
    protected boolean[][] getShape() {
        return _shape;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Ball;
    }

    @Override
    public void interactAt(Gobj other, Set<Point> intersection) {
        if (other.getKind() == GobjKind.Paddle) {
            setSpeed(getSpeed().mirrorVertically());
        }
    }


    @Override
    public boolean isAlive() {
        return _lives > 0;
    }

    @Override
    public void move() {
        Point current = getPosition();
        Point next = current.add(getSpeed());
        if (next.X < 0 || next.X >= _size.getWidth()) setSpeed(getSpeed().mirrorHorizontally());
        if (next.Y < 0) setSpeed(getSpeed().mirrorVertically());
        super.move();
    }

    public int getLives() {
        return _lives;
    }

    public void incLives() {
        _lives++;
    }

    public void decLives() {
        _lives--;
        init();
    }
}
