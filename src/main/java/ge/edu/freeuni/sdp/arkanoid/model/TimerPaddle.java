package ge.edu.freeuni.sdp.arkanoid.model;


import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class TimerPaddle extends Paddle {
    private int _milisecsToLive;
    private long _startTime;
    private Capsule _capsule;

    TimerPaddle(Point position, int milisecsToLive, Capsule capsule) {
        super(position);
        _milisecsToLive = milisecsToLive;
        _startTime = System.currentTimeMillis();
        _capsule = capsule;
    }

    @Override
    public void move() {
        long timeDiff = System.currentTimeMillis() - _startTime;
        if (timeDiff > _milisecsToLive) {
            _capsule.interact(this);
        } else {
            super.move();
        }
    }

    public long getTimeLeft() {
        return System.currentTimeMillis() - _startTime;
    }
}
