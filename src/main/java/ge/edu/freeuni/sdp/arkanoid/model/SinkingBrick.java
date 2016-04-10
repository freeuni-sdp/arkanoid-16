package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class SinkingBrick extends MovingBrick {

    private static final long SINKING_DELAY = 10000;

    public SinkingBrick(Point position, BrickColor color, Capsule capsule) {
        super(position, color, capsule, Direction.DOWN, SINKING_DELAY);
    }
}
