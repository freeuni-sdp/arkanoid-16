package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.ArrayList;

public class Level1Builder extends FrameBuilder {

    private DeathCountListener _countListener;
    private int _numGameBlocks;

    public Level1Builder(Size size) {
        super(size);
        _countListener = new DeathCountListener(0);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = getNormalBrickWidth();
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        _numGameBlocks = 0;
        createRow(room, brickDistance, roomWidth, 5);
        createRow(room, brickDistance, roomWidth, 4);
        _countListener.setCount(_numGameBlocks);
    }

    @Override
    public void setLevelOverListener(LevelOverListener listener) {
        _countListener.setLevelOverListener(listener);
    }

    private void createRow(Room room, int brickDistance, int roomWidth, int rowHeight) {
        for (int i = 0; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, rowHeight);
            Brick current = new NormalBrick(position, BrickColor.Red, new NullCapsule(null));
            current.setDeathListener(_countListener);
            _numGameBlocks++;
            room.add(current);
        }
    }

    private int getNormalBrickWidth() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize().getWidth();
    }
}

