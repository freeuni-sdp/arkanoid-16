package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created by rezo on 4/10/16.
 */
public class ExtraLiveCapsule extends Capsule {
    private Room _room;
    ExtraLiveCapsule(Point position, Room room) {
        super(position, room);
        this._room = room;
    }

    @Override
    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle){
            _room.getLiveCounter().incScore(1);
        }
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Capsule;
    }
}
