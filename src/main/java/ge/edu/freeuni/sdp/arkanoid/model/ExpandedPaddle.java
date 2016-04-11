package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class ExpandedPaddle extends Paddle {

    private static final int EXPANDED_WIDTH = 9;
    private static final int EXPANDED_HEIGHT = 1;

    ExpandedPaddle(Point position) {
        super(position);
    }

    @Override
    public void interact(Gobj other){
        super.interact(other);
        if (other instanceof FrameBrick){
            if (other.getPosition().X < getPosition().X + EXPANDED_WIDTH &&
                    other.getPosition().X > getPosition().X){
                setPosition(new Point(other.getPosition().X - EXPANDED_WIDTH,
                        getPosition().Y));
            }
        }
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle(getPosition(), new Size(EXPANDED_WIDTH,
                EXPANDED_HEIGHT));
    }
}
