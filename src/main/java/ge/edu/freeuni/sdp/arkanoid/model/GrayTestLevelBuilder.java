package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by Koko on 12.04.2016.
 */
public class GrayTestLevelBuilder extends FrameBuilder {
    public GrayTestLevelBuilder(Size size) {
        super(size);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = getNormalBrickWidth();
        System.out.println("width: " + brickDistance);
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        createRow(room, brickDistance, roomWidth, 5);
        createRow(room, brickDistance, roomWidth, 4);
    }

    private void createRow(Room room, int brickDistance, int roomWidth, int rowHeight) {
        for (int i = 0; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, rowHeight);
//            Brick current = new NormalBrick(position, BrickColor.Gray, new NullCapsule(null));
            Brick koko = new MyBrick(position, BrickColor.Gray, new NullCapsule(null));
//            room.add(current);
            room.add(koko);
        }
    }

    private int getNormalBrickWidth() {
        return new MyBrick(null, BrickColor.Gray, null).getShape().getSize().getWidth();
    }
}
