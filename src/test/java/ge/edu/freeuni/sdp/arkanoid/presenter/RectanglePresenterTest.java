package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


/**
 * Created by Nika Doghonadze.
 */
public class RectanglePresenterTest {
    private RectanglePresenter rectanglePresenter;

    @Before
    public void setUp() {
        rectanglePresenter = Mockito.mock(RectanglePresenter.class, Mockito.CALLS_REAL_METHODS);
    }


    @Test
    public void testDrawNone() {
        Gobj rectangleGobj = initRectangleObj(0, 0, 10, 10);

        when(rectanglePresenter.getGameObject()).thenReturn(rectangleGobj);
        when(rectanglePresenter.getContent()).thenReturn(CellContent.None);
        when(rectanglePresenter.getSize()).thenReturn(new Size(10, 10));
        CellContent[][] cells = initNoneCells(10, 10);

        rectanglePresenter.Draw(cells);

        checkAllCellsEqual(cells, CellContent.None);
    }

    @Test
    public void testDrawFullRectangle() {
        Gobj rectangleGobj = initRectangleObj(0, 0, 10, 10);
        when(rectanglePresenter.getGameObject()).thenReturn(rectangleGobj);
        when(rectanglePresenter.getContent()).thenReturn(CellContent.BlueBrick);
        when(rectanglePresenter.getSize()).thenReturn(new Size(10, 10));
        CellContent[][] cells = initNoneCells(10, 10);

        rectanglePresenter.Draw(cells);

        checkAllCellsEqual(cells, CellContent.BlueBrick);

    }

    @Test
    public void testDrawCapsuleAtOrigin() {
        Gobj rectangleGobj = initRectangleObj(0, 0, 2, 3);
        when(rectanglePresenter.getGameObject()).thenReturn(rectangleGobj);
        when(rectanglePresenter.getContent()).thenReturn(CellContent.ACapsule);
        when(rectanglePresenter.getSize()).thenReturn(new Size(10, 10));
        CellContent[][] cells = initNoneCells(10, 10);

        rectanglePresenter.Draw(cells);

        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[i].length; j++) {
                if (i < 2 && j < 3) {
                    assertEquals(CellContent.ACapsule, cells[i][j]);
                } else {
                    assertEquals(CellContent.None, cells[i][j]);
                }
            }
        }
    }

    @Test
    public void testDrawCapsuleInMiddle() {
        Gobj rectangleGobj = initRectangleObj(1, 2, 4, 5);
        when(rectanglePresenter.getGameObject()).thenReturn(rectangleGobj);
        when(rectanglePresenter.getContent()).thenReturn(CellContent.GrayBrick);
        when(rectanglePresenter.getSize()).thenReturn(new Size(10, 10));
        CellContent[][] cells = initNoneCells(10, 10);

        rectanglePresenter.Draw(cells);

        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[i].length; j++) {
                if (i >= 1 && i < 5 && j >= 2 && j < 7) {
                    assertEquals(CellContent.GrayBrick, cells[i][j]);
                } else {
                    assertEquals(CellContent.None, cells[i][j]);
                }
            }
        }
    }

    @Test
    public void testDrawOnSmallerBackground() {
        Gobj rectangleGobj = initRectangleObj(5, 5, 15, 15);
        when(rectanglePresenter.getGameObject()).thenReturn(rectangleGobj);
        when(rectanglePresenter.getContent()).thenReturn(CellContent.KillCapsule);
        when(rectanglePresenter.getSize()).thenReturn(new Size(10, 10));
        CellContent[][] cells = initNoneCells(10, 10);

        rectanglePresenter.Draw(cells);

        for (int i=0; i<cells.length; i++) {
            for (int j=0; j<cells[i].length; j++) {
                if (i >= 5 && j >= 5) {
                    assertEquals(CellContent.KillCapsule, cells[i][j]);
                } else {
                    assertEquals(CellContent.None, cells[i][j]);
                }
            }
        }
    }

    private Gobj initRectangleObj(int px, int py, int sx, int sy) {
        Gobj res = Mockito.mock(Gobj.class);
        Point point = new Point(px, py);
        Size size = new Size(sx, sy);
        when(res.getShape()).thenReturn(new Rectangle(point, size));
        return res;
    }

    private CellContent[][] initNoneCells(int x, int y) {
        CellContent[][] res = new CellContent[x][y];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = CellContent.None;
            }
        }
        return res;
    }

    private void checkAllCellsEqual(CellContent[][] cells, CellContent value) {
        for (CellContent[] cellRow : cells) {
            for (CellContent cellContent : cellRow) {
                assertEquals(value, cellContent);
            }
        }
    }

}