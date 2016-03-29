package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class ExpandCapsule extends Capsule {

    protected ExpandCapsule(Point position, Room room) {
        super(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            Room room = getRoom();
            Paddle extendedPaddle = new ExpandedPaddle(other.getPosition());
            room.add(extendedPaddle);
        }
    }
}
