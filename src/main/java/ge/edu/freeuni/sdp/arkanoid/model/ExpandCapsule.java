package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class ExpandCapsule extends Capsule {

    static {
        CapsuleFactory.getInstance().registerCapsuleType(CapsuleType.Expand, new ExpandCapsule());
    }

    private ExpandCapsule() {
        super(null, null);
    }

    ExpandCapsule(Point position, Room room) {
        super(position, room);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new ExpandCapsule(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            Paddle oldPaddle = (Paddle) other;
            Paddle newPaddle = new ExpandedPaddle(other.getPosition());
            oldPaddle.exchange(newPaddle);
            SoundPlayer.getInstance().play(SoundPlayer.EXTEND);
        }
    }
}
