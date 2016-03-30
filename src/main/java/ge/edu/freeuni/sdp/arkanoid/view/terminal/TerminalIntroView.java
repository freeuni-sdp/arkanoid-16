package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.IntroPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.IntroView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TerminalIntroView extends IntroView {
    private final String _spaces;
    private Terminal _terminal;

    public TerminalIntroView(IntroPresenter presenter, Terminal terminal) {
        super(presenter);
        _terminal = terminal;
        int nrOfSpaces = (terminal.getTerminalSize().getColumns() - 81) / 2;
        _spaces = String.format("%" + nrOfSpaces + "s", "");
    }

    @Override
    protected void show() {

        _terminal.clearScreen();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("intro.txt").getFile());
        int i = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                printLine(i++, _spaces + line);
            }
            scanner.close();
        } catch (IOException e) {
            printLine(i++, "Uuups ... 42");
        }

        while (true) {
            Key key = _terminal.readInput();
            if (key != null) break;
        }
    }

    private void printLine(int i, String line) {
        for (int j = 0; j < line.length(); j++) {
            _terminal.moveCursor(j, i);
            _terminal.putCharacter(line.charAt(j));
        }
    }
}
