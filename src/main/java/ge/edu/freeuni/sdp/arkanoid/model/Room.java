package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {

    private final Set<Gobj> _gobjs;

    public Room() {
        _gobjs = new HashSet<>();
    }

    public void move() {
        _gobjs.forEach(Gobj::move);
    }

    public void interact() {
        Gobj[] snapshot = new Gobj[_gobjs.size()];
        _gobjs.toArray(snapshot);

        for (Gobj current : snapshot) {
            for (Gobj other : snapshot) {
                if (current == other) continue;
                boolean hasOverlap = current.getShape().canOverlap(other.getShape());
                if (hasOverlap) {
                    current.interact(other);
                }
            }
        }
    }

    public void removeZombies() {
        List<Gobj> zombies = new ArrayList<>();
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
