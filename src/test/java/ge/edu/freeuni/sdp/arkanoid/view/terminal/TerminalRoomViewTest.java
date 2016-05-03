package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.terminal.text.UnixTerminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.Command;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class TerminalRoomViewTest {

    private DefaultPressedKeyWatcher watcher;
    private RoomPresenter presenter;
    private com.googlecode.lanterna.terminal.Terminal terminal;

    @Before
    public void setUp() throws Exception {
        watcher = mock(DefaultPressedKeyWatcher.class);
        presenter = mock(RoomPresenter.class);
        terminal = mock(UnixTerminal.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testShowRightKeyExecutesRightCommand() throws Exception {

        when(watcher.isRightPressed()).thenReturn(true);
        BDDMockito.given(presenter.isGameOver()).willReturn(false,true);

        TerminalRoomView view = new TerminalRoomView(presenter, terminal, watcher,0);
        view.show();
        verify(presenter).execute(Command.Right);

    }

    @Test
    public void testShowSpaceKeyExecutesFireCommand() throws Exception {

        when(watcher.isSpacePressed()).thenReturn(true);
        BDDMockito.given(presenter.isGameOver()).willReturn(false,true);

        TerminalRoomView view = new TerminalRoomView(presenter, terminal, watcher,0);
        view.show();
        verify(presenter).execute(Command.Fire);

    }

    @Test
    public void testShowLeftKeyExecutesLeftCommand() throws Exception {

        when(watcher.isLeftPressed()).thenReturn(true);
        BDDMockito.given(presenter.isGameOver()).willReturn(false,true);

        TerminalRoomView view = new TerminalRoomView(presenter, terminal, watcher,0);
        view.show();
        verify(presenter).execute(Command.Left);

    }
    @Test
    public void testShowEscapeKeyTerminatesLoop() throws Exception {

        when(watcher.isEscapePressed()).thenReturn(true);
        BDDMockito.given(presenter.isGameOver()).willReturn(false);
        TerminalRoomView view = new TerminalRoomView(presenter, terminal, watcher,0);
        view.show();
        verify(presenter,atMost(1)).isGameOver();

    }
    @Test
    public void testShowPauseKeyExecutesPauseCommand() throws Exception {

//        when(watcher.isPausedPressed()).thenReturn(true);
//        BDDMockito.given(presenter.isGameOver()).willReturn(false, true);
//        TerminalRoomView view = new TerminalRoomView(presenter, terminal, watcher,0);
//        view.show();
//        verify(presenter).execute(Command.Pause);

    }

    @Test
    public void testUpdateCell() throws Exception {

    }

    @Test
    public void testUpdateScore() throws Exception {
        TerminalRoomView view = new TerminalRoomView(presenter, terminal, watcher,0);
        ArgumentCaptor<Character> argumentCaptor = ArgumentCaptor.forClass(Character.class);
        when(terminal.getTerminalSize()).thenReturn(new TerminalSize(200,200));
        view.updateScore(1);

        verify(terminal, times(8)).putCharacter(argumentCaptor.capture());


        List<Character> capturedPeople = argumentCaptor.getAllValues();

        List<Character> expected = Arrays.asList('S','c','O','r','e',':',' ','1');

        boolean equals = expected.equals(capturedPeople);
        Collections.reverse(expected);
        boolean equals1 = expected.equals(capturedPeople);
        Assert.assertEquals(equals,equals1);



    }

    @Test
    public void testUpdateLives() throws Exception {
        TerminalRoomView view = new TerminalRoomView(presenter, terminal, watcher,0);
        ArgumentCaptor<Character> argumentCaptor = ArgumentCaptor.forClass(Character.class);
        when(terminal.getTerminalSize()).thenReturn(new TerminalSize(200,200));
        view.updateLives(2);

        verify(terminal, times(2)).putCharacter(argumentCaptor.capture());


        List<Character> capturedPeople = argumentCaptor.getAllValues();

        List<Character> expected = Arrays.asList('*','*');

        boolean equals = expected.equals(capturedPeople);
        Collections.reverse(expected);
        boolean equals1 = expected.equals(capturedPeople);
        Assert.assertEquals(equals,equals1);

    }
}