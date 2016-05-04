package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by root_pc on 5/4/2016.
 */

public class TerminalGameOverViewTest {


    @Mock
    private Terminal terminal;

    @Mock
    private GameOverPresenter gameOverPresenter;

    private TerminalGameOverView gameOverView;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        gameOverView = new TerminalGameOverView(gameOverPresenter,terminal);
    }

}
