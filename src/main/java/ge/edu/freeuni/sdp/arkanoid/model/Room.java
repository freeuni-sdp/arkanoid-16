package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

class Room {

    private final Set<Gobj> _gobjs;
    private int _killableLeft;
    private boolean _breakCapsuleTaken;
    private LiveCounter _liveCounter = null;

    Room() {
        _gobjs = new HashSet<>();
        _killableLeft = 0;
        _breakCapsuleTaken = false;
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

        zombies.stream().filter(Gobj::isKillable).forEach(zomby -> _killableLeft--);

        _gobjs.removeAll(zombies);
    }

    Set<Gobj> getGobjs() {
        return _gobjs;
    }

    public boolean areKillablesLeft() {
        return _killableLeft <= 0 || _breakCapsuleTaken;
    }

    public void add(Gobj gobj) {
        if (gobj.isKillable())
            _killableLeft++;
        _gobjs.add(gobj);
    }

    public void breakCapsuleIsTaken() {
        _breakCapsuleTaken = true;
    }

    public void setLiveCounter(LiveCounter liveCounter){
        this._liveCounter = liveCounter;
    }

    public LiveCounter getLiveCounter(){
        return this._liveCounter;
    }

    public int getLives(){
        return this._liveCounter.getLive();
    }
}
