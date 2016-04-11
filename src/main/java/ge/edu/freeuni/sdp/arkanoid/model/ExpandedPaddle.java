package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class ExpandedPaddle extends Paddle {
    ExpandedPaddle(Point position) {
        super(position);
    }

    @Override
    public void interact(Gobj other){
        super.interact(other);
        if (other instanceof FrameBrick){
            if (other.getPosition().X < getPosition().X + 9){
                setPosition(new Point(other.getPosition().X - 9, getPosition()
                        .Y));
            }
        }
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle(getPosition(), new Size(9, 1));
    }
}
