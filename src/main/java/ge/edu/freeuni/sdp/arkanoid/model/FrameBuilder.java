package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public abstract class FrameBuilder implements RoomBuilder {

    private final Size _size;

    public FrameBuilder(Size size) {
        _size = size;
    }

    public void build(Room room, ScoreCounter scoreCounter) {

        int h = _size.getHeight();
        int w = _size.getWidth();

        //NOTE: Bottom is a killer brick
        Brick bottomBrick = new KillerBrick(
                new Point(-1, h),
                new Size(w, 1));
        room.add(bottomBrick);

        Brick topBrick = new FrameBrick(
                new Point(-1, -1),
                new Size(w, 1));
        room.add(topBrick);


        Brick leftBrick = new FrameBrick(
                new Point(-1, 0),
                new Size(1, h - 1));
        room.add(leftBrick);

        Brick rightBrick = new FrameBrick(
                new Point(w, 0),
                new Size(1, h - 1));
        room.add(rightBrick);
    }
}
