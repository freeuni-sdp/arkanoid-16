package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class SinkingWallLevelBuilder extends FrameBuilder {

    public SinkingWallLevelBuilder(Size size) {
        super(size);
    }

    @Override
    FrameBrick createTopFrameBrick(Point point, Size size, Room room) {
        return new SinkingWallGeneratorFrameBrick(point, size, room);
    }

}
