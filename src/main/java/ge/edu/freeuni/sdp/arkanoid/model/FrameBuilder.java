package ge.edu.freeuni.sdp.arkanoid.model;

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
        }
    }
}
