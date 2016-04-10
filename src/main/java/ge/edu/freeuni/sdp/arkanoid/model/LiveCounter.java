package ge.edu.freeuni.sdp.arkanoid.model;

/**
 * Created by rezo on 4/10/16.
 */
public class LiveCounter {
    private int _live;


    public int getLive() {
        return _live;
    }


    public int incLive(int live){
       return this._live += live;

    }

}
