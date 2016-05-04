package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.model.Game;
import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by root_pc on 5/4/2016.
 */

public class TerminalGameOverViewTest {


    @Mock
    private Terminal terminal;


    @Mock
    GameOverPresenter gameOverPresenter;

    private TerminalGameOverView gameOverView;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(gameOverPresenter.getRoomSize()).thenReturn(new Size(100, 100));
        gameOverView = new TerminalGameOverView(gameOverPresenter,terminal);
    }


    @Test
    public void check_yes_input(){
        when(terminal.readInput()).thenReturn(new Key('y'));
        gameOverView.show();
        verify(gameOverPresenter, times(1)).setGameActivity(true);
    }

    @Test
    public void check_no_input(){
        when(terminal.readInput()).thenReturn(new Key('n'));
        gameOverView.show();
        verify(gameOverPresenter, times(1)).setGameActivity(false);
    }


}
