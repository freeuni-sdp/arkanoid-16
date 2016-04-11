package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class ReturnCapsule extends Capsule {
    private Paddle _oldPaddle;

    ReturnCapsule(Point position, Room room, Paddle oldPaddle) {
        super(position, room);
        _oldPaddle = oldPaddle;
    }

    @Override
    public void interact(Gobj other) {
        if (other instanceof Paddle) {
            Paddle activePaddle = (Paddle) other;

            _oldPaddle.setAlive(true);
            _room.add(_oldPaddle);

            activePaddle.exchange(_oldPaddle);

            _room.getGobjs()
                    .stream()
                    .filter(obj -> obj instanceof Ball)
                    .forEach(obj -> {
                        Ball ball = (Ball) obj;
                        Speed ballSpeed = ball.getSpeed();
                        ballSpeed.setLength(ballSpeed.getLength()/2);
                    });
            SoundPlayer.getInstance().play(SoundPlayer.AUTOPILOT_EXPIRE);
        }
        super.interact(other);
    }
}
