package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.Timer;

public class SinkingWallGeneratorFrameBrick extends FrameBrick {

    private final static int SINKING_DELAY = 10000;
    private final Room room;
    private long lastGeneratedTime;

    public SinkingWallGeneratorFrameBrick(Point position, Size size, Room room) {
        super(position, size);
        this.room = room;
        lastGeneratedTime = System.currentTimeMillis();
        produceRow();
    }

    @Override
    public void move() {
        long currTime = System.currentTimeMillis();

        if (currTime - lastGeneratedTime >= SINKING_DELAY) {
            produceRow();
            lastGeneratedTime = currTime;
        }
    }

    private void produceRow() {
        Size brickSize = getNormalBrickSize();
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        createRow(brickSize.getWidth(), brickSize.getHeight(), roomWidth);
    }

    private Size getNormalBrickSize() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize();
    }

    private void createRow(int brickWidth, int brickHeight, int roomWidth) {
        for (int i = 0; i < roomWidth; i += brickWidth) {
            Point position = new Point(i, 0);
            Capsule capsule = new ExpandCapsule(position, room);
            Brick current = new SinkingBrick(position, BrickColor.Red, capsule);
            room.add(current);
        }
    }

}
