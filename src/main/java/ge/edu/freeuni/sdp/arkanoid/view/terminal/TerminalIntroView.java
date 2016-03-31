package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.IntroPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.IntroView;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TerminalIntroView extends IntroView {
    private final String _spaces;
    private final ClassLoader _classLoader;
    private Terminal _terminal;
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

        Clip clip = playTune();
        while (true) {
            Key key = _terminal.readInput();
            if (key != null) break;
        }
        if (clip != null) clip.close();
    }

    private Clip playTune() {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(_classLoader.getResource("intro.wav").openStream()));
        } catch (LineUnavailableException e) {
            printLine(_row++, "Ups! HTTP 404 - Not found ...");
        } catch (IOException e) {
            printLine(_row++, "Ups! HTTP 403 - Forbidden ...");
        } catch (UnsupportedAudioFileException e) {
            printLine(_row++, "Ups! HTTP 415 Unsupported Media Type ...");
        }

        if (clip != null) clip.loop(42);
        return clip;
    }

    private void drawSplashScreen() {
        File file = new File(_classLoader.getResource("intro.txt").getFile());
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
