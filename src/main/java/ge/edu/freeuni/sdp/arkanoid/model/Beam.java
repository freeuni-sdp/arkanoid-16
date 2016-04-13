package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class Beam extends Gobj<Rectangle> {

    private boolean _isAlive;
    private Room _room;
    private int life_left = 3;

    Beam(Point position, Room room) {
        super(position);
        _isAlive = true;
        _room = room;
    }

    @Override
    public void move() {
        life_left--;
        if (life_left == 0)
            _isAlive = false;
    }

    @Override
    public GobjKind getKind() {
        return GobjKind.Beam;
    }

    public void interact(Gobj other) {
        if(other instanceof Brick) {
            Brick brick = (Brick) other;
            brick.interact(new Ball());
        }
    }

    @Override
    public Rectangle getShape() {
        int height = (int) getHeight();
        System.out.print(height);
        Size size = new Size(1, height);
        return new Rectangle(new Point(getPosition().X, getPosition().Y - height), size);
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
                double brickX = brick.getPosition().X;
                int brickWidth = brick.getShape().getSize().getWidth();
                double beamX = getPosition().X;
                if (beamX >= brickX && beamX <= brickX + brickWidth) {
                    if (lowestBrick == null)
                        lowestBrick = brick;
                    else if (lowestBrick.getPosition().Y > brick.getPosition().Y)
                        lowestBrick = brick;
                }
            }
        }
        return Math.abs(lowestBrick.getPosition().Y - getPosition().Y);
    }

}
