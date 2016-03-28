package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class FrameBuilder implements RoomBuilder {

    private final Size _size;

    public FrameBuilder(Size size) {
        _size = size;
    }

    public void build(Room room) {
        for (int i = -1; i < _size.getWidth() + 1; i++) {
            //NOTE: Bottom is a killer brick
            Brick bottomBrick = new KillerBrick(new Point(i, _size.getHeight()));
            room.add(bottomBrick);

            Brick topBrick = new SolidBrick(new Point(i, -1));
            room.add(topBrick);

        }

        for (int j = 0; j < _size.getHeight(); j++) {
            //NOTE: Bottom is a killer brick
            Brick leftBrick = new SolidBrick(new Point(-1, j));
            room.add(leftBrick);

            Brick rightBrick = new SolidBrick(new Point(_size.getWidth(), j));
            room.add(rightBrick);

        }
    }
}
