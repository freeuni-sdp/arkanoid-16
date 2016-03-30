package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class TestFrameBuilder implements RoomBuilder {
    private Size _size;

    public TestFrameBuilder(Size size) {
        _size = size;
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        int h = _size.getHeight();
        int w = _size.getWidth();

        //NOTE: Bottom is not a killer brick good for testing
        Brick bottomBrick = new FrameBrick(
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
