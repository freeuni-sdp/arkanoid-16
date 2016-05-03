package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by nika on 5/3/16.
 */
public class StickyPaddleCapsuleTest {

    private TestableStickyPaddleCapsule capsule;
    @Mock private Room mRoom;
    private Point capsulePoint;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        capsulePoint = new Point(0, 1);
        capsule = new TestableStickyPaddleCapsule(capsulePoint, mRoom);
        capsule.setReturnPaddle(mock(Paddle.class));
    }

    @Test
    public void interact_PaddleInstance_callCreateStickyPaddle() throws Exception {
        Paddle mPaddle = mock(Paddle.class);
        capsule.interact(mPaddle);
        assertEquals(1, capsule.getNumCalls());
    }

    @Test
    public void interact_notPaddleInstance_notCallCreateStickyPaddle() throws Exception {
        Gobj mGobj = mock(Gobj.class);
        capsule.interact(mGobj);
        assertEquals(0, capsule.getNumCalls());
    }

    @Test
    public void interact_PaddleInstance_exchangePaddles() throws Exception {
        Paddle mPaddle = mock(Paddle.class);
        capsule.interact(mPaddle);
        verify(mPaddle, times(1)).exchange(capsule.getReturnPaddle());
    }

    @Test
    public void interact_notPaddleInstance_notExchangePaddles() throws Exception {
        Gobj mGobj = mock(Gobj.class);
        capsule.interact(mGobj);
        // If interact calls exchange Exception will be thrown
    }

    @Test
    public void getShape_returnCorrectShape() throws Exception {
        Rectangle shape = capsule.getShape();
        assertEquals(shape.getPosition().X, capsulePoint.X, 0);
        assertEquals(shape.getPosition().Y, capsulePoint.Y, 0);
    }

    @Test
    public void isAlive_interactPaddle_isAliveReturnFalse() throws Exception {
        Paddle mPaddle = mock(Paddle.class);
        capsule.interact(mPaddle);
        assertFalse(capsule.isAlive());
    }

    @Test
    public void isAlive_interactFrameBrick_isAliveReturnFalse() throws Exception {
        FrameBrick mBrick = mock(FrameBrick.class);
        capsule.interact(mBrick);
        assertFalse(capsule.isAlive());
    }

    @Test
    public void isAlive_interactGobj_isAliveReturnTrue() throws Exception {
        Gobj mGobj = mock(Gobj.class);
        capsule.interact(mGobj);
        assertTrue(capsule.isAlive());
    }

    @Test
    public void getPosition_constructorPoint_sameCoordinates() throws Exception {
        Point pos = capsule.getPosition();
        assertEquals(capsulePoint.X, pos.X, 0);
        assertEquals(capsulePoint.Y, pos.Y, 0);
    }

    @Test
    public void setPosition_changePosition_changeCoordinates() throws Exception {
        Point newPoint = new Point(capsulePoint.X + 1, capsulePoint.Y + 1);
        capsule.setPosition(newPoint);
        Point res = capsule.getPosition();
        assertEquals(newPoint.X, res.X, 0);
        assertEquals(newPoint.Y, res.Y, 0);
    }

    @Test
    public void getSpeed_speedNull_sameCoordinates() throws Exception {
        assertEquals(capsule.getSpeed(), Speed.NULL);
    }

    @Test
    public void setSpeed_changeSpeed_getSpeedReturnNewSpeed() throws Exception {
        Speed newSpeed = new Speed(90);
        capsule.setSpeed(newSpeed);
        Speed res = capsule.getSpeed();
        assertEquals(newSpeed, res);
    }

    @Test
    public void move_mockPositionAndSpeed_positionAddSpeedCall() throws Exception {
        Point mPoint = mock(Point.class);
        Speed mSpeed = new Speed(45);
        capsule.setPosition(mPoint);
        capsule.setSpeed(mSpeed);
        capsule.move();
        verify(mPoint, times(1)).add(mSpeed);
    }

    @Test
    public void release_mustFallAndAddToRoom() throws Exception {
        Point releasePoint = new Point(2, 2);
        capsule.release(releasePoint);

        // Assert position
        assertEquals(capsule.getPosition().X, releasePoint.X, 0);
        assertEquals(capsule.getPosition().Y, releasePoint.Y, 0);

        // Assert that it's falling
        assertEquals(capsule.getSpeed().getAngleDegrees(), 90, 0.00001);

        // Assert that it's added to room
        verify(mRoom, times(1)).add(capsule);
    }

}

class TestableStickyPaddleCapsule extends StickyPaddleCapsule {

    private Paddle returnPaddle;
    private int numCalls;

    TestableStickyPaddleCapsule(Point position, Room room) {
        super(position, room);
        returnPaddle = null;
        numCalls = 0;
    }

    public void setReturnPaddle(Paddle paddle) {
        this.returnPaddle = paddle;
    }

    public Paddle getReturnPaddle() {
        return this.returnPaddle;
    }

    public int getNumCalls() {
        return this.numCalls;
    }

    @Override
    protected Paddle createStickyPaddle(Paddle prevPaddle, Point position) {
        numCalls++;
        return returnPaddle;
    }

}