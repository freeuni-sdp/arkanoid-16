package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public abstract class GeneratorFrameBrick extends FrameBrick {

    protected final Room room;
    private final long generationDelay;
    private long lastGeneratedTime;

    public GeneratorFrameBrick(Point position, Size size, Room room, long generationDelay) {
        super(position, size);
        this.room = room;
        this.generationDelay = generationDelay;
        this.lastGeneratedTime = System.currentTimeMillis();
        generate();
    }

    @Override
    public void move() {
        long currTime = System.currentTimeMillis();

        if (currTime - lastGeneratedTime >= generationDelay) {
            generate();
            lastGeneratedTime = currTime;
        }
    }

    private void generate() {
        Size brickSize = getNormalBrickSize();
        Size roomSize = Configuration.getInstance().getSize();

        generate(brickSize, roomSize);
    }

    private Size getNormalBrickSize() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize();
    }

    protected abstract void generate(Size brickSize, Size roomSize);

}
