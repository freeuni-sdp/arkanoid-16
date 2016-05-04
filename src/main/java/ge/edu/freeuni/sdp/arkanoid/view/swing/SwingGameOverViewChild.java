package ge.edu.freeuni.sdp.arkanoid.view.swing;

import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;

import javax.swing.*;

/**
 * Created by GM on 5/4/2016.
 */
public class SwingGameOverViewChild extends SwingGameOverView {

    private  JFrame frame;
    private final GameOverPresenter _presenter;
    private int mockValue;

    public SwingGameOverViewChild(GameOverPresenter presenter, JFrame frame) {
        super(presenter, frame);
        this.frame = frame;
        _presenter = presenter;
        mockValue = 0;
    }

    public void publicShow(){
        int n = this.getValueFromDialog();
        super.makeAppropriateAction(n);
    }

    @Override
    public int getValueFromDialog(){
        return this.mockValue;
    }


    public void setMockValue(int n){
        this.mockValue = n;
    }

}
