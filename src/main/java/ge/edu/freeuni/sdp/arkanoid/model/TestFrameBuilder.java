package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class TestFrameBuilder extends FrameBuilder {

    TestFrameBuilder(Size size) {
        super(size);
    }

    @Override
    protected FrameBrick createBottomFrameBrick(Point bottomFrameBrickPosition, Size horizontalFrameBrickSize) {
        return new FrameBrick(bottomFrameBrickPosition, horizontalFrameBrickSize);
    }
}
