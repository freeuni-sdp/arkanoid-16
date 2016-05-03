package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by nika on 5/3/16.
 */
public class StickyPaddleCapsuleTest {

    private TestableStickyPaddleCapsule capsule;
    @Mock private Point mPoint;
    @Mock private Room mRoom;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        capsule = new TestableStickyPaddleCapsule(mPoint, mRoom);
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