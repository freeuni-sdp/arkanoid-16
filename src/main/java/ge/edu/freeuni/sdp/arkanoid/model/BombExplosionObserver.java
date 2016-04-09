package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;

/**
 * Created by giorgi on 4/9/2016.
 * Interface for BombExplosionObservers, should be implemented by Room
 */
public interface BombExplosionObserver {
    void onBombExploded(Gobj exploder, BombBrick bombBrick);
}
