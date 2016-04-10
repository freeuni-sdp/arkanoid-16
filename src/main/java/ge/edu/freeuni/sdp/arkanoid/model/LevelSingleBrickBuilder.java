package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class LevelSingleBrickBuilder extends FrameBuilder {

    public LevelSingleBrickBuilder(Size size) {
        super(size);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = getNormalBrickWidth();
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        createBrick(room, 5);
    }

    private void createBrick(Room room, int rowHeight) {
            Point position = new Point(70, rowHeight);
            Brick current = new NormalBrick(position, BrickColor.Red, new NullCapsule(null));
            room.add(current);
    }

    private int getNormalBrickWidth() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize().getWidth();
    }
}

