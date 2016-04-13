package ge.edu.freeuni.sdp.arkanoid.view.swing;

import com.googlecode.lanterna.input.Key;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.LevelSelectionListener;
import ge.edu.freeuni.sdp.arkanoid.view.LevelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by khrak on 4/12/16.
 */
public class SwingLevelView extends LevelView implements LevelSelectionListener{

    private final JFrame _frame;
    private int selectedLevel = 0;
    private Key p = null;
    private ArrayList<JLabel> labels = new ArrayList<JLabel>();

    public SwingLevelView(LevelPresenter presenter, JFrame frame) {
        super(presenter);
        _frame = frame;
        presenter.setLevelSelectionListener(this);
    }


    public void show() {
        draw();

        boolean isAccepted = false;


        while (!isAccepted) {

            if (p == null) continue;
            if (p.getKind() == Key.Kind.Enter) {
                isAccepted = true;
                _frame.getContentPane().removeAll();
                _frame.repaint();
                continue;
            }
            char ch = p.getCharacter();
            if (Character.isDigit(ch)) selectedLevel = Character.getNumericValue(ch) - 1;
            if (p.getKind() == Key.Kind.ArrowDown) selectedLevel++;
            if (p.getKind() == Key.Kind.ArrowUp) selectedLevel--;

            selectedLevel = Math.floorMod(selectedLevel, getPresenter().getLevelNames().length);
            p = null;
            getPresenter().setSelection(selectedLevel);
        }
    }


    private void draw() {


        _frame.setVisible(true);

        String[] names = getPresenter().getLevelNames();


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setVisible(true);


        panel.add(new JLabel("Start and enjoy game"));

        _frame.add(panel);
        _frame.setVisible(true);

        _frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == 10) {
                    p = new Key(Key.Kind.Enter);
                }
                if(e.getKeyCode() == 38) {
                    p = new Key(Key.Kind.ArrowUp);
                }
                if(e.getKeyCode() == 40) {
                    p = new Key(Key.Kind.ArrowDown);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        for (int i = 0; i < names.length; i++) {
            String prefix = "";
            if (getPresenter().getSelection() == i) prefix = "-->";
            String label_string = (String.format("%3$s [%1$s] - %2$s", i + 1, names[i], prefix));

            JLabel label = new JLabel(label_string);
            labels.add(label);
            panel.add(label);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        panel.setVisible(true);
        _frame.add(panel);
        _frame.setVisible(true);

    }

    private void update() {

        for(int i = 0; i < labels.size(); i++) {
            String text = labels.get(i).getText();

            if (text.startsWith("-->")) {
                labels.get(i).setText(text.substring(3));
            }
        }

        String chosenLevelText = labels.get(selectedLevel).getText();
        labels.get(selectedLevel).setText("-->" + chosenLevelText);
    }

    public void levelSelectionChanged() {
        update();
    }

}
