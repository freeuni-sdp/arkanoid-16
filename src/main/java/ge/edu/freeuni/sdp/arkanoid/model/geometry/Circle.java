package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Circle implements Shape, ShapeVisitor {
    private final double _radius;
    private final Point _center;

    public Circle(double radius, Point center) {
        _radius = radius;
        _center = center;
    }

    public double getRadius() {
        return _radius;
    }

    public Point getCenter() {
        return _center;
    }

    private boolean canOverlap(Circle other) {
        Point c1 = this.getCenter();
        Point c2 = other.getCenter();
        double distance = Math.hypot(c1.X - c2.X, c1.Y - c2.Y);
        return distance < this.getRadius() + other.getRadius();
    }


    @Override
    public boolean canOverlap(ShapeVisitor other) {
        return other.visit(this);
    }

    @Override
    public boolean visit(Circle elem) {
        return canOverlap(elem);
    }
}
