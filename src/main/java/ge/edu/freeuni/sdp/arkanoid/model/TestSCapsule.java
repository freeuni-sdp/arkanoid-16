package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by nino on 12.04.2016.
 */
public class TestSCapsule extends TestFrameBuilder{

    public TestSCapsule(Size size) {
        super(size);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = 4;
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        for (int i = 0; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, 5);
            Capsule capsule = new SlowBallCapsule(position, room);
            Brick current = new NormalBrick(position, BrickColor.Red, capsule);
            room.add(current);
        }
    }

}
