package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.Set;

public interface GameFacade {
    void init(Level level);

    void move(Direction direction);

    void fire();

    boolean isGameOver();

    Size getSize();

    Set<Gobj> getGobjs();
}