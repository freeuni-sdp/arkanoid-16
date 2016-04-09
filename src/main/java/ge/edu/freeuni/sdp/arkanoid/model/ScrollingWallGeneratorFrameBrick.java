package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by misho on 4/7/2016.
 */
public class ScrollingWallGeneratorFrameBrick extends GeneratorFrameBrick{
    private final static long SINKING_DELAY = 3000;
    private final static int COLUMN_LENGTH = 14;
    private final static int GAP = 2;

    public ScrollingWallGeneratorFrameBrick(Point position, Size size, Room room) {
        super(position, size, room, SINKING_DELAY);
    }

    @Override
    protected void generate(Size brickSize, Size roomSize) {
        for (int i = 0; i < COLUMN_LENGTH; i += GAP) {
            Point position = new Point(0, i);
            Capsule capsule = new ExpandCapsule(position, room);
            Brick current = new ScrollingBrick(position, BrickColor.Red, capsule);
            room.add(current);
        }
    }
}
