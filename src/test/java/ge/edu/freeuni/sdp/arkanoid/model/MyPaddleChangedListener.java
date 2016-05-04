package ge.edu.freeuni.sdp.arkanoid.model;

/**
 * Created by Tornike on 04.05.2016.
 */
public class MyPaddleChangedListener implements PaddleChangedListener {
    public boolean changed = false;
    public void paddleChanged(Paddle newPaddle){
        changed = true;
    }
}
