package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.stream.Stream;

/**
 * Created by misho on 4/7/2016.
 */
public class MovementKillerBrick extends FrameBrick {

    private final Room room;

    public MovementKillerBrick(Point position, Size size, Room room) {
        super(position, size);
        this.room = room;
    }

    @Override
    public void interact(Gobj other) {
        if (other instanceof MovingBrick) {
            if (killableLeft()) {
                killAllKillable();
                decreaseLevelIndex();
                decreasePaddleLifes();
            }
        } else {
            super.interact(other);
        }
    }

    private void decreasePaddleLifes() {
        // TODO Decrease paddle lifes
    }

    private boolean killableLeft() {
        return room.getGobjs().stream().filter(g -> (g.isAlive() && g.isKillable())).count() > 0;
    }

    private void killAllKillable() {
        room.getGobjs().stream().filter(Gobj::isKillable).forEach(g -> g.interact(this));
    }

    private void decreaseLevelIndex() {
        // HACK solution
        int selectedLevelIndex = Configuration.getInstance().getSelectedLevelIndex();
        Configuration.getInstance().setSelectedLevelIndex(selectedLevelIndex - 1);
    }
}
