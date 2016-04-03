package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public abstract class FrameBuilder implements RoomBuilder {

    private final Size _size;

    FrameBuilder(Size size) {
        _size = size;
    }

    public void build(Room room, ScoreCounter scoreCounter) {

        int h = _size.getHeight();
        int w = _size.getWidth();

        Size horizontalFrameBrickSize = new Size(w + 2, 1);

        Point bottomFrameBrickPosition = new Point(-1, h);
        Brick bottomBrick = createBottomFrameBrick(bottomFrameBrickPosition, horizontalFrameBrickSize);
        room.add(bottomBrick);

        Point topFrameBrickPosition = new Point(-1, -1);
        Brick topBrick = createFrameBrick(topFrameBrickPosition, horizontalFrameBrickSize);
        room.add(topBrick);

        Size verticalFrameBrickSize = new Size(1, h + 2);

        Point leftFrameBrickPosition = new Point(-1, -1);
        Brick leftBrick = createFrameBrick(leftFrameBrickPosition, verticalFrameBrickSize);
        room.add(leftBrick);

        Point rightFrameBrickPosition = new Point(w, -1);
        Brick rightBrick = createFrameBrick(rightFrameBrickPosition, verticalFrameBrickSize);
        room.add(rightBrick);
    }

    private FrameBrick createFrameBrick(Point point, Size size) {
        return new FrameBrick(
                point,
                size);
    }

    FrameBrick createBottomFrameBrick(Point point, Size size) {
        return new KillerBrick(
                point,
                size);
    }
}
