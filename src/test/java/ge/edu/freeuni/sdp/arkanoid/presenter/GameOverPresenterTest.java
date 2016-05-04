package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Meko on 04/05/2016.
 */
public class GameOverPresenterTest {
    private GameFacade facade;
    private Size size;
    private GameOverPresenter presenter;

    @Before
    public void setup(){
        facade = mock(GameFacade.class);
        presenter = new GameOverPresenter(facade);
        size = mock(Size.class);
        when(facade.getSize()).thenReturn(size);
    }

    @Test
    public void testRoomSizeCallsGameFacadeSize(){
        Size tempSize =  presenter.getRoomSize();
        verify(facade).getSize();
    }

    @Test
    public void testRoomSizeSameAsGameFacadeSize(){
        assert(presenter.getRoomSize() == size);
    }

    @Test
    public void testIsGameActive(){
        presenter.setGameActivity(false);
        boolean falseAssert = presenter.isGameActive();
        presenter.setGameActivity(true);
        boolean trueAssert = presenter.isGameActive();
        assert(!falseAssert && trueAssert);
    }



}
