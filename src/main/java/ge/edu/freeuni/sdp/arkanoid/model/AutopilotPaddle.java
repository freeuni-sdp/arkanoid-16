package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class AutopilotPaddle extends TimerPaddle {
    private Room _room;

    public AutopilotPaddle(Point position, int ttl, Capsule capsule, Room room) {
        super(position, ttl, capsule);
        _room = room;
    }

    @Override
    void setSpeed(Speed _speed) {
        Ball closestBall = getClosestBall();

        Speed newSpeed;
        Point ballPosition = closestBall.getPosition();
        Point paddleCenterPosition = getPosition().add(new Point(getShape().getSize().getWidth()/2, 0));

        double xDistance = ballPosition.X - paddleCenterPosition.X;
        if (Math.abs(xDistance) < getShape().getSize().getWidth()/2) {
            newSpeed = new Speed(new Point(0, 0));
        } else {
            newSpeed = new Speed(new Point(Math.signum(xDistance) ,0));
        }


        super.setSpeed(newSpeed);
    }

    private Ball getClosestBall() {
        List<Ball> ballList = _room.getGobjs()
                .stream()
                .filter(obj -> obj instanceof Ball)
                .map(obj -> (Ball) obj)
                .collect(Collectors.toList());

        Ball closestBall = ballList.get(0);
        double min_distance = getPosition().getDistance(closestBall.getPosition());
        for (Ball b: ballList) {
            double cur_distance = getPosition().getDistance(closestBall.getPosition());
            if (cur_distance < min_distance) {
                closestBall = b;
                min_distance = cur_distance;
            }
        }
        return closestBall;
    }
}
