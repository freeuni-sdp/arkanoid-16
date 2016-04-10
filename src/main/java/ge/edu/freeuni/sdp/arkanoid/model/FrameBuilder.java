package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class FrameBuilder implements RoomBuilder {

    private final Size _size;

    FrameBuilder(Size size) {
        _size = size;
    }

    public void build(Room room, ScoreCounter scoreCounter) {

        int h = _size.getHeight();
        int w = _size.getWidth();

        Size horizontalFrameBrickSize = new Size(w + 2, 1);

        Point bottomFrameBrickPosition = new Point(-1, h);
        Brick bottomBrick = createBottomFrameBrick(bottomFrameBrickPosition, horizontalFrameBrickSize, room);
        room.add(bottomBrick);

        Point topFrameBrickPosition = new Point(-1, -1);
        Brick topBrick = createTopFrameBrick(topFrameBrickPosition, horizontalFrameBrickSize, room);
        room.add(topBrick);

        Size verticalFrameBrickSize = new Size(1, h + 2);

        Point leftFrameBrickPosition = new Point(-1, -1);
        Brick leftBrick = createLeftFrameBrick(leftFrameBrickPosition, verticalFrameBrickSize, room);
        room.add(leftBrick);

        Point rightFrameBrickPosition = new Point(w, -1);
        Brick rightBrick = createRightFrameBrick(rightFrameBrickPosition, verticalFrameBrickSize, room);
        room.add(rightBrick);
    }

    private FrameBrick createFrameBrick(Point point, Size size) {
        return new FrameBrick(
                point,
                size);
    }

    FrameBrick createBottomFrameBrick(Point point, Size size, Room room) {
        return new KillerBrick(point, size, room);
    }

    FrameBrick createTopFrameBrick(Point point, Size size, Room room) {
        return createFrameBrick(point, size);
    }

    FrameBrick createRightFrameBrick(Point point, Size size, Room room) {
        return createFrameBrick(point, size);
    }

    FrameBrick createLeftFrameBrick(Point point, Size size, Room room) {
        return createFrameBrick(point, size);
    }
}
