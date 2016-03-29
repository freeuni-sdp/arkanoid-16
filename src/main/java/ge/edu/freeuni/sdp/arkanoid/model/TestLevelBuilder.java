package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class TestLevelBuilder extends FrameBuilder {

    public TestLevelBuilder(Size size) {
        super(size);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = 5;
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        for (int i = 1; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, 5);
            Capsule capsule = new ExpandCapsule(position, room);
            Brick current = new NormalBrick(position, BrickColor.Red, capsule);
            room.add(current);
        }
    }
}
