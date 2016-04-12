package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by nino on 12.04.2016.
 */
public class SlowBallCapsule extends Capsule {

    SlowBallCapsule(Point position, Room room) {
        super(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);

        if (other instanceof Paddle) {
            //slow down ball
            _room.getGobjs()
            .stream()
            .filter(obj -> obj instanceof Ball)
            .forEach(obj -> {
                Ball ball = (Ball) obj;
                Speed ballSpeed = ball.getSpeed();
                ballSpeed.setLength(ballSpeed.getLength()/2);
                ball.setSpeed(ballSpeed);
            });
        }
    }
}
