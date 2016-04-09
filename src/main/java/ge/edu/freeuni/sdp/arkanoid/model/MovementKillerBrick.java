package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by misho on 4/7/2016.
 */
public class MovementKillerBrick extends FrameBrick {
    public MovementKillerBrick(Point position, Size size) {
        super(position, size);
    }

    @Override
    public void interact(Gobj other) {
        //TODO kill the ball if other instanceof MovingBrick
        super.interact(other);
    }
}
