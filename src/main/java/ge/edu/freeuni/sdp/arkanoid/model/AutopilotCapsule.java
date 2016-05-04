package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class AutopilotCapsule extends Capsule {

    static {
        CapsuleFactory.getInstance().registerCapsuleType(CapsuleType.Autopilot, new AutopilotCapsule());
    }

    private Paddle newPaddle;
    
    private AutopilotCapsule() {
        super(null, null);
    }

    AutopilotCapsule(Point point, Room room) {
        super(point, room);
    }

    @Override
    public Capsule createCapsule(Point position, Room room) {
        return new AutopilotCapsule(position, room);
    }

    public void setNewPaddle(Paddle newPaddle){
        this.newPaddle = newPaddle;
    }
    
    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            Paddle oldPaddle = (Paddle) other;
            
            if (newPaddle == null){
                Capsule returnCapsule = new ReturnCapsule(getPosition(), _room, oldPaddle, 0.5);
                newPaddle = new AutopilotPaddle(other.getPosition(), 5000, returnCapsule, _room);
            }
            
            oldPaddle.exchange(newPaddle);

            //double speed of ball
            _room.getGobjs()
                    .stream()
                    .filter(obj -> obj instanceof Ball)
                    .forEach(obj -> {
                Ball ball = (Ball) obj;
                Speed ballSpeed = ball.getSpeed();
                ballSpeed.setLength(ballSpeed.getLength()*2);
                ball.setSpeed(ballSpeed);
            });
            SoundPlayer.getInstance().play(SoundPlayer.AUTOPILOT_START);
        }
    }
    
    public boolean equals(AutopilotCapsule other){
        return this.getPosition().equals(other.getPosition());
    }
}
