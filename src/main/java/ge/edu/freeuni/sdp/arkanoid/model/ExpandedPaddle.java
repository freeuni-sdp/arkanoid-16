package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class ExpandedPaddle extends Paddle {
    ExpandedPaddle(Point position) {
        super(position);
    }

    @Override
    public Rectangle getShape() {
        return new Rectangle(getPosition(), new Size(9, 1));
    }
}
