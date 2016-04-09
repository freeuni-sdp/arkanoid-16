package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

class Room {

    private final Set<Gobj> _gobjs;
    private int _killableLeft;

    Room() {
        _gobjs = new HashSet<>();
        _killableLeft = 0;
    }

    void move() {
        Set<Gobj> cgobjs = new HashSet<>(_gobjs);
        cgobjs.forEach(Gobj::move);
    }

    void interact() {
        Gobj[] snapshot = new Gobj[_gobjs.size()];
        _gobjs.toArray(snapshot);
        boolean current_alive, other_alive;

        for (Gobj current : snapshot) {
            for (Gobj other : snapshot) {
                if (current == other) continue;
                boolean hasOverlap = current.getShape().canOverlap(other.getShape());
                if (hasOverlap) {
                    current_alive = current.isAlive();
                    other_alive = other.isAlive();
                    current.interact(other);
                    if (current.isKillable() && current_alive && !current.isAlive())
                        _killableLeft--;
                    if (other.isKillable() && other_alive && !other.isAlive())
                        _killableLeft--;
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

    public boolean isLevelCleared() {
        return _killableLeft <= 0;
    }

    public void add(Gobj gobj) {
        if (gobj.isKillable())
            _killableLeft++;
        _gobjs.add(gobj);
    }
}
