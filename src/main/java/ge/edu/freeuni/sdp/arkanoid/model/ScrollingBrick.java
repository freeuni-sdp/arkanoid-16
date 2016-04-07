package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created by misho on 4/7/2016.
 */
public class ScrollingBrick extends MovingBrick {
    private static final long SCROLLING_DELAY = 3000;

    public ScrollingBrick(Point position, BrickColor color, Capsule capsule) {
        super(position, color, capsule, Direction.RIGHT, SCROLLING_DELAY);
    }
}
