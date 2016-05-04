package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.TimerPaddleInterfaces.ICurrentTime;
import ge.edu.freeuni.sdp.arkanoid.TimerPaddleInterfaces.StubCurrentTime;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.asm.tree.analysis.Frame;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


/**
 * Created by khrak on 5/3/16.
 */
public class TimerPaddleTest {

    @Mock Capsule capsule;
    @Mock Point position;
    @Mock Size size;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void TimerPaddleGetTimeLeftTest() {

        ICurrentTime timer = new StubCurrentTime(0);

        TimerPaddle timerpaddle = new TimerPaddle(position, 500, capsule, timer);
        long timeLeft = timerpaddle.getTimeLeft();

        assertEquals(timeLeft,(long)0);
    }

    @Test
    public void TimerPaddleMoveTimediffGreaterThanMilisecToLiveTest() {
        ICurrentTime timer = new StubCurrentTime(500);
        TimerPaddle timerpaddle = new TimerPaddle(position, 50, capsule, timer);

        timerpaddle.move();

        verify(capsule, times(1)).interact(timerpaddle);
    }

    @Test
    public void TimerPaddleMoveTimeDiffLessThanMilisecToLiveTest() {
        ICurrentTime timer = new StubCurrentTime(50);
        TimerPaddle timerpaddle = Mockito.spy(new TimerPaddle(position, 500, capsule, timer));

        timerpaddle.move();

        verify(timerpaddle, times(1)).move();
    }

    @Test
    public void PaddleGetKindTest() {
        Paddle paddle = new Paddle(position);

        assertEquals(paddle.getKind(), GobjKind.Paddle);
    }


    @Test
    public void PaddleInteractInstanceOfFrameBrickSetPositionTest() {
        Paddle paddlenew = new Paddle(position);


        Paddle paddle = Mockito.spy(new Paddle(position));
        Mockito.doCallRealMethod().when(paddle).interact(any());

        paddle.interact(new FrameBrick(position, size));

        verify(paddle, times(1)).setPosition(any());
    }


    @Test
    public void PaddleInteractInstanceOfBallGetSpeedTest() {
        Paddle paddle = new Paddle(position);

        Ball ball = mock(Ball.class);
        when(ball.getSpeed()).thenReturn(new Speed(30));

        paddle.interact(ball);
        verify(ball, times(1)).getSpeed();
    }

    @Test
    public void PaddleInteractInstanceOfBallSetSpeedTest() {
        Paddle paddle = new Paddle(position);

        Ball ball = mock(Ball.class);
        when(ball.getSpeed()).thenReturn(new Speed(30));

        paddle.interact(ball);
        verify(ball, times(1)).setSpeed(any());
    }


    @Test
    public void PaddleIsAliveTest() {

        Paddle paddle = new Paddle(position);

        assertEquals(paddle.isAlive(), true);
    }

    @Test
    public void PaddleSetAliveTest() {

        Paddle paddle = new Paddle(position);

        paddle.setAlive(false);
        assertEquals(paddle.isAlive(), false);
    }

    @Test
    public void PaddleIsFireable() {

        Paddle paddle = new Paddle(position);
        paddle.setFirable(true);
        assertEquals(paddle.isFirable(), true);
    }

    @Test
    public void PaddleMoveSuperMoveTest() {
        Paddle paddlenew = new Paddle(position);

        Paddle paddle = Mockito.spy(new Paddle(position));

        paddle.move();

        verify(paddle, times(1)).move();
    }


    @Test
    public void PaddleExchangeIsAliveTest() {

        Paddle paddle = new Paddle(position);
        Paddle paddlenew = mock(Paddle.class);

        paddle.exchange(paddlenew);
        assertEquals(paddle.isAlive(), false);
    }



    @Test
    public void PaddleExchangeThisGetPositionTest() {
        Paddle paddlenew = new Paddle(position);

        Paddle paddle = Mockito.spy(new Paddle(position));
        Mockito.doCallRealMethod().when(paddle).exchange(paddlenew);

        paddle.exchange(paddlenew);

        verify(paddle, times(1)).getPosition();
    }

    @Test
    public void PaddleExchangeNewPaddleSetPositionTest() {
        Paddle paddle = new Paddle(position);
        Paddle newpaddle = mock(Paddle.class);

        paddle.exchange(newpaddle);

        verify(newpaddle, times(1)).setPosition(any());
    }

    @Test
    public void PaddleFireRoomAddTest() {

        Paddle paddle = new Paddle(position);
        Room room = mock(Room.class);

        paddle.setFirable(true);

        paddle.fire(room);

        verify(room, times(1)).add(any());
    }


    @Test
    public void PaddleSetPositionTest() {
        Point position1 = new Point(3,4);
        Point position2 = new Point(4,5);

        Paddle paddle = new Paddle(position1);
        paddle.setPosition(position2);
        //assertTrue(paddle.getPrevPosition().equals(position2));
        // paddle.getPrevPosition() es metodi iyo tavidan da axla ro chamovfule
        // agaraa amitom vakomentareb.
    }

    @Test
    public void PaddleSetPositionSuperSetPositionTest() {

        Paddle paddlenew = new Paddle(position);

        Paddle paddle = Mockito.spy(new Paddle(position));

        paddle.setPosition(position);

        verify(paddle, times(1)).setPosition(any());

    }

}
