package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.Capsule;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created by Giorgi on 4/10/2016.
 */
public class BreakCapsule extends Capsule{

    BreakCapsule(Point point, Room room){
        super(point, room);

    }

    @Override
    public void interact(Gobj other){
        super.interact(other);
        if (other instanceof Paddle){
            _room.breakCapsuleIsTaken();
        }
    }

}
