package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giorgi on 4/9/2016.
 */
public class BombBrick extends NormalBrick {
    private List<BombExplosionObserver> _bombExplosionObservers;
    private double _explosionWidth;
    private double _explosionHeight;

    public BombBrick(Point position, BrickColor color, Capsule capsule, int explosionRadius) {
        super(position, color, capsule);
        _bombExplosionObservers = new ArrayList<>();
        setupExplosionBoundaries(explosionRadius);
    }

    @Override
    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Ball) {
            notifyBombExploded(other);
        }
    }

    public void registerObserver(BombExplosionObserver bombExplosionObserver){
        _bombExplosionObservers.add(bombExplosionObserver);
    }

    public boolean neighbourIsInExplosionRadius(Gobj neighbour){
        Point neighbourPosition = neighbour.getPosition();
        double yCoordinateDifference = Math.abs(neighbourPosition.Y - getPosition().Y);
        double xCoordinateDifference = Math.abs(neighbourPosition.X - getPosition().X);
        return xCoordinateDifference <= _explosionWidth && yCoordinateDifference <= _explosionHeight;
    }

    private void notifyBombExploded(Gobj exploder){
        for (BombExplosionObserver bombExplosionObserver : _bombExplosionObservers) {
            bombExplosionObserver.onBombExploded(exploder, this);
        }
    }

    private void setupExplosionBoundaries(int explosionRadius){
        Size shapeSize = getShape().getSize();
        _explosionWidth = explosionRadius * shapeSize.getWidth();
        _explosionHeight = explosionRadius * shapeSize.getHeight();
    }
}
