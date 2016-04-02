package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by nika on 4/2/16.
 */
public class DeathCountListener implements GobjDeathListener {

    private int _count;
    private ArrayList<LevelOverListener> _overListeners;
    private Lock _countLock;

    public DeathCountListener(int count) {
        _count = count;
        _overListeners = new ArrayList<>();
        _countLock = new ReentrantLock();
    }

    public void setLevelOverListener(LevelOverListener listener) {
        _overListeners.add(listener);
    }

    public void setCount(int count) {
        _countLock.lock();
        _count = count;
        _countLock.unlock();
    }

    public int getCount() {
        int res;
        _countLock.lock();
        res = _count;
        _countLock.unlock();
        return res;
    }

    @Override
    public void gobjDied(Gobj obj, String reason) {
        boolean notify;
        _countLock.lock();
        _count--;
        notify = (_count == 0);
        _countLock.unlock();

        if (notify)
            notifyAllLevelOver();
    }

    protected void notifyAllLevelOver() {
        _overListeners.forEach(LevelOverListener::levelOver);
    }

}
