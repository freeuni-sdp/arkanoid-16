package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class Beam extends Gobj<Rectangle> {

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
        int height = (int) getHeight();
        System.out.print(height);
        Size size = new Size(1, height);
        return new Rectangle(getPosition(), size);
    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    private double getHeight() {
        Brick lowestBrick = null;

        for(Gobj obj : _room.getGobjs()) {
            if(obj instanceof  Brick) {
                Brick brick = (Brick) obj;
                double brickY = brick.getPosition().Y;
                //Todo get lowest brick position at piont.Y
                int brickWidth = brick.getShape().getSize().getWidth();
                double beamY = getPosition().Y;
                if (beamY >= brickY && beamY <= brickY + brickWidth) {
                    if (lowestBrick == null)
                        lowestBrick = brick;
                    else if (lowestBrick.getPosition().X > brick.getPosition().X)
                        lowestBrick = brick;
                }
            }

        }
        //Todo return distance from lowest brick to paddle

        return lowestBrick.getPosition().X - getPosition().X;
    }

}
