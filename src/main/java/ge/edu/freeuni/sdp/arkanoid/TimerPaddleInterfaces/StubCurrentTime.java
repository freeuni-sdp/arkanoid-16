package ge.edu.freeuni.sdp.arkanoid.TimerPaddleInterfaces;

/**
 * Created by khrak on 5/3/16.
 */
public class StubCurrentTime implements ICurrentTime {

    private long time = 0;
    private long incrementValue;

    public StubCurrentTime(long incrementValue) {
        this.incrementValue = incrementValue;
    }

    @Override
    public long currentTimeMillis() {
        long prevTime = time;
        time += incrementValue;
        return prevTime;
    }
}
