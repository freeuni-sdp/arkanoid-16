package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created By Nika Doghonadze 4/10/2016.
 */
public class TestACapsuleLevelBuilder extends TestFrameBuilder {
    public TestACapsuleLevelBuilder(Size size) {
        super(size);
    }

    @Override
    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = 4;
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        for (int i = 0; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, 5);
            Capsule capsule = new LaserCapsule(position, room);
            Brick current = new NormalBrick(position, BrickColor.Red, capsule);
            room.add(current);
        }
    }
}
