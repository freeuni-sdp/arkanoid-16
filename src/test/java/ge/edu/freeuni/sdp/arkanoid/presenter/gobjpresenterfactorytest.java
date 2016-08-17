package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Created by Tornike on 04.05.2016.
 */

public class gobjpresenterfactorytest {
    /** Test GetPresenter Method on BrickPresenter*/
    @Test
    public void testBrickPresenter(){
        Size s = new Size(10, 7);
        GobjPresenterFactory factory = new GobjPresenterFactory(s);
        Brick b = mock(Brick.class);
        when(b.getKind()).thenReturn(GobjKind.Brick);
        GobjPresenter presenter = factory.getPresenter(b);
        assertTrue(presenter instanceof  BrickPresenter);
        BrickPresenter p = (BrickPresenter) presenter;
        assertEquals(p.getSize(), s);
    }

    /** Test GetPresenter Method on PaddlePresenter*/
    @Test
    public void testPaddlePresenter(){
        GobjPresenterFactory factory = new GobjPresenterFactory(new Size(11, 7));
        Paddle p = new Paddle(new Size(5, 3));
        GobjPresenter presenter = factory.getPresenter(p);
        assertTrue(presenter instanceof PaddlePresenter);
        PaddlePresenter pr = (PaddlePresenter) presenter;
        assertEquals(pr.getSize(), new Size(11, 7));
        assertTrue((pr.getGameObject()) instanceof Paddle);
    }

    /** Test GetPresenter Method on BallPresenter*/
    @Test
    public void testBallPresenter(){
        GobjPresenterFactory factory = new GobjPresenterFactory(new Size(11, 8));
        Ball b = new Ball();
        GobjPresenter presenter = factory.getPresenter(b);
        assertTrue(presenter instanceof BallPresenter);
        BallPresenter pr = (BallPresenter) presenter;
        assertEquals(pr.getSize(), new Size(11, 8));
        assertTrue((pr.getGameObject()) instanceof Ball);
    }

}