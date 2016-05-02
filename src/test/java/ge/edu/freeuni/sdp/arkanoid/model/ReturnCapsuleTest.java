package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ReturnCapsuleTest {

    @Mock Paddle oldPaddleMock, activePaddleMock;
    @Mock Point pointMock;
    @Mock Room roomMock;
    @Mock SoundPlayer soundPlayerMock;

    private ReturnCapsule returnCapsule;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        returnCapsule = new ReturnCapsule(pointMock, roomMock, oldPaddleMock, 0.5);
        SoundPlayer.setSoundPlayer(soundPlayerMock);
    }

    @Test
    public void interact_InteractWithNoPaddle_OldPaddleSetAliveIsNotCalled(){
        Gobj gobjMock = mock(Gobj.class);
        returnCapsule.interact(gobjMock);
        verify(oldPaddleMock, never()).setAlive(true);
    }

    @Test
    public void interact_InteractWithPaddle_OldPaddleSetAliveIsCalled(){
        returnCapsule.interact(activePaddleMock);
        verify(oldPaddleMock, times(1)).setAlive(true);
    }

    @Test
    public void interact_InteractWithPaddle_OldPaddleIsAddedIntToTheRoom(){
        returnCapsule.interact(activePaddleMock);
        verify(roomMock, times(1)).add(oldPaddleMock);
    }

    @Test
    public void interact_InteractWithPaddle_PaddleIsExchanged(){
        returnCapsule.interact(activePaddleMock);
        verify(activePaddleMock, times(1)).exchange(oldPaddleMock);
    }

    @Test
    public void interact_RoomWithNoBall_ballGetSpeedIsNotCalled(){
        Gobj gobjMock = mock(Gobj.class);
        when(roomMock.getGobjs()).thenReturn(new HashSet<>(Arrays.asList(gobjMock)));
        returnCapsule.interact(activePaddleMock);
        verify(gobjMock, never()).getSpeed();
    }

    @Test
    public void interact_RoomWithABallAndAGobj_getSpeedIsCalledOnce(){
        Ball ballMock = mock(Ball.class);
        Speed speedMock = new Speed(0);
        when(ballMock.getSpeed()).thenReturn(speedMock);

        Gobj gobjMock = mock(Gobj.class);
        when(roomMock.getGobjs()).thenReturn(new HashSet<>(Arrays.asList(ballMock, gobjMock)));
        returnCapsule.interact(activePaddleMock);
        verify(ballMock, times(1)).getSpeed();
    }

    @Test
    public void interact_RoomWithABall_ballSpeedIsHalved(){
        Ball ballMock = mock(Ball.class);
        Speed speed = new Speed(0);
        speed.setLength(4);
        when(ballMock.getSpeed()).thenReturn(speed);

        when(roomMock.getGobjs()).thenReturn(new HashSet<>(Arrays.asList(ballMock)));
        returnCapsule.interact(activePaddleMock);
        assertEquals(speed.getLength(), 2, 0.01);
    }

}
