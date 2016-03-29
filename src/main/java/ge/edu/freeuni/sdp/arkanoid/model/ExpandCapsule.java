package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class ExpandCapsule extends Capsule {

    protected ExpandCapsule(Point position, Room room) {
        super(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            Paddle oldPaddle = (Paddle) other;
            Paddle newPaddle = new ExpandedPaddle(other.getPosition());
            oldPaddle.exchange(newPaddle);
        }
    }
}
