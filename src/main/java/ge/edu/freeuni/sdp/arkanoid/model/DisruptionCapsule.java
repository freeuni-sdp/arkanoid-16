package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

import java.util.ArrayList;

/**
 * Created by mi.maghriani on 4/12/2016.
 */
public class DisruptionCapsule extends Capsule {


    DisruptionCapsule (Point position, Room room) {
        super(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            ArrayList<Ball> balls = _room.getBalls();
            if (balls.size() > 0) {
                if (balls.size() == 1) {
                    Point position = balls.get(0).getPosition();
                    balls.get(0).increaseNumBalls();
                    Ball newBall1  =  new Ball();
                    Ball newBall2  =  new Ball();
                    newBall1.setPosition(position);
                    newBall1.setSpeed(new Speed(-60));
                    newBall2.setPosition(position);
                    newBall2.setSpeed(new Speed(210));
                    balls.get(0).addBall(newBall1);
                    balls.get(0).addBall(newBall2);

                }
            }
            SoundPlayer.getInstance().play(SoundPlayer.BOUNCE);
        }
    }
}
