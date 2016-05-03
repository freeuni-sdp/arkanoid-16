package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Ball;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by vato on 5/3/16.
 */
public class BallPresenterTest {

    @Mock private Ball ballMock;
    @Mock private Size sizeMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void drawBallEqualsTrue() {
        double ballPosX = 4.8, ballPosY = 5.2;
        int sizeWidth = 5, sizeHeight = 6;
        when(ballMock.getPosition()).thenReturn(new Point(ballPosX, ballPosY));
        when(sizeMock.getWidth()).thenReturn(sizeWidth);
        when(sizeMock.getHeight()).thenReturn(sizeHeight);
        when(sizeMock.isInRange(any(Point.class))).thenReturn(true);
        BallPresenter ballPresenter = new BallPresenter(ballMock, sizeMock);
        CellContent[][] cellContents = new CellContent[10][10];
        ballPresenter.Draw(cellContents);
        assertEquals(cellContents[4][5], CellContent.Ball);
    }

    @Test
    public void drawBallEqualsFalse() {
        double ballPosX = 7, ballPosY = 8;
        int sizeWidth = 4, sizeHeight = 4;
        when(ballMock.getPosition()).thenReturn(new Point(ballPosX, ballPosY));
        when(sizeMock.getWidth()).thenReturn(sizeWidth);
        when(sizeMock.getHeight()).thenReturn(sizeHeight);
        when(sizeMock.isInRange(any(Point.class))).thenReturn(false);
        BallPresenter ballPresenter = new BallPresenter(ballMock, sizeMock);
        CellContent[][] cellContents = new CellContent[10][10];
        ballPresenter.Draw(cellContents);
        assertNotEquals(cellContents[7][8], CellContent.Ball);
    }
}
