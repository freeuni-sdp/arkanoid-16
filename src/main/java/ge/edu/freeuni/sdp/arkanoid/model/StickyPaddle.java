package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by dell on 4/12/16.
 */
public class StickyPaddle extends Paddle {
    private Paddle prevPaddle;
    private StickedBall stickedBall;

    StickyPaddle(Paddle prevPaddle, Point position) {
        super(position);
        this.prevPaddle = prevPaddle;
        stickedBall = null;
    }

    @Override
    public void interact(Gobj other) {
        if ((other instanceof Ball) && stickedBall == null) {
            Ball ball = (Ball) other;
            stickedBall = new StickedBall(ball, ball.getSpeed());
            ball.setSpeed(Speed.NULL);
        } else {
            super.interact(other);
        }
    }

    @Override
    public void move() {
        super.move();
        Point currPoint = getPosition();
        if (stickedBall != null)
            stickedBall.getBall().setPosition(new Point(currPoint.X + 1, currPoint.Y - 1));
    }

    @Override
    void fire(Room room) {
        super.fire(room);
        if (stickedBall != null) {
            stickedBall.getBall().setSpeed(stickedBall.getSpeed().mirrorY());
            stickedBall = null;
        }
        prevPaddle.setAlive(true);
        room.add(prevPaddle);
        this.exchange(prevPaddle);
    }

    private class StickedBall {
        Ball ball;
        Speed speed;

        StickedBall(Ball ball, Speed speed) {
            this.ball = ball;
            this.speed = speed;
        }

        Speed getSpeed() {
            return speed;
        }

        Ball getBall() {
            return ball;
        }
    }
}
