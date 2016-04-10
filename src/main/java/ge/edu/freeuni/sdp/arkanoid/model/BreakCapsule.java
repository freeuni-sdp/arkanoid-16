package ge.edu.freeuni.sdp.arkanoid.model;

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
            KillerBrick killer = getKillerBrick();
            if (killer != null)
                _room.getGobjs().forEach(gobj -> gobj.interact(killer));
        }
    }

    private KillerBrick getKillerBrick(){
        for (Gobj obj:  _room.getGobjs()){
           if (obj instanceof KillerBrick){
                return (KillerBrick) obj;
            }
        }
        return null; //TODO throw noKillerBrickException or get killerBrick
        //TODO another way
    }

}
