package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created by Nikoloz on 04/12/16.
 */
public class KillCapsule extends Capsule {

    KillCapsule(Point position, Room room) {
        super(position, room);
    }

    @Override
    public void interact(Gobj other) {
        super.interact(other);

        if (other instanceof Paddle){
            ((Paddle)other).lifeLost();
        }
    }
}
