package ge.edu.freeuni.sdp.arkanoid.view.terminal.view.swing;

import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.swing.SwingGameOverView;
import ge.edu.freeuni.sdp.arkanoid.view.swing.SwingGameOverViewChild;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by GM on 5/4/2016.
 */
public class SwingGameOverViewTest {
    @Mock
    private JFrame jframe;
    @Mock
    private GameFacade facade;
    @Mock
    private SwingGameOverView gameOverView;
    @Mock
    private SwingGameOverViewChild gameOverViewChild;
    private GameOverPresenter gameOverPresenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        gameOverPresenter = new GameOverPresenter(facade);
        gameOverViewChild = new SwingGameOverViewChild(gameOverPresenter, jframe);
    }

    @Test
    public void testShowFinishGame(){
        gameOverViewChild.setMockValue(0);
        gameOverViewChild.publicShow();
        assertTrue(gameOverPresenter.isGameActive());
    }

    @Test
    public void testShowContinueGame1(){
        gameOverViewChild.setMockValue(1);
        gameOverViewChild.publicShow();
        assertFalse(gameOverPresenter.isGameActive());
    }

    @Test
    public void testShowContinueGame17(){
        gameOverViewChild.setMockValue(17);
        gameOverViewChild.publicShow();
        assertFalse(gameOverPresenter.isGameActive());
    }

}
