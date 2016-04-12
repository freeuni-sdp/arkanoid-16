package ge.edu.freeuni.sdp.arkanoid.view.Swing;

import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
import ge.edu.freeuni.sdp.arkanoid.presenter.Presenter;
import ge.edu.freeuni.sdp.arkanoid.view.GameOverView;

import javax.swing.*;

/**
 * Created by kaneki on 4/13/16.
 */
public class SwingGameOverView extends GameOverView {

    private  JFrame frame;
    private final GameOverPresenter _presenter;

    public SwingGameOverView(GameOverPresenter presenter, JFrame frame) {
        super(presenter);
        this.frame = frame;
        _presenter = presenter;
    }

    @Override
    protected void show() {

        int n = JOptionPane.showConfirmDialog(
                frame,
                "Would you like to play again ?",
                "Game is over",
                JOptionPane.YES_NO_OPTION);
        if (n == 0)
            _presenter.setGameActivity(true);
        else {
            _presenter.setGameActivity(false);
            frame.setVisible(false);
            frame.dispose();
        }
    }
}
