package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class Level1Builder extends FrameBuilder {

    public Level1Builder(Size size) {
        super(size);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = getNormalBrickWidth();
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        createRow(room, brickDistance, roomWidth, 5);
        createRow(room, brickDistance, roomWidth, 4);
    }

    private void createRow(Room room, int brickDistance, int roomWidth, int rowHeight) {
        for (int i = 1; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, rowHeight);
            Brick current = new NormalBrick(position, BrickColor.Red, new NullCapsule(null));
            room.add(current);
        }
    }

    private int getNormalBrickWidth() {
        return ((Rectangle) new NormalBrick(null, BrickColor.Red, null).getShape()).getSize().getWidth();
    }
}

