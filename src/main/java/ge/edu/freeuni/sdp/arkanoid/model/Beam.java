package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;

public abstract class Beam extends Gobj<Rectangle> {

    private boolean _isAlive;
    private Room _room;

    Beam(Point position, Room room) {
        super(position);
        _isAlive = true;
        _room = room;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Beam;
    }

    public void interact(Gobj other) {

    }

    @Override
    public Rectangle getShape() {
        int height = getHeight();
        Size size = new Size(1, height);
        return new Rectangle(getPosition(), size);
    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    private int getHeight() {
        _room.getGobjs().stream().filter(obj -> obj instanceof Brick).forEach(obj -> {
            Brick brick = (Brick) obj;
            Point brickPosition = brick.getPosition();
            //Todo get lowest brick position at piont.Y
        });
        //Todo return distance from lowest brick to paddle
        return 10;
    }

}
