package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

public class MovingBrick extends NormalBrick {

    private Direction direction;
    private final long movementDelay;
    private long lastMovementTime;

    public MovingBrick(Point position, BrickColor color, Capsule capsule, Direction direction, long movementDelay) {
        super(position, color, capsule);
        this.direction = direction;
        this.movementDelay = movementDelay;
        this.lastMovementTime = System.currentTimeMillis();
    }

    @Override
    public void move() {
        long currTime = System.currentTimeMillis();

        if (currTime - lastMovementTime >= movementDelay) {
            setSpeed(getMovementVector());
            super.move();
            setSpeed(new Speed(new Point(0, 0)));
            lastMovementTime = currTime;
        }
    }

    private Speed getMovementVector() {
        return new Speed(direction.toPoint().multiply(getShape().getSize().toPoint()));
    }
}
