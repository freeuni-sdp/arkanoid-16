package ge.edu.freeuni.sdp.arkanoid.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


/**
 * Created by giorgi on 4/30/2016.
 */
public class LevelTest {
    @Mock RoomBuilder _roomBuilder;
    private String _testingName = "testingLevel";
    private String _testingDescription = "testingDescription";
    private Level _target;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        _target = new Level(_testingName, _testingDescription, _roomBuilder);
    }

    @Test
    public void getNameReturnsCorrectName(){
        assertEquals(_testingName, _target.getName());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription(){
        assertEquals(_testingDescription, _target.getDescription());
    }

    @Test
    public void buildCallsRoomBuilderBuild(){
        Room dummyRoom = mock(Room.class);
        ScoreCounter dummyCounter = mock(ScoreCounter.class);

        _target.build(dummyRoom, dummyCounter);
        verify(_roomBuilder).build(dummyRoom, dummyCounter);
    }

    @Test(expected=NullPointerException.class)
    public void buildThrowsExceptionWhenBuilderIsNull(){
        Room dummyRoom = mock(Room.class);
        ScoreCounter dummyCounter = mock(ScoreCounter.class);

        _target = new Level(_testingName, _testingDescription, null);
        _target.build(dummyRoom, dummyCounter);
    }
}
