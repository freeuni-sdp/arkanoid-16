package ge.edu.freeuni.sdp.arkanoid.model;

import java.util.Set;

public interface GameFacade {
    void init(Level level);

    void move(Direction direction);

    void fire();

    boolean isGameOver();

    Size getSize();

    Set<Gobj> getGobjs();
}