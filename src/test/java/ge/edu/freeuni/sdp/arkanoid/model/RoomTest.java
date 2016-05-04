package ge.edu.freeuni.sdp.arkanoid.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by elene on 5/4/16.
 */
public class RoomTest {


    /*
    * areKillablesLeft() metodi ukugma abrunebs, tu darchenilia - trues, tu araa - false
    * piriqit miweria testebshi, imitom rom sxva ragacebis shemowmebac miwevs imave metodit da mashin es unda gamesworebina, rac yvelgan aurevda, sadac gamoyenebuli aqvt
    *
    *
    * */



    @Test
    public void testRoomConstructor(){
        Room room = new Room();
        assertTrue(room.areKillablesLeft());
        assertTrue(room.getGobjs().isEmpty());
    }

    @Test
    public void testAddGobj(){
        Room room = new Room();
        int numObj = 10;
        HashSet<Gobj> objects = new HashSet<>();
        for(int i = 0; i < numObj; i++){
            Gobj tmp = mock(Gobj.class);
            objects.add(tmp);
            room.add(tmp);
        }
        int counter = 0;
        Set<Gobj> _gobjs = room.getGobjs();
        for (Gobj obj : _gobjs) {
            counter++;
            assertTrue(objects.contains(obj));
        }
        assertEquals(numObj, counter);
    }

    @Test
    public void testMove(){
        Room room = new Room();
        int numObj = 10;
        for(int i = 0; i < numObj; i++){
            Gobj tmp = mock(Gobj.class);
            room.add(tmp);
        }
        room.move();
        Set<Gobj> _gobjs = room.getGobjs();
        for (Gobj obj : _gobjs) {
            verify(obj).move();

        }
    }

    @Test
    public void testKillables(){
        Room room = new Room();
        int numKillables = 5;
        int numNonKillables = 3;
        for(int i = 0; i < numKillables; i++){
            Gobj tmp = mock(Gobj.class);
            when(tmp.isKillable()).thenReturn(true);
            room.add(tmp);
        }
        for(int i = 0; i < numNonKillables; i++){
            Gobj tmp = mock(Gobj.class);
            when(tmp.isKillable()).thenReturn(false);
            room.add(tmp);
        }
        assertFalse(room.areKillablesLeft());
    }

    @Test
    public void testRemoveZombies(){
        Room room = new Room();
        int numKillables = 5;
        int numNonKillables = 3;
        for(int i = 0; i < numKillables; i++){
            Gobj tmp = mock(Gobj.class);
            when(tmp.isKillable()).thenReturn(true);
            room.add(tmp);
        }
        for(int i = 0; i < numNonKillables; i++){
            Gobj tmp = mock(Gobj.class);
            when(tmp.isKillable()).thenReturn(false);
            room.add(tmp);
        }
        room.removeZombies();
        assertTrue(room.areKillablesLeft());

    }


    @Test
    public void testGetBalls(){
        Room room = new Room();
        int numBalls = 5;
        HashSet<Ball> balls = new HashSet<>();
        for(int i = 0; i < numBalls; i++){
            Ball tmp = mock(Ball.class);
            balls.add(tmp);
            room.add(tmp);
        }
        ArrayList<Ball> roomBalls = room.getBalls();
        for(Ball tmpBall : roomBalls){
            assertTrue(balls.contains(tmpBall));
        }
        assertEquals(roomBalls.size(), balls.size());
    }





}
