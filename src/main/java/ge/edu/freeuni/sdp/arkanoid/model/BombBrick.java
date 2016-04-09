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
    private Room _room;
    private double _explosionWidth;
    private double _explosionHeight;

    public BombBrick(Point position, BrickColor color, Capsule capsule, int explosionRadius, Room room) {
        super(position, color, capsule);
        _room = room;
        setupExplosionBoundaries(explosionRadius);
    }

    @Override
    public void interact(Gobj other) {
        super.interact(other);
        if (other instanceof Ball) {
            explodeNeighbours(other);
        }
    }

    private void explodeNeighbours(Gobj exploder){
        _room.getGobjs()
                .stream()
                .filter(neighbour -> neighbour instanceof Brick)
                .filter(neighbour -> neighbour.isAlive())
                .filter(neighbour -> neighbourIsInExplosionRadius(neighbour))
                .forEach(neighbour -> neighbour.interact(exploder));
    }

    private boolean neighbourIsInExplosionRadius(Gobj neighbour){
        Point neighbourPosition = neighbour.getPosition();
        double yCoordinateDifference = Math.abs(neighbourPosition.Y - getPosition().Y);
        double xCoordinateDifference = Math.abs(neighbourPosition.X - getPosition().X);
        return xCoordinateDifference <= _explosionWidth && yCoordinateDifference <= _explosionHeight;
    }

    private void setupExplosionBoundaries(int explosionRadius){
        Size shapeSize = getShape().getSize();
        _explosionWidth = explosionRadius * shapeSize.getWidth();
        _explosionHeight = explosionRadius * shapeSize.getHeight();
    }
}
