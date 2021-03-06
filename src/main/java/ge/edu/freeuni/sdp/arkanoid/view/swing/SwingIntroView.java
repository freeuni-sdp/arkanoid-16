package ge.edu.freeuni.sdp.arkanoid.view.swing;

import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.input.Key;
import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.presenter.IntroPresenter;
import ge.edu.freeuni.sdp.arkanoid.view.IntroView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


class SwingIntroView extends IntroView{
    private final String _spaces;
    private final ClassLoader _classLoader;
    private JFrame _frame;
    private int _row;
    private volatile Key key = null;

    SwingIntroView(IntroPresenter presenter, JFrame frame) {
        super(presenter);
        _frame = frame;
        int nrOfSpaces = 20;
        _spaces = String.format("%" + nrOfSpaces + "s", "");
        _row = 0;
        _classLoader = getClass().getClassLoader();
    }

    @Override
    protected void show() {

        drawSplashScreen();

        // SoundPlayer.getInstance().loop(SoundPlayer.INTRO);

        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                key = new Key(e.getKeyChar());


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        _frame.addKeyListener(listener);

        while (key == null);


        _frame.removeKeyListener(listener);
        _frame.getContentPane().removeAll();
        _frame.repaint();


        // SoundPlayer.getInstance().stopAll();
        //SoundPlayer.getInstance().play(SoundPlayer.START);
    }


    private void drawSplashScreen() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setVisible(true);

        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Start and enjoy game"));

        _frame.add(panel);
        _frame.setVisible(true);

    }
}
