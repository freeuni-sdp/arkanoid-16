package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by khrak on 5/3/16.
 */
public class DisruptionCapsuleTest {
    @Mock Point position;
    @Mock Room room;
    @Mock Paddle paddle;
    @Mock Ball ball;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void DisruptionCapsuleSuperInteractTest() {

        DisruptionCapsule disruptionCapsule = Mockito.spy(new DisruptionCapsule(position, room));

        disruptionCapsule.interact(any());

        verify(disruptionCapsule, times(1)).interact(any());
    }

    @Test
    public void DisruptionCapsuleInteractRoomGetBallsTest() {

        DisruptionCapsule disruptionCapsule = new DisruptionCapsule(position, room);

        when(room.getBalls()).thenReturn(new ArrayList<Ball>(Arrays.asList(new Ball())));

        disruptionCapsule.interact(paddle);

        verify(room, times(1)).getBalls();
    }

    @Test
    public void DisruptionCapsuleInteractBallsSizeTest() {
        DisruptionCapsule disruptionCapsule = new DisruptionCapsule(position, room);

        Ball mockedBall = mock(Ball.class);

        ArrayList<Ball> mylist = mock(ArrayList.class);
        mylist.add(mockedBall);

        when(room.getBalls()).thenReturn(mylist);

        disruptionCapsule.interact(paddle);

        verify(mylist, times(1)).size();
    }

    @Test
    public void DisruptionCapsuleInteractBallGetPositionTest() {

        DisruptionCapsule disruptionCapsule = new DisruptionCapsule(position, room);

        Ball mockedBall = mock(Ball.class);

        ArrayList<Ball> mylist = mock(ArrayList.class);
        mylist.add(mockedBall);

        when(room.getBalls()).thenReturn(mylist);
        when(mylist.size()).thenReturn(1);
        when(mylist.get(0)).thenReturn(mockedBall);

        disruptionCapsule.interact(paddle);

        verify(mockedBall, times(1)).getPosition();
    }

    @Test
    public void DisruptionCapsuleInteractAddBallTest() {
        DisruptionCapsule disruptionCapsule = new DisruptionCapsule(position, room);

        Ball mockedBall = mock(Ball.class);

        ArrayList<Ball> mylist = mock(ArrayList.class);
        mylist.add(mockedBall);

        when(room.getBalls()).thenReturn(mylist);
        when(mylist.size()).thenReturn(1);
        when(mylist.get(0)).thenReturn(mockedBall);

        disruptionCapsule.interact(paddle);

        verify(mockedBall, times(2)).addBall(any());
    }

    @Test
    public void CapsuleGetKindTest() {
        Point position = mock(Point.class);
        Room room = mock(Room.class);


        DisruptionCapsule capsule = new DisruptionCapsule(position, room);

        assertEquals(capsule.getKind(), GobjKind.Capsule);
    }

    @Test
    public void CapsuleInteractTest() {

        Point position = mock(Point.class);
        Room room = mock(Room.class);
        Paddle paddle = mock(Paddle.class);

        DisruptionCapsule capsule = new DisruptionCapsule(position, room);
        capsule.interact(paddle);

        assertEquals(capsule.isAlive(),false);
    }

    @Test
    public void CapsuleIsAliveTest() {
        DisruptionCapsule capsule = new DisruptionCapsule(position, room);

        assertEquals(capsule.isAlive(), true);
    }

    @Test
    public void CapsuleReleaseThisSetPositionTest() {
        DisruptionCapsule disruptionCapsule = Mockito.spy(new DisruptionCapsule(position, room));

        disruptionCapsule.release(any());

        verify(disruptionCapsule, times(1)).setPosition(any());
    }

    @Test
    public void CapsuleReleaseThisSetSpeedTest() {
        DisruptionCapsule disruptionCapsule = Mockito.spy(new DisruptionCapsule(position, room));

        disruptionCapsule.release(any());

        verify(disruptionCapsule, times(1)).setSpeed(any());
    }

    @Test
    public void CapsuleReleaseRoomAddTest() {
        Point position = mock(Point.class);
        Room room = mock(Room.class);
        Paddle paddle = mock(Paddle.class);

        DisruptionCapsule capsule = new DisruptionCapsule(position, room);
        capsule.release(position);

        verify(room, times(1)).add(any());
    }

    @Test
    public void CapsuleGetRoomTest() {
        Point position = mock(Point.class);
        Room room = mock(Room.class);
        Paddle paddle = mock(Paddle.class);

        DisruptionCapsule capsule = new DisruptionCapsule(position, room);
        Room room1 = capsule.getRoom();

        assertTrue(room.equals(room1));
    }
}

