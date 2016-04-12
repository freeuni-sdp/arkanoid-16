package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

class Room {

    private final Set<Gobj> _gobjs;
    private int _killableLeft;
    private LiveCounter _liveCounter = null;

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

        _killableLeft -= zombies.stream().filter(Gobj::isKillable).count();

        _gobjs.removeAll(zombies);
    }

    Set<Gobj> getGobjs() {
        return _gobjs;
    }

    public boolean areKillablesLeft() {
        return _killableLeft <= 0;
    }

    public void add(Gobj gobj) {
        if (gobj.isKillable())
            _killableLeft++;
        _gobjs.add(gobj);
    }

    /*public ArrayList<Ball> getBalls() {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        for (Gobj obj : _gobjs) {
            if (obj instanceof Ball) {
                balls.add((Ball) obj);
            }
        }
        return balls;
    }*/


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
