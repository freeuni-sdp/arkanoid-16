package ge.edu.freeuni.sdp.arkanoid.model.geometry;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by GM on 5/3/2016.
 */
public class SpeedTest {

    private double DEFAULT_LENGTH = 0.15;

    @Test
    public void singleIntegerConstructor(){
        Speed spd = new Speed(15);
        assertEquals(15, spd.getAngleDegrees(), 0.001);
        assertEquals(DEFAULT_LENGTH, spd.getLength(), 0.001);
    }

    @Test
    public void pointConstructor(){
        Point pt = new Point(2.0, 3.0);
        Speed spd = new Speed(pt);
        assertEquals(2.0, spd.X, 0.001);
        assertEquals(3.0, spd.Y, 0.001);
    }

    @Test
    public void mirrorX(){
        Speed spd = new Speed(45);
        Speed xMirror = spd.mirrorX();
        assertEquals(spd.X, -xMirror.X, 0.001);
        assertEquals(spd.Y, xMirror.Y, 0.001);
    }

    @Test
    public void mirrorY(){
        Speed spd = new Speed(45);
        Speed yMirror = spd.mirrorY();
        assertEquals(spd.X, yMirror.X, 0.001);
        assertEquals(spd.Y, -yMirror.Y, 0.001);
    }

    @Test
    public void setAngle45(){
        int ang = 45;
        Speed spd = new Speed(ang);
        double length = spd.getLength();
        double first_angle = Math.toRadians(ang);
        double x = Math.cos(first_angle) * length;
        double y = Math.sin(first_angle) * length;
        double angle = Math.atan2(y, x);
        x = Math.cos(angle) * DEFAULT_LENGTH;
        y = Math.sin(angle) * DEFAULT_LENGTH;
        assertEquals(x, spd.X, 0.001);
        assertEquals(y, spd.Y, 0.001);
    }

    @Test
    public void setAngle0(){
        int ang = 0;
        Speed spd = new Speed(ang);
        double length = spd.getLength();
        double first_angle = Math.toRadians(ang);
        double x = Math.cos(first_angle) * length;
        double y = Math.sin(first_angle) * length;
        double angle = Math.atan2(y, x);
        x = Math.cos(angle) * DEFAULT_LENGTH;
        y = Math.sin(angle) * DEFAULT_LENGTH;
        assertEquals(x, spd.X, 0.001);
        assertEquals(y, spd.Y, 0.001);
    }

    @Test
    public void setAngle90(){
        int ang = 45;
        Speed spd = new Speed(ang);
        double length = spd.getLength();
        double first_angle = Math.toRadians(ang);
        double x = Math.cos(first_angle) * length;
        double y = Math.sin(first_angle) * length;
        double angle = Math.atan2(y, x);
        x = Math.cos(angle) * DEFAULT_LENGTH;
        y = Math.sin(angle) * DEFAULT_LENGTH;
        assertEquals(x, spd.X, 0.001);
        assertEquals(y, spd.Y, 0.001);
    }

    @Test
    public void setAngle90Negative(){
        int ang = -90;
        Speed spd = new Speed(ang);
        double length = spd.getLength();
        double first_angle = Math.toRadians(ang);
        double x = Math.cos(first_angle) * length;
        double y = Math.sin(first_angle) * length;
        double angle = Math.atan2(y, x);
        x = Math.cos(angle) * DEFAULT_LENGTH;
        y = Math.sin(angle) * DEFAULT_LENGTH;
        assertEquals(x, spd.X, 0.001);
        assertEquals(y, spd.Y, 0.001);
    }

    @Test
    public void setAngle30Negative(){
        int ang = -30;
        Speed spd = new Speed(ang);
        double length = spd.getLength();
        double first_angle = Math.toRadians(ang);
        double x = Math.cos(first_angle) * length;
        double y = Math.sin(first_angle) * length;
        double angle = Math.atan2(y, x);
        x = Math.cos(angle) * DEFAULT_LENGTH;
        y = Math.sin(angle) * DEFAULT_LENGTH;
        assertEquals(x, spd.X, 0.001);
        assertEquals(y, spd.Y, 0.001);
    }

}
