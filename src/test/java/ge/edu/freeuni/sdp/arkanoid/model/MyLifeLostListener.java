package ge.edu.freeuni.sdp.arkanoid.model;

/**
 * Created by Tornike on 04.05.2016.
 */
public class MyLifeLostListener implements LifeLostListener {
    public boolean lost = false;
    public void lifeLost(){
        lost = true;
    }
}
