package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class KillerBrick extends MovementKillerBrick {
    public KillerBrick(Point position, Size size, Room room) {
        super(position, size, room);
    }

    @Override
    public void interact(Gobj other) {
        //TODO kill the ball if other instanceof Ball
        super.interact(other);
    }
}
