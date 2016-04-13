package ge.edu.freeuni.sdp.arkanoid.model;
import java.util.ArrayList;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
/**
 * Created by elene on 4/12/16.
 */
public class WormholeBall extends Ball {

    private ArrayList<PortalBrick> portals;
    private PortalBrick _last;

    public WormholeBall(ArrayList<PortalBrick> portals){
        super();
        this.portals = portals;
        _last = null;
    }
    //the portals are stored in the arraylist, each nth portal(where n is even) is connected with n+1-th portal
    private PortalBrick pairedPortal(PortalBrick portal){
        int indx = portals.indexOf(portal);
        if(indx == -1) return null;
        if(indx % 2 == 0 ) return portals.get(indx + 1);
        else return portals.get(indx - 1);
    }

    @Override
    public void interact(Gobj other) {
        if(other instanceof PortalBrick){
            if (other.equals(_last)) {
                return;
            }
            PortalBrick pair = pairedPortal((PortalBrick)other);
            setPosition(new Point(pair.getPosition().X, pair.getPosition().Y));
            _last = pair;
        }
        else {
            _last = null;
            super.interact(other);
        }

    }
}
