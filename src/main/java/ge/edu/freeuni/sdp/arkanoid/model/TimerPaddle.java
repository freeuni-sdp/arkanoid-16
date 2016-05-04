package ge.edu.freeuni.sdp.arkanoid.model;


import ge.edu.freeuni.sdp.arkanoid.TimerPaddleInterfaces.ICurrentTime;
import ge.edu.freeuni.sdp.arkanoid.TimerPaddleInterfaces.SystemCurrentTime;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */

public class TimerPaddle extends Paddle {
    private int _milisecsToLive;
    private long _startTime;
    private Capsule _capsule;
    private ICurrentTime timer = new SystemCurrentTime();

    TimerPaddle(Point position, int milisecsToLive, Capsule capsule) {
        super(position);
        _milisecsToLive = milisecsToLive;
        _startTime = timer.currentTimeMillis();
        _capsule = capsule;
    }

    TimerPaddle(Point position, int milisecsToLive, Capsule capsule, ICurrentTime timer) {
        super(position);
        this.timer = timer;
        _milisecsToLive = milisecsToLive;
        _startTime = timer.currentTimeMillis();
        _capsule = capsule;
    }


    @Override
    public void move() {
        long timeDiff = timer.currentTimeMillis() - _startTime;
        if (timeDiff > _milisecsToLive) {
            _capsule.interact(this);
        } else {
            super.move();
        }
    }

    public long getTimeLeft() {
        return timer.currentTimeMillis() - _startTime;
    }
}
