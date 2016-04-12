package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class LaserCapsule extends Capsule {
    LaserCapsule(Point point, Room room) {
        super(point, room);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new LaserCapsule(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            //Todo paddle may fire
            Point paddlePosition = other.getPosition();
            Beam beam = new Beam(paddlePosition, _room);
            _room.add(beam);
        }
    }
}
