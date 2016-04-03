package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nika on 4/2/16.
 */
public class DeathCountListener implements GobjDeathListener {

    private int _count;
    private ArrayList<LevelOverListener> _overListeners;

    public DeathCountListener(int count) {
        _count = count;
        _overListeners = new ArrayList<>();
    }

    public void setLevelOverListener(LevelOverListener listener) {
        _overListeners.add(listener);
    }

    public void setCount(int count) {
        _count = count;
    }

    public int getCount() {
        return _count;
    }

    @Override
    public void gobjDied(Gobj obj, String reason) {
        if (--_count == 0)
            notifyAllLevelOver();
    }

    protected void notifyAllLevelOver() {
        _overListeners.forEach(LevelOverListener::levelOver);
    }

}
