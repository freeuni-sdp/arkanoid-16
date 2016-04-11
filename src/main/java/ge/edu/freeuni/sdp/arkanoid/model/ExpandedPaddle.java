package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class ExpandedPaddle extends Paddle {

    private static final int _expandedWidth = 9;
    private static final int _expandedHeight = 1;

    ExpandedPaddle(Point position) {
        super(position);
    }

    @Override
    public void interact(Gobj other){
        super.interact(other);
        if (other instanceof FrameBrick){
            if (other.getPosition().X < getPosition().X + _expandedWidth &&
                    other.getPosition().X > getPosition().X){
                setPosition(new Point(other.getPosition().X - _expandedWidth,
                        getPosition().Y));
            }
        }
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle(getPosition(), new Size(_expandedWidth,
                _expandedHeight));
    }
}
