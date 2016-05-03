package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by misho on 5/3/2016.
 */
public class TerminalLevelViewTest {
    @Mock private LevelPresenter presenter;
    @Mock private Terminal terminal;

    private TerminalLevelView view;
    private String[] names = {"name1", "1enam"};

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        view = new TerminalLevelView(presenter, terminal);
        verify(presenter).setLevelSelectionListener(view);
    }

    @Test
    public void testShowEnter(){
        when(terminal.readInput()).thenReturn(new Key(Key.Kind.Enter));
        when(presenter.getLevelNames()).thenReturn(names);
        view.show();
        verifyShowMethods();
    }

    @Test
    public void testShowDownAndEnter(){
        when(terminal.readInput())
                .thenReturn(new Key(Key.Kind.ArrowDown))
                .thenReturn(new Key(Key.Kind.Enter));
        when(presenter.getLevelNames()).thenReturn(names);
        view.show();
        verify(presenter).setSelection(1);
        verifyShowMethods();
    }

    @Test
    public void testShowDownUpAndEnter(){
        when(terminal.readInput())
                .thenReturn(new Key(Key.Kind.ArrowDown))
                .thenReturn(new Key(Key.Kind.ArrowUp))
                .thenReturn(new Key(Key.Kind.Enter));
        when(presenter.getLevelNames()).thenReturn(names);
        view.show();
        verify(presenter).setSelection(0);
        verifyShowMethods();
    }

    @Test
    public void testShowDownMoreTimesThanValues(){
        when(terminal.readInput())
                .thenReturn(new Key(Key.Kind.ArrowDown))
                .thenReturn(new Key(Key.Kind.ArrowDown))
                .thenReturn(new Key(Key.Kind.ArrowDown))
                .thenReturn(new Key(Key.Kind.Enter));
        when(presenter.getLevelNames()).thenReturn(names);
        view.show();
        verify(presenter, times(3)).setSelection(anyInt());
        verifyShowMethods();
    }

    @Test
    public void testShowOnCharN(){
        when(terminal.readInput())
                .thenReturn(new Key('n'))
                .thenReturn(new Key(Key.Kind.Enter));
        when(presenter.getLevelNames()).thenReturn(names);
        view.show();
        verify(presenter, times(1)).setSelection(0);
        verifyShowMethods();
    }

    @Test
    public void testShowOnChar2(){
        System.out.println(Character.getNumericValue('1'));
        when(terminal.readInput())
                .thenReturn(new Key('2'))
                .thenReturn(new Key(Key.Kind.Enter));
        when(presenter.getLevelNames()).thenReturn(names);
        view.show();
        verify(presenter, times(1)).setSelection(1);
        verifyShowMethods();
    }

    private void verifyShowMethods(){
        int moveTimes = 1 + names.length;
        verify(terminal, times(moveTimes)).moveCursor(anyInt(), anyInt());
        verify(terminal).clearScreen();
        verify(terminal).flush();
    }

}
