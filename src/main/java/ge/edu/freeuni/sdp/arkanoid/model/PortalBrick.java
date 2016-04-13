package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
/**
 * Created by elene on 4/12/16.
 */
public class PortalBrick extends NormalBrick {
    public PortalBrick(Point position, BrickColor color, Capsule capsule){
        super(position, color, capsule);
    }


    @Override
    public void interact(Gobj other){
        return;
    }


    @Override
    public boolean isKillable() {
        return false;
    }



}
