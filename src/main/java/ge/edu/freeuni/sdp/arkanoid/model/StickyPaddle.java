package ge.edu.freeuni.sdp.arkanoid.model;
import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

/**
 * Created by dell on 4/12/16.
 */
public class StickyPaddle extends Paddle {
    private Paddle prevPaddle;
    private StickedBall stickedBall;
    private Room room;


    StickyPaddle( Room room, Paddle prevPaddle, Point position) {
        super(position);
        this.prevPaddle = prevPaddle;
        stickedBall = null;
        this.room = room;
    }

    @Override
    public void interact(Gobj other) {
        if (other instanceof FrameBrick) {
            SoundPlayer.getInstance().play(SoundPlayer.COLLIDE);
            setPosition(super.getPrevPosition());
        }

        if (other instanceof Ball) {
            Ball ball = (Ball) other;
            if(stickedBall == null) {
                stickedBall = new StickedBall(ball, ball.getSpeed());
                ball.setSpeed(new Speed(new Point(0,0)));

            }
            else{
                SoundPlayer.getInstance().play(SoundPlayer.PARRY);
                Speed newSpeed = ball.getSpeed().mirrorY();
                ball.setSpeed(newSpeed);
            }
        }
    }

    @Override
    public void move(){
        super.move();
        Point currPoint = super.getPrevPosition();
        if(stickedBall != null)
            stickedBall.getBall().setPosition(new Point(currPoint.X+1, currPoint.Y-1));
    }

    @Override
    void fire(){
        super.fire();
        if(stickedBall != null) {
            stickedBall.getBall().setSpeed(stickedBall.getSpeed().mirrorY());
            stickedBall = null;
        }
        prevPaddle.setAlive(true);
        room.add(prevPaddle);
        this.exchange(prevPaddle);
    }

    class StickedBall{
        Ball ball;
        Speed speed;
        StickedBall(Ball ball, Speed speed){
            this.ball = ball;
            this.speed = speed;
        }

        Speed getSpeed(){
            return speed;
        }

        Ball getBall(){
            return ball;
        }
    }
}
