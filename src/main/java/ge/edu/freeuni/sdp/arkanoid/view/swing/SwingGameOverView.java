package ge.edu.freeuni.sdp.arkanoid.view.swing;

import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;
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

    //radgan getValueFromDialogs ar vidzaxebt arsad, amitom show c ver moxvdeba coverageshi. xolo show-s shida meore metodi calke itesteba, amitom mshvidobaa mainc.
    @Override
    protected void show() {
        int n = this.getValueFromDialog();
        makeAppropriateAction(n);
    }

    protected void makeAppropriateAction(int n){
        if (n == 0)
            _presenter.setGameActivity(true);
        else {
            _presenter.setGameActivity(false);
            frame.setVisible(false);
            frame.dispose();
        }
    }

    //es metodi overageshi veranairad moxvdeba, radgan jFrame damockili unda iyos, radgan user irchevs actions.
    protected int getValueFromDialog() {
        return JOptionPane.showConfirmDialog(
                frame,
                "Would you like to play again ?",
                "Game is over",
                JOptionPane.YES_NO_OPTION);
    }

}
