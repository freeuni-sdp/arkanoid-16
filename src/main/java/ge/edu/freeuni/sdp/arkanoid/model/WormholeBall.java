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

    private PortalBrick pairedPortal(PortalBrick portal){
        int indx = portals.indexOf(portal);
        if(indx == -1) return null;
        if(indx % 2 == 0 ) return portals.get(indx + 1);
        else return portals.get(indx - 1);
    }

    @Override
    public void interact(Gobj other) {

        if(other instanceof PortalBrick){
            //if (other.equals(_last)) { System.out.print(" same "); return;}
            PortalBrick pair = pairedPortal((PortalBrick)other);
            if(getSpeed().Y > 0)
                setPosition(new Point(pair.getPosition().X, pair.getPosition().Y - pair.getShape().getSize().getHeight()));
            else
                setPosition(new Point(pair.getPosition().X, pair.getPosition().Y + pair.getShape().getSize().getHeight()));
            _last = pair;
            System.out.print(" a ");
        }
        else {
            _last = null;
            super.interact(other);
        }

    }
}
