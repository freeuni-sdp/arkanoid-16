package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kaneki on 5/4/16.
 */
public class SpeedingBallTest {


    @Test
    public void test_interact1(){
//    Testing if the SpeedingBall changes speed
//      Create a bick
        Point position = new Point(-1, -1);
        NormalBrick brick = new NormalBrick(position, BrickColor.Blue, new NullCapsule(position));
//      Create Speeding Ball
        SpeedingBall ball = new SpeedingBall(1.0,new Point(0,0));
        ball.setPosition(new Point(4,4));
        ball.setSpeed(new Speed(new Point(0,-1)));

//      Interact 10 times
        for (int k = 0; k < 10; k++) {
            Speed sp = ball.getSpeed();
            ball.interact(brick);
        }
        Speed speed = ball.getSpeed();

        assertEquals(speed.getLength(), 2, 0.1);

    }

    @Test
    public void test_interact2(){
//  Testing if the SpeedingBall doesn't change speed
//      Create a bick
        Point position = new Point(-1, -1);
        Brick brick = new FrameBrick(new Point(0,0),new Size(5,5));
//      Create Speeding Ball
        SpeedingBall ball = new SpeedingBall(1.0,new Point(0,0));
        ball.setPosition(new Point(4,4));
        ball.setSpeed(new Speed(new Point(0,-1)));

//      Interact 10 times
        for (int k = 0; k < 10; k++) {
            Speed sp = ball.getSpeed();
            ball.interact(brick);
        }
        Speed speed = ball.getSpeed();

        assertEquals(speed.getLength(), 1, 0.1);

    }

    @Test
    public void test_interact3(){
// Testing if the speeding ball changes speed to the MAXSPEED
//      Create a bick
        Point position = new Point(-1, -1);
        NormalBrick brick = new NormalBrick(position, BrickColor.Blue, new NullCapsule(position));
//      Create Speeding Ball
        SpeedingBall ball = new SpeedingBall(1.0,new Point(0,0));
        ball.setPosition(new Point(4,4));
        ball.setSpeed(new Speed(new Point(0, -5)));

//      Interact 10 times
        for (int k = 0; k < 10; k++) {
            Speed sp = ball.getSpeed();
            ball.interact(brick);
        }
        Speed speed = ball.getSpeed();

        assertEquals(speed.getLength(), 2.1, 0.1);

    }


}
