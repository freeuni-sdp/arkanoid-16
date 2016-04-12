package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;


/**
 * Created by Giorgi on 4/10/2016.
 */
public class BreakCapsule extends Capsule{

    static {
        CapsuleFactory.getInstance().registerCapsuleType(CapsuleType.Break, new BreakCapsule());
    }

    private BreakCapsule() {
        super(null, null);
    }

    BreakCapsule(Point point, Room room){
        super(point, room);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new BreakCapsule(position, room);
    }

    @Override
    public void interact(Gobj other){
        super.interact(other);

        if (other instanceof Paddle){

            KillerBrick killer = new KillerBrick(new Point(0, 0),
                    new Size(0, 0), _room);
            _room.getGobjs().stream()
                    .filter(Gobj::isKillable)
                    .forEach(gobj -> gobj.interact(killer));
    }
    }
}
