package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by Nikoloz on 04/12/16.
 */
public class LevelKillCapsuleBuilder extends FrameBuilder {
    public LevelKillCapsuleBuilder(Size size) {
        super(size);
    }
    @Override
    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = getNormalBrickWidth();
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        createRow(room, brickDistance, roomWidth, 4);
    }

    private void createRow(Room room, int brickDistance, int roomWidth, int rowHeight) {
        for (int i = 0; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, rowHeight);
            Capsule capsule = new KillCapsule(position, room);
            Brick current = new NormalBrick(position, BrickColor.Red, capsule);
            room.add(current);
        }
    }

    private int getNormalBrickWidth() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize().getWidth();
    }
}
