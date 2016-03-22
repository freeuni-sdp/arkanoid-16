package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.HashSet;
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
                Set<Point> intersection = other.getOccupied();
                intersection.retainAll(current.getOccupied());
                if (!intersection.isEmpty()) {
                    current.interactAt(other, intersection);
                }
            }
        }
    }

    public void removeZombies() {

    }

    public Set<Gobj> getGobjs() {
        return _gobjs;
    }

    public void add(Gobj gobj) {
        _gobjs.add(gobj);
    }
}
