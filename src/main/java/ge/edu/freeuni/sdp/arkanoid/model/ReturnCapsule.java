package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class ReturnCapsule extends Capsule {
    private Paddle _oldPaddle;
    private double _ballSpeed;

    ReturnCapsule(Point position, Room room, Paddle oldPaddle, double ballSpeed) {
        super(position, room);
        _oldPaddle = oldPaddle;
        _ballSpeed = ballSpeed;
    }

    ReturnCapsule(Point position, Room room) {
        super(position, room);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new ReturnCapsule(position, room);
    }

    @Override
    public void interact(Gobj other) {
        if (other instanceof Paddle) {
            Paddle activePaddle = (Paddle)other;

            _oldPaddle.setAlive(true);
            _room.add(_oldPaddle);

            activePaddle.exchange(_oldPaddle);
            _room.getGobjs()
                    .stream()
                    .filter(obj -> obj instanceof Ball)
                    .forEach(obj -> {
                        Ball ball = (Ball)obj;
                        Speed ballSpeed = ball.getSpeed();
                        ballSpeed.setLength(ballSpeed.getLength() * _ballSpeed);
                    });
            SoundPlayer.getInstance().play(SoundPlayer.AUTOPILOT_EXPIRE);
        }
        super.interact(other);
    }

    public void setOldPaddle(Paddle oldPaddle) {
        _oldPaddle = oldPaddle;
    }

    public void setBallSpeed(double ballSpeed) {
        _ballSpeed = ballSpeed;
    }
}
