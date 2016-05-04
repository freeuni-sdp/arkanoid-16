package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class StickyPaddleTest {
    @Mock Paddle paddle;
    @Mock Point position;
    @Mock SoundPlayer soundPlayer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        SoundPlayer.setSoundPlayer(soundPlayer);
    }

    @Test
    public void testGetShape_RectanglePositionOriginSizeFiveOnOne() {
        Point pointOrigin = new Point(0, 0);
        Paddle target = new StickyPaddle(paddle, pointOrigin);

        assertThat(target.getShape(), is(equalTo(new Rectangle(pointOrigin, new Size(5, 1)))));
    }

    @Test
    public void testGetKing_isPaddle() {
        Paddle target = new StickyPaddle(paddle, position);

        assertEquals(target.getKind(), GobjKind.Paddle);
    }

    @Test
    public void testInteract_withNullObject() {
        Paddle target = new StickyPaddle(paddle, position);
        target.interact(null);
    }

    @Test
    public void testInteract_withBallNoStickedBallOnPaddle() {
        Ball ball = mock(Ball.class);
        Paddle target = new StickyPaddle(paddle, position);
        target.interact(ball);

        assertThat(target.isAlive(), is(true));
        verify(ball).setSpeed(Speed.NULL);
    }

    @Test
    public void testInteract_withBallStickedBallOnPaddle() {
        Ball ball = mock(Ball.class);
        when(ball.getSpeed()).thenReturn(Direction.DOWN.toSpeed());
        Paddle target = new StickyPaddle(paddle, position);
        target.interact(ball);
        target.interact(ball);

        assertThat(target.isAlive(), is(true));
        verify(ball, times(1)).setSpeed(Speed.NULL);
    }

    @Test
    public void testInteract_withFrameBrick() {
        Size size = mock(Size.class);
        Point originPoint = new Point(0, 0);
        FrameBrick frameBrick = new FrameBrick(position, size);
        Paddle target = new StickyPaddle(paddle, originPoint);
        target.interact(frameBrick);

        assertThat(target.getPosition(), is(equalTo(originPoint)));
    }

    @Test
    public void testInteract_withFrameBrickWithStickedBall() {
        Size size = mock(Size.class);
        Point startPoint = new Point(0, 0);
        Speed speed = Direction.LEFT.toSpeed();
        Ball ball = mock(Ball.class);
        FrameBrick frameBrick = new FrameBrick(position, size);
        Paddle target = new StickyPaddle(paddle, startPoint);
        target.interact(ball);
        target.setSpeed(speed);
        target.move();
        target.interact(frameBrick);

        assertThat(target.getPosition(), is(equalTo(startPoint)));
        verify(ball).setPosition(new Point(startPoint.X + 1, startPoint.Y - 1));
    }

    @Test
    public void testMove_withoutStickedBall() {
        Speed speed = Direction.RIGHT.toSpeed();
        Point startPoint = new Point(0, 0);
        Point endPoint = startPoint.add(speed);
        Paddle target = new StickyPaddle(paddle, startPoint);
        target.setSpeed(speed);
        target.move();

        assertThat(target.getPosition(), is(equalTo(endPoint)));
    }

    @Test
    public void testMode_withStickedBall() {
        Speed speed = Direction.RIGHT.toSpeed();
        Point startPoint = new Point(0, 0);
        Point endPoint = startPoint.add(speed);
        Ball ball = mock(Ball.class);
        when(ball.getSpeed()).thenReturn(Direction.DOWN.toSpeed());
        Paddle target = new StickyPaddle(paddle, startPoint);
        target.interact(ball);
        target.setSpeed(speed);
        target.move();


        assertThat(target.getPosition(), is(equalTo(endPoint)));
        verify(ball).setPosition(new Point(endPoint.X + 1, endPoint.Y - 1));
    }

    @Test
    public void testFire_withoutStickedBall() {
        Room room = mock(Room.class);
        Paddle target = new StickyPaddle(paddle, position);
        target.fire(room);

        assertThat(target.isAlive(), is(false));
        verify(paddle).setPosition(position);
        verify(room).add(paddle);
        verify(paddle).setAlive(true);
    }

    @Test
    public void testFire_withStickedBall() {
        Room room = mock(Room.class);
        Ball ball = mock(Ball.class);
        when(ball.getSpeed()).thenReturn(Direction.DOWN.toSpeed());
        Paddle target = new StickyPaddle(paddle, position);
        target.interact(ball);
        target.fire(room);

        assertThat(target.isAlive(), is(false));
        verify(paddle).setPosition(position);
        verify(room).add(paddle);
        verify(paddle).setAlive(true);
        verify(ball).setSpeed(Direction.DOWN.toSpeed().mirrorY());
    }

    @Test
    public void testExchange() {
        PaddleChangedListener paddleChangedListener = mock(PaddleChangedListener.class);
        Paddle target = new StickyPaddle(paddle, position);
        target.addListener(paddleChangedListener);
        target.exchange(paddle);

        assertThat(target.isAlive(), is(false));
        verify(paddleChangedListener).paddleChanged(paddle);
    }

    @Test
    public void testLifeLost() {
        LifeLostListener lifeLostListener = mock(LifeLostListener.class);
        Paddle target = new StickyPaddle(paddle, position);
        target.addLifeLostListener(lifeLostListener);
        target.lifeLost();

        verify(lifeLostListener).lifeLost();
    }

    @Test
    public void testIsAlive_true() {
        Paddle target = new StickyPaddle(paddle, position);
        target.setAlive(true);

        assertThat(target.isAlive(), is(true));
    }

    @Test
    public void testIsAlive_false() {
        Paddle target = new StickyPaddle(paddle, position);
        target.setAlive(false);

        assertThat(target.isAlive(), is(false));
    }

    @Test
    public void testIsFirable_true() {
        Paddle target = new StickyPaddle(paddle, position);
        target.setFirable(true);

        assertThat(target.isFirable(), is(true));
    }

    @Test
    public void testIsFirable_false() {
        Paddle target = new StickyPaddle(paddle, position);
        target.setFirable(false);

        assertThat(target.isFirable(), is(false));
    }

}