package ge.edu.freeuni.sdp.arkanoid.view.swing;

import ge.edu.freeuni.sdp.arkanoid.presenter.*;
import ge.edu.freeuni.sdp.arkanoid.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaneki on 4/12/16.
 */
public class SwingRoomView extends RoomView implements CellUpdateListener, StatusUpdateListener {

    private final int PRECISION = 5;
    private volatile KeyListener key;
    private JFrame frame;
    private JPanel mainPanel;
    private List<List<JPanel>> panels;


    private boolean left = false;
    private boolean right = false;
    private boolean fire = false;
    private boolean pause = false;
    private boolean escape = false;

    protected SwingRoomView(RoomPresenter presenter, JFrame frame) {
        super(presenter);
        this.frame = frame;
        key = new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                    right = true;
                }
                if (KeyEvent.VK_LEFT == e.getKeyCode()) {
                    left = true;
                }
                if (KeyEvent.VK_UP == e.getKeyCode()) {
                    fire = true;
                }

                if (KeyEvent.VK_P == e.getKeyCode()) {
                    pause = true;
                }

                if (KeyEvent.VK_ESCAPE == e.getKeyCode()) {
                    escape = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                    right = false;
                }

                if (KeyEvent.VK_LEFT == e.getKeyCode()) {
                    left = false;
                }

                if (KeyEvent.VK_UP == e.getKeyCode()) {
                    fire = false;
                }

            }
        };

        presenter.set_cellUpdateListener(this);
        presenter.setStatusUpdateListener(this);

        int width = frame.getWidth() / PRECISION;
        int height = frame.getHeight() / PRECISION;

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(height, width));
        panels = new ArrayList<>();

        JPanel panel;
        for (int i = 0; i < height; i++){
            List<JPanel> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                panel = new JPanel();
                panel.setBackground(Color.BLACK);
                row.add(panel);
                mainPanel.add(panel);
            }
            panels.add(row);
        }
        
        frame.add(mainPanel);
        frame.addKeyListener(key);
    }

    @Override
    protected void show() {

        RoomPresenter presenter = getPresenter();
        presenter.init();

        frame.setVisible(true);
        frame.setFocusable(true);
        while (true) {
            Command command = Command.None;
            if (presenter.isGameOver()) break;

            if (right) command = Command.Right;
            if (left) command = Command.Left;
            if (fire) command = Command.Fire;
            if (pause) {
                command = Command.Pause;
                presenter.execute(command);
                JOptionPane.showMessageDialog(frame,
                        "To continue press OK button.",
                        "Game paused",
                        JOptionPane.PLAIN_MESSAGE);
                pause = false;
            }
            if (escape) {
                int n = JOptionPane.showConfirmDialog(
                        frame,
                        "Would you End the game? ",
                        "Exit: ",
                        JOptionPane.YES_NO_OPTION);
                escape = false;
                if (n == 0)
                    break;
            }
            presenter.execute(command);
            sleep();
        }

        mainPanel.setVisible(false);
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCell(CellPosition position, CellContent content) {

        JPanel panel = panels.get(position.y / PRECISION).get(position.x / PRECISION);
        panel.setVisible(false);

        switch (content) {
            case None:
                panel.setBackground(Color.BLACK);
                break;
            case GrayBrick:
                panel.setBackground(Color.YELLOW);
                break;
            case RedBrick:
                panel.setBackground(Color.RED);
                break;
            case BlueBrick:
                panel.setBackground(Color.BLUE);
                break;
            case WhiteBrick:
                panel.setBackground(Color.WHITE);
                break;
            case OrangeBrick:
                panel.setBackground(Color.MAGENTA);
                break;
            case LightBlueBrick:
                panel.setBackground(Color.CYAN);
                break;
            case GreenBrick:
                panel.setBackground(Color.GREEN);
                break;
            case DarkBlueBrick:
                panel.setBackground(Color.BLUE);
                break;
            case LightOrangeBrick:
                panel.setBackground(Color.MAGENTA);
                break;
            case YellowBrick:
                panel.setBackground(Color.YELLOW);
                break;
            case GoldBrick:
                panel.setBackground(Color.YELLOW);
                break;
            case Paddle:
                panel.setBackground(Color.GREEN);
                break;
            case Ball:
                panel.setBackground(Color.WHITE);
                break;
            case ECapsule:
                panel.setBackground(Color.WHITE);
                break;
            case BCapsule:
                panel.setBackground(Color.GREEN);
                break;
            case ACapsule:
                panel.setBackground(Color.CYAN);
                break;
            case PCapsule:
                panel.setBackground(Color.WHITE);
                break;
            case DCapsule:
                panel.setBackground(Color.GREEN);
                break;
            case Beam:
                panel.setBackground(Color.BLACK);
                break;
            case KillCapsule:
                panel.setBackground(Color.WHITE);
                break;
            case SCapsule:
                panel.setBackground(Color.MAGENTA);
                break;
            case LCapsule:
                panel.setBackground(Color.YELLOW);
                break;
        }

//        panel.repaint();
        panel.setVisible(true);

    }

    @Override
    public void updateScore(int score) {

    }

    @Override
    public void updateLives(int lives) {

    }
}