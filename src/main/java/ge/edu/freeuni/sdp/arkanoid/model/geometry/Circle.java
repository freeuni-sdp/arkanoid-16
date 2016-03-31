package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Circle extends Shape {
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

    public boolean canOverlap(Shape other) {
        if (other instanceof Circle) {
            return canOverlap((Circle) other);
        }

        if (other instanceof Rectangle) {
            return canOverlap((Rectangle) other);
        }

        return false;
    }

    public boolean canOverlap(Circle other) {
        Point c1 = this.getCenter();
        Point c2 = other.getCenter();
        double distance = Math.hypot(c1.X - c2.X, c1.Y - c2.Y);
        return distance < this.getRadius() + other.getRadius();
    }

    public boolean canOverlap(Rectangle rectangle) {
        return rectangle.canOverlap(_center);
    }
}
