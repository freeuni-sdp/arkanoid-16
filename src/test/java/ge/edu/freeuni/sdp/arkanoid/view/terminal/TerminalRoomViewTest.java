package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.Command;
import ge.edu.freeuni.sdp.arkanoid.presenter.RoomPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;

import static org.mockito.Mockito.*;

public class TerminalRoomViewTest {

    private DefaultPressedKeyWatcher watcher;
    private RoomPresenter presenter;
    private com.googlecode.lanterna.terminal.Terminal terminal;

    @Before
    public void setUp() throws Exception {
        watcher = mock(DefaultPressedKeyWatcher.class);
        presenter = mock(RoomPresenter.class);
        terminal = mock(Terminal.class);
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

//        when(terminal.someMethod(anyString())).thenAnswer(new Answer() {
//            Object answer(InvocationOnMock invocation) {
//                Object[] args = invocation.getArguments();
//                Object mock = invocation.getMock();
//                return "called with arguments: " + args;
//            }
//        });

    }

    @Test
    public void testUpdateLives() throws Exception {

    }
}