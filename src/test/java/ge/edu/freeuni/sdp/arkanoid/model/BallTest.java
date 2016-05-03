package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BallTest {

    @Test
    public void testGetShape() throws Exception {

    }

    @Test
    public void testGetKind() throws Exception {
        Ball  ball = new Ball();
        ball.getSpeed();
    }

    @Test
    public void testInteract() throws Exception {
        Brick brick = new FrameBrick(new Point(0,0),new Size(5,5));
        Ball ball = new Ball(1.0,new Point(0,0));
        ball.setPosition(new Point(4,4));
        ball.setSpeed(new Speed(new Point(-2,0)));

        ball.interact(brick);

        Speed expected = new Speed(new Point(2, 0));
        Speed speed = ball.getSpeed();
        double x = expected.getX();
        double y = expected.getY();
        double x1 = speed.getX();
        double y1 = speed.getY();


        //System.out.println(speed.getX());
        assertEquals(y, y1,0.1);
        assertEquals(x, x1,0.1);


    }

    @Test
    public void testInteractTop() throws Exception {
        Brick brick = new FrameBrick(new Point(0,0),new Size(5,5));
        Ball ball = new Ball(1.0,new Point(0,0));
        ball.setPosition(new Point(4,4));
        ball.setSpeed(new Speed(new Point(0,-5)));

        ball.interact(brick);

        Speed expected = new Speed(new Point(0, 5));
        Speed speed = ball.getSpeed();
        double x = expected.getX();
        double y = expected.getY();
        double x1 = speed.getX();
        double y1 = speed.getY();

        assertEquals(y, y1,0.1);
        assertEquals(x, x1,0.1);


    }


    @Test
    public void    testSpeed(){
        Ball ball = new Ball();
        ball.setSpeed(new Speed(new Point(0,1)));
        Speed speed = new Speed(new Point(0, 1));
        double x = ball.getSpeed().getX();
        double x1 = speed.getX();
        assertEquals(x1,x,0);

    }
    @Test
    public void testMove() throws Exception {

    }

    @Test
    public void testPutOn() throws Exception {

    }
}