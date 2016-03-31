package ge.edu.freeuni.sdp.arkanoid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import ge.edu.freeuni.sdp.arkanoid.model.Configuration;
import ge.edu.freeuni.sdp.arkanoid.model.Game;
import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.Level;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.presenter.PresenterFactory;
import ge.edu.freeuni.sdp.arkanoid.view.ViewController;
import ge.edu.freeuni.sdp.arkanoid.view.terminal.TerminalViewFactory;

import java.nio.charset.Charset;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Terminal terminal = getTerminal();
        SoundPlayer soundPlayer = SoundPlayer.getInstance();
        Size size = getSize(terminal);

        List<Level> levels = LevelRegistry.getLevels(size);

        Configuration.init(size, levels);
        TerminalViewFactory viewFactory = new TerminalViewFactory(terminal);
        GameFacade gameFacade = new Game(size);
        PresenterFactory presenterFactory = new PresenterFactory(gameFacade, size);
        ViewController controller = new ViewController(viewFactory, presenterFactory);
        controller.run();

        soundPlayer.close();
        terminal.exitPrivateMode();
    }


    private static Terminal getTerminal() {
        Terminal terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.clearScreen();
        terminal.setCursorVisible(false);
        return terminal;
    }

    private static Size getSize(Terminal terminal) {
        TerminalSize terminalSize = terminal.getTerminalSize();
        int width = terminalSize.getColumns();
        int height = terminalSize.getRows();

        return new Size(width, height - 1);
    }
}