package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class SinkingWallGeneratorFrameBrick extends GeneratorFrameBrick {

    public SinkingWallGeneratorFrameBrick(Point position, Size size, Room room) {
        super(position, size, room);
    }

    @Override
    protected void generate(Size brickSize, Size roomSize) {
        for (int i = 0; i < roomSize.getWidth(); i += brickSize.getWidth()) {
            Point position = new Point(i, 0);
            Capsule capsule = new ExpandCapsule(position, room);
            Brick current = new SinkingBrick(position, BrickColor.Red, capsule);
            room.add(current);
        }
    }
}
