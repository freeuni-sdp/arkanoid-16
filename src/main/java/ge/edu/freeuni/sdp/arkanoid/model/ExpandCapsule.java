package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

public class ExpandCapsule extends Capsule {

    ExpandCapsule(Point position, Room room) {
        super(position, room);
    }

    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Paddle) {
            Paddle oldPaddle = (Paddle) other;
            int oldPaddleWidth = oldPaddle.getShape().getSize().getWidth();
            int expandedPaddleWidth = getExpandedPaddleWidth();
            Point newPosition = getNewPosition(other.getPosition(), oldPaddleWidth, expandedPaddleWidth);
            Paddle newPaddle = new ExpandedPaddle(newPosition);
            oldPaddle.exchange(newPaddle);
            SoundPlayer.getInstance().play(SoundPlayer.EXTEND);
        }
    }

    private Point getNewPosition(Point oldPosition, int oldPaddleWidth, int expandedPaddleWidth) {
        Point newPosition = new Point(oldPosition);

        if (oldPosition.X <= 0) {
            newPosition.X += (expandedPaddleWidth - oldPaddleWidth) / 2;
        } else if (oldPosition.X + oldPaddleWidth >= Configuration.getInstance().getSize().getWidth()) {
            newPosition.X -= (expandedPaddleWidth - oldPaddleWidth) / 2;
        }

        return newPosition;
    }

    private int getExpandedPaddleWidth() {
        return new ExpandedPaddle(null).getShape().getSize().getWidth();
    }
}
