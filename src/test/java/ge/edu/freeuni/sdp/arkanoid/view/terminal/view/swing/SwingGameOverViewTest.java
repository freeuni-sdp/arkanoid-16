package ge.edu.freeuni.sdp.arkanoid.view.terminal.view.swing;

import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.swing.SwingGameOverView;
import ge.edu.freeuni.sdp.arkanoid.view.swing.SwingGameOverViewChild;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by GM on 5/4/2016.
 */
public class SwingGameOverViewTest {
    @Mock
    private JFrame jframe;
    @Mock
    private GameOverPresenter gameOverPresenter;
    private SwingGameOverView gameOverView;
    private SwingGameOverViewChild gameOverViewChild;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        gameOverView = new SwingGameOverView(gameOverPresenter, jframe);
        gameOverViewChild = new SwingGameOverViewChild(gameOverPresenter, jframe);
    }

    @Test
    public void testShowFinishGame(){
        when(jframe.getComponentOrientation()).thenReturn(ComponentOrientation.UNKNOWN);
        when(JOptionPane.showOptionDialog(
                jframe,
                "Would you like to play again ?",
                "Game is over",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null)).thenReturn(1);
        gameOverViewChild.publicShow();
        assertTrue(gameOverPresenter.isGameActive());
    }
}
