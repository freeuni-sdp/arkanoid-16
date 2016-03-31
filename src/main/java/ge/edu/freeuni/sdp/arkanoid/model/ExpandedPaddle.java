package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class ExpandedPaddle extends Paddle {
    protected ExpandedPaddle(Point position) {
        super(position);
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), new Size(9, 1));
    }
}
