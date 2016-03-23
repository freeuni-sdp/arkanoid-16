package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public class KillerBrick extends SolidBrick {
    public KillerBrick(Point position) {
        super(position);
    }

    @Override
    public void interactAt(Gobj other, Set<Point> intersection) {
        if (other.getKind() == GobjKind.Ball) {
            ((Ball) other).decLives();
        }
    }
}
