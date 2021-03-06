package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelSelectionListener;
import ge.edu.freeuni.sdp.arkanoid.view.LevelView;

class TerminalLevelView extends LevelView implements LevelSelectionListener {

    private final Terminal _terminal;
    private int selectedLevel = 0;

    public TerminalLevelView(LevelPresenter presenter, Terminal terminal) {
        super(presenter);
        _terminal = terminal;
        presenter.setLevelSelectionListener(this);
    }

    public void show() {
        draw();
        
        boolean isAccepted = false;

        while (!isAccepted) {
            Key p = _terminal.readInput();
            if (p == null) continue;
            if (p.getKind() == Key.Kind.Enter) {
                isAccepted = true;
                continue;
            }
            char ch = p.getCharacter();
            if (Character.isDigit(ch)) selectedLevel = Character.getNumericValue(ch) - 1;
            if (p.getKind() == Key.Kind.ArrowDown) selectedLevel++;
            if (p.getKind() == Key.Kind.ArrowUp) selectedLevel--;

            selectedLevel = Math.floorMod(selectedLevel, getPresenter().getLevelNames().length);

            getPresenter().setSelection(selectedLevel);
        }
    }

    private void draw() {
        _terminal.clearScreen();
        String[] names = getPresenter().getLevelNames();

        _terminal.moveCursor(4, 4);
        writeLine("Press number to select. Press ENTER to confirm.");

        for (int i = 0; i < names.length; i++) {
            _terminal.moveCursor(6, 6 + i);
            char prefix = ' ';
            if (getPresenter().getSelection() == i) prefix = '>';
            writeLine(String.format("%3$c [%1$s] - %2$s", i + 1, names[i], prefix));
        }

        _terminal.flush();
    }

    private void writeLine(String print) {
        char[] printToChar = print.toCharArray();
        for (int i = 0; i < print.length(); i++) {
            _terminal.putCharacter(printToChar[i]);
        }
    }

    public void levelSelectionChanged() {
        draw();
    }
}
