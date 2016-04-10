package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by rezo on 4/10/16.
 */
public class FrameBuilderWithLives extends FrameBuilder {

    public FrameBuilderWithLives(Size size) {
        super(size);
    }

    @Override
    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        createBrick(room,5);
    }

    private void createBrick(Room room, int rowHeight) {
        addOne(room, 70,rowHeight);
        addOne(room, 10,rowHeight);
        addOne(room, 20,rowHeight);
        addOne(room, 30,rowHeight);
        addOne(room, 40,rowHeight);
        addOne(room, 50,rowHeight);
        addOne(room, 60,rowHeight);
        addOne(room, 60,rowHeight);
        addOne(room, 60,rowHeight);
        addOne(room, 60,rowHeight);
        addOne(room, 60,rowHeight);
        addOne(room, 80,rowHeight);
        addOne(room, 85,rowHeight);
        addOne(room, 90,rowHeight);

    }

    private void addOne(Room room, int x , int y) {

        Point position = new Point(x, y);
        Brick current = new NormalBrick(position, BrickColor.Red, new ExtraLiveCapsule(position, room));
        room.add(current);
    }
}
