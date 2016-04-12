package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by nino on 12.04.2016.
 */
public class SlowBallCapsule extends Capsule {

    static {
        CapsuleFactory.getInstance().registerCapsuleType(CapsuleType.SlowBall, new SlowBallCapsule());
    }

    private SlowBallCapsule() {
        super(null, null);
    }

    SlowBallCapsule(Point position, Room room) {
        super(position, room);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new SlowBallCapsule(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);

        if (other instanceof Paddle) {

            Paddle oldPaddle = (Paddle) other;
            Capsule returnCapsule = new ReturnCapsule(getPosition(), _room, oldPaddle, 2);
            Paddle newPaddle = new TimerPaddle(other.getPosition(), 5000, returnCapsule);
            oldPaddle.exchange(newPaddle);

            //slow down ball
            _room.getGobjs().stream().filter(obj -> obj instanceof Ball).forEach(obj -> {
                Ball ball = (Ball) obj;
                Speed ballSpeed = ball.getSpeed();
                ballSpeed.setLength(ballSpeed.getLength()/2);
                ball.setSpeed(ballSpeed);
            });
        }
    }
}
