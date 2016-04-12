package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created by rezo on 4/10/16.
 */
public class ExtraLiveCapsule extends Capsule {

    static {
        CapsuleFactory.getInstance().registerCapsuleType(CapsuleType.ExtraLive, new ExtraLiveCapsule());
    }

    private Room _room;

    private ExtraLiveCapsule() {
        super(null, null);
    }

    ExtraLiveCapsule(Point position, Room room) {
        super(position, room);
        this._room = room;
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new ExtraLiveCapsule(position, room);
    }

    @Override
    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle){
            _room.getLiveCounter().increase();
        }
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Capsule;
    }
}
