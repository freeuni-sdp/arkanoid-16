package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Circle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by Tornike on 12.04.2016.
 */
public class CornerDeflectingPaddle extends Paddle {
    private final int additionalDegrees = 30;
    private final double cornerPart = .2;
    public CornerDeflectingPaddle(Size roomSize) {
        super(roomSize);
    }
    @Override
    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Ball) {
            Ball ball = (Ball) other;
            int direction = getCollisionId(ball, this);

            if (direction != 0) {
                Speed oldSpeed = ball.getSpeed();
                double oldAngle = oldSpeed.getAngleDegrees();
                double newAngle = oldAngle + direction * additionalDegrees;
                Speed newSpeed = new Speed((int)newAngle);
                newSpeed.setLength(oldSpeed.getLength());
                ball.setSpeed(newSpeed);
            }
        }
    }

    private int getCollisionId(Ball ball, Paddle paddle){
        double ballPosition = ball.getPosition().X;
        double left = paddle.getPosition().X;
        double size = paddle.getShape().getSize().getWidth();
        double right = left + size;

        if ((ballPosition - left)/size <= cornerPart){
            return -1;
        }

        if ((right - ballPosition)/size <= cornerPart){
            return 1;
        }

        return 0;
    }
}
