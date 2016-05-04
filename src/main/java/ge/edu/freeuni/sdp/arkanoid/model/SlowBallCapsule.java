package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by nino on 12.04.2016.
 */
public class SlowBallCapsule extends Capsule {
    private PaddleFactory _paddleFactory;

    SlowBallCapsule(Point position, Room room) {
        super(position, room);
        _paddleFactory = PaddleFactory.getInstance();
    }

    // added injecting constructor for testing
    public SlowBallCapsule(Point position, Room room, PaddleFactory paddleFactory){
        super(position, room);
        _paddleFactory = paddleFactory;
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new SlowBallCapsule(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);

        if (other instanceof Paddle) {

            Paddle oldPaddle = (Paddle) other;
            Paddle newPaddle = _paddleFactory.createBallSpeedRestoringTimerPaddle(getPosition(), _room, oldPaddle);
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
