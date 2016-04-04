package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

class Room {

    private final Set<Gobj> _gobjs;

    Room() {
        _gobjs = new HashSet<>();
    }

    void move() {
        Set<Gobj> cgobjs = new HashSet<>(_gobjs);
        cgobjs.forEach(Gobj::move);
    }

    void interact() {
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

    void removeZombies() {
        Stack<Gobj> zombies =
                getGobjs()
                    .stream()
                    .filter(candidate -> !candidate.isAlive())
                    .collect(Collectors.toCollection(Stack::new));
        
        _gobjs.removeAll(zombies);
    }

    Set<Gobj> getGobjs() {
        return _gobjs;
    }

    public void add(Gobj gobj) {
        _gobjs.add(gobj);
    }
}
