package ge.edu.freeuni.sdp.arkanoid.model;

/**
 * Created by rezo on 4/10/16.
 */
public class LiveCounter {
    private int _live;

    public LiveCounter(){
        this._live = 3;
    }

    public LiveCounter(int lives){
        this._live = lives;
    }

    public int getLive() {
        return _live;
    }


    public void increase(){
       this._live++;
    }

    public void decrease(){
        this._live = Math.max(this._live-1, 0);
    }
}
