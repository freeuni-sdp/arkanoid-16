package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class TestFrameBuilder extends FrameBuilder {
    private Size _size;

    TestFrameBuilder(Size size) {
        super(size);
    }

    @Override
    protected FrameBrick createBottomFrameBrick(Point bottomFrameBrickPosition, Size horizontalFrameBrickSize, Room room) {
        return new FrameBrick(bottomFrameBrickPosition, horizontalFrameBrickSize);
    }
}
