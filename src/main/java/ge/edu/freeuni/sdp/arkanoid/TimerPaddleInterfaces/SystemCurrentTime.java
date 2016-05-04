package ge.edu.freeuni.sdp.arkanoid.TimerPaddleInterfaces;

/**
 * Created by khrak on 5/3/16.
 */
public class SystemCurrentTime implements ICurrentTime{
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
