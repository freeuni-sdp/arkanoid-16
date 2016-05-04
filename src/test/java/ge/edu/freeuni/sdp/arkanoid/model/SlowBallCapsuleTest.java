package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


/**
 * Created by giorgi on 4/29/2016.
 */
public class SlowBallCapsuleTest {
    @Mock Room _room;
    @Mock Point _position;
    private SlowBallCapsule _target;
    private Set<Gobj> _gobjs;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        _target = new SlowBallCapsule(_position, _room);
        _gobjs = new HashSet<>();
    }

    @Test
    public void targetIsNotKillable(){
        assertFalse(_target.isKillable());
    }

    @Test
    public void targetKindIsCapsule(){
        GobjKind targetKind = _target.getKind();
        assertEquals(GobjKind.Capsule, targetKind);
    }

    @Test
    public void targetIsAliveWhenCreated(){
        assertTrue(_target.isAlive());
    }

    @Test
    public void paddleKillsTarget(){
        Paddle paddle = mock(Paddle.class);
        _target.interact(paddle);
        assertFalse(_target.isAlive());
    }

    @Test
    public void frameBrickKillsTarget(){
        FrameBrick frameBrick = mock(FrameBrick.class);
        _target.interact(frameBrick);
        assertFalse(_target.isAlive());
    }

    @Test
    public void targetReleasePositionIsSetCorrectly(){
        Point position = mock(Point.class);
        _target.release(position);
        assertEquals(position, _target.getPosition());
    }

    @Test
    public void targetSpeedIsSetCorrectlyOnRelease(){
        double expectedSpeedAngle = 90;
        Point position = mock(Point.class);
        _target.release(position);
        assertNotNull(_target.getSpeed());
        assertTrue(_target.getSpeed().getLength() > 0);
        assertTrue(expectedSpeedAngle == _target.getSpeed().getAngleDegrees());
    }

    @Test
    public void targetIsAddedToRoomOnRelease(){
        Point position = mock(Point.class);
        _target.release(position);
        verify(_room).add(_target);
    }

    @Test
    public void createCapsuleParametersTest(){
        Capsule createdCapsule = _target.createCapsule(_position, _room);
        assertTrue(createdCapsule instanceof SlowBallCapsule);
        assertEquals(_position, createdCapsule.getPosition());
        assertEquals(_room, createdCapsule.getRoom());
    }

    @Test
    public void targetSlowsBallDownWhenItHitsPaddle(){
        int sampleAngleDegrees = 90;
        double sampleBallSpeedLength = 10;
        double expectedBallSpeedLength = sampleBallSpeedLength / 2;

        ArgumentCaptor<Speed> speedArgumentCaptor = ArgumentCaptor.forClass(Speed.class);

        // Speed is the final class, so it's impossible to mock in this java version(you cannot extend final class)
        Speed testingSpeed = new Speed(sampleAngleDegrees);
        testingSpeed.setLength(sampleBallSpeedLength);
        Ball ball = mock(Ball.class);
        Paddle dummyPaddle = mock(Paddle.class);
        _gobjs.add(ball);

        when(ball.getSpeed()).thenReturn(testingSpeed);
        when(_room.getGobjs()).thenReturn(_gobjs);

        _target.interact(dummyPaddle);
        verify(ball).setSpeed(speedArgumentCaptor.capture());
        assertTrue(speedArgumentCaptor.getValue().getLength() == expectedBallSpeedLength);
    }

    @Test
    public void targetExchangesPaddleWithBallSpeedRestoringTimerPaddle(){
        Paddle paddle = mock(Paddle.class);
        PaddleFactory paddleFactory = mock(PaddleFactory.class);
        TimerPaddle ballSpeedRestoringPaddle = mock(TimerPaddle.class);
        when(paddleFactory.createBallSpeedRestoringTimerPaddle(any(), any(), any()))
                .thenReturn(ballSpeedRestoringPaddle);

        _target = new SlowBallCapsule(_position, _room, paddleFactory);
        _target.interact(paddle);

        verify(paddleFactory).createBallSpeedRestoringTimerPaddle(_target.getPosition(), _room, paddle);
        verify(paddle).exchange(ballSpeedRestoringPaddle);
    }
}
