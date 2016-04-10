package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by Giorgi on 4/10/2016.
 */
public class LevelBreakCapsuleBuilder extends FrameBuilder{

    public LevelBreakCapsuleBuilder(Size size) {
        super(size);
    }

    @Override
    public void build(Room room, ScoreCounter scoreCounter){
        super.build(room, scoreCounter);
        int brickDistance = 2*getNormalBrickWidth();
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        createRow(room, brickDistance, roomWidth, 5, 0);
        createRow(room, brickDistance, roomWidth, 4, brickDistance/2);
    }

    private void createRow(Room room, int brickDistance, int roomWidth, int
            rowHeight, int initPosition) {
        for (int i = initPosition; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, rowHeight);
            Capsule capsule;
            if (i%(2*brickDistance) == 0 || (i-initPosition)%
                    (2*brickDistance) == 0){
                capsule = new BreakCapsule(position, room);
            }else {
                capsule = new NullCapsule(null);
            }
            Brick current = new NormalBrick(position, BrickColor.Red,
                    capsule);
            room.add(current);
        }
    }

    private int getNormalBrickWidth() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize().getWidth();
    }
}

