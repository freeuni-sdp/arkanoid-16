package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.ArrayList;

/**
 * Created by nika on 4/2/16.
 */
public class DeathCountListener implements GobjDeathListener {

    private int _count;
    private ArrayList<LevelClearedListener> _overListeners;

    public DeathCountListener(int count) {
        _count = count;
        _overListeners = new ArrayList<>();
    }

    public void setLevelOverListener(LevelClearedListener listener) {
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
            notifyAllLevelCleared();
    }

    protected void notifyAllLevelCleared() {
        _overListeners.forEach(LevelClearedListener::levelCleared);
    }

}
