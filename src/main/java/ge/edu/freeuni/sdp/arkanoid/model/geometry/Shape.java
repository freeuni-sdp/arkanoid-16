package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public interface  Shape {
    boolean canOverlap(ShapeVisitor other);
}
