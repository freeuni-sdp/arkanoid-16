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
import ge.edu.freeuni.sdp.arkanoid.view.ViewFactory;
import ge.edu.freeuni.sdp.arkanoid.view.swing.SwingViewFactroy;
import ge.edu.freeuni.sdp.arkanoid.view.terminal.TerminalViewFactory;

import javax.swing.*;
import java.nio.charset.Charset;
import java.util.List;

class App {

    public static void main(String[] args) {

        SoundPlayer soundPlayer = SoundPlayer.getInstance();

        JFrame jFrame = getFrame();
        Size size = getSize(jFrame);

        Terminal terminal = getTerminal();
//        Size size = getSize(terminal);

        List<Level> levels = LevelRegistry.getLevels(size);

        Configuration.init(size, levels);

        ViewFactory viewFactory = new SwingViewFactroy(jFrame);
//        TerminalViewFactory viewFactory = new TerminalViewFactory(terminal);

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

    private static JFrame getFrame(){
        JFrame frame = new JFrame();
        frame.setSize(500, 350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    private static Size getSize(Terminal terminal) {
        TerminalSize terminalSize = terminal.getTerminalSize();
        int width = terminalSize.getColumns();
        int height = terminalSize.getRows();

        return new Size(width, height - 1);
    }

    private static Size getSize(JFrame jFrame) {
        int width = jFrame.getWidth();
        int height = jFrame.getHeight();

        return new Size(width, height - 1);
    }
}