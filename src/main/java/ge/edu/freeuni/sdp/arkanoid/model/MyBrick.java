package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 * Created by Koko on 12.04.2016.
 */
public class MyBrick extends Brick {

    private boolean _isAlive;
    private Capsule _capsule;
    private int n = 2;

    MyBrick(Point position, BrickColor color, Capsule capsule) {
        super(position, color, capsule);
        _isAlive = true;
        _capsule = capsule;
    }

    @Override
    public void interact(Gobj other) {
        n--;
        if (other instanceof Ball) {
            if (n < 1) {
                _capsule.release(getPosition());
                _isAlive = false;
            }
        } else if (other instanceof MovementKillerBrick){
            if (n < 1)
                _isAlive = false;
        }
    }
    @Override
    public Rectangle getShape() {
        return new Rectangle(getPosition(), new Size(5, 1));
    }

    @Override
    public boolean isKillable() {
        return true;
    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public void setN(int n){
        this.n = n;
    }
}
