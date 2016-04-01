package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.presenter.IntroPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.IntroView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class TerminalIntroView extends IntroView {
    private final String _spaces;
    private final ClassLoader _classLoader;
    private final Terminal _terminal;
    private int _row;

    public TerminalIntroView(IntroPresenter presenter, Terminal terminal) {
        super(presenter);
        _terminal = terminal;
        int nrOfSpaces = (terminal.getTerminalSize().getColumns() - 81) / 2;
        _spaces = String.format("%" + nrOfSpaces + "s", "");
        _row = 0;
        _classLoader = getClass().getClassLoader();
    }

    @Override
    protected void show() {

        _terminal.clearScreen();
        drawSplashScreen();

        SoundPlayer.getInstance().loop(SoundPlayer.INTRO);

        while (true) {
            Key key = _terminal.readInput();
            if (key != null) break;
        }

        SoundPlayer.getInstance().stopAll();
        SoundPlayer.getInstance().play(SoundPlayer.START);
    }


    private void drawSplashScreen() {
        URL resource = _classLoader.getResource("intro.txt");
        if (resource == null) return;
        File file = new File(resource.getFile());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                printLine(_row++, _spaces + line);
            }
            scanner.close();
        } catch (IOException e) {
            printLine(_row++, "Ups! HTTP 403 - Forbidden ...");
        }
    }

    private void printLine(int i, String line) {
        for (int j = 0; j < line.length(); j++) {
            _terminal.moveCursor(j, i);
            _terminal.putCharacter(line.charAt(j));
        }
    }
}
