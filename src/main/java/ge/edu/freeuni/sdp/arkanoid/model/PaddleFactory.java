package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created by giorgi on 4/30/2016.
 */
public class PaddleFactory {
    private static PaddleFactory _instance;
    public static PaddleFactory getInstance(){
        if (_instance == null)
            _instance = new PaddleFactory();
        return _instance;
    }

    private PaddleFactory() {}

    public Paddle createBallSpeedRestoringTimerPaddle(Point position, Room room, Paddle oldPaddle){
        Capsule returnCapsule = new ReturnCapsule(position, room, oldPaddle, 2);
        return new TimerPaddle(oldPaddle.getPosition(), 5000, returnCapsule);
    }
}
