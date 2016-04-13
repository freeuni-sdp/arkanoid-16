package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by dell on 4/12/16.
 */
public class StickyPaddleCapsule extends Capsule{

    StickyPaddleCapsule(Point position, Room room) {
        super(position, room);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new StickyPaddleCapsule(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);

        if (other instanceof Paddle) {
            Paddle prevPaddle = (Paddle) other;
            Paddle stickyPaddle = new StickyPaddle(_room ,prevPaddle, other.getPosition());
            prevPaddle.exchange(stickyPaddle);
        }
    }
}
