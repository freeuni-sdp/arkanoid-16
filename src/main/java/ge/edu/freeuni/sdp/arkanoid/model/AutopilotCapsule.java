package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class AutopilotCapsule extends Capsule {
    AutopilotCapsule(Point point, Room room) {
        super(point, room);
    }

    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            Paddle oldPaddle = (Paddle) other;
            Capsule returnCapsule = new ReturnCapsule(getPosition(), _room, oldPaddle);
            Paddle newPaddle = new AutopilotPaddle(other.getPosition(), 5000, returnCapsule, _room);
            oldPaddle.exchange(newPaddle);

            //double speed of ball
            _room.getGobjs()
                    .stream()
                    .filter(obj -> obj instanceof Ball)
                    .forEach(obj -> {
                Ball ball = (Ball) obj;
                Speed ballSpeed = ball.getSpeed();
                ball.setSpeed(new Speed(new Point(2 * ballSpeed.X, 2 * ballSpeed.Y)));
            });
        }
    }
}
