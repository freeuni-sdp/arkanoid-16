package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by misho on 4/7/2016.
 */
public class ScrollingWallLevelBuilder extends FrameBuilder {

    public ScrollingWallLevelBuilder(Size size) {
        super(size);
    }

    @Override
    FrameBrick createLeftFrameBrick(Point point, Size size, Room room) {
        return new ScrollingWallGeneratorFrameBrick(point, size, room);
    }

    @Override
    FrameBrick createRightFrameBrick(Point point, Size size, Room room) {
        return new KillerBrick(
                point,
                size);
    }

    @Override
    public void setLevelClearedListener(LevelClearedListener listener) {
        // TODO add listener
    }
}
