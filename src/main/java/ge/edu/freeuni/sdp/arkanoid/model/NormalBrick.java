package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Shape;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class NormalBrick extends Brick {

    public NormalBrick(Point position, BrickColor color, Capsule capsule) {
        super(position, color, capsule);
    }

    @Override
    public Shape getShape() {
        return new Rectangle(getPosition(), new Size(3, 1));
    }
}
