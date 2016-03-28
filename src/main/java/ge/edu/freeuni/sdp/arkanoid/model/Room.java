package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {

    private final Set<Gobj> _gobjs;

    public Room() {
        _gobjs = new HashSet<Gobj>();
    }

    public void move() {
        for (Gobj current : _gobjs) {
            current.move();
        }
    }

    public void interact() {
        for (Gobj current : _gobjs) {
            for (Gobj other : _gobjs) {
                if (current == other) continue;
                boolean hasOverlap = current.getShape().hasOverlap(other.getShape());
                if (hasOverlap) current.interact(other);
            }
        }
    }

    public void removeZombies() {
        List<Gobj> zombies = new ArrayList<Gobj>();
        for (Gobj candidate : getGobjs()) {
            if (!candidate.isAlive()) zombies.add(candidate);
        }
        _gobjs.removeAll(zombies);
    }

    public Set<Gobj> getGobjs() {
        return _gobjs;
    }

    public void add(Gobj gobj) {
        _gobjs.add(gobj);
    }
}
