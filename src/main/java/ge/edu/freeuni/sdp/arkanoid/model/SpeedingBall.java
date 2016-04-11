package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by Koba on 11/04/2016.
 */
public class SpeedingBall extends Ball {

    private int BRICK_COLLISION_COUNTER = 0;

    private final int SPEED_BOOST_THRESHOLD = 10;

    private final int BALL_SPEED_MULTIPLIER = 2;

    @Override
    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Brick && !(other instanceof FrameBrick)) {
            BRICK_COLLISION_COUNTER ++;
            if (BRICK_COLLISION_COUNTER == SPEED_BOOST_THRESHOLD) {
                BRICK_COLLISION_COUNTER = 0;
                Speed ballSpeed = this.getSpeed();
                ballSpeed.setLength(BALL_SPEED_MULTIPLIER*ballSpeed.getLength());
                this.setSpeed(ballSpeed);
            }
        }
    }
}
