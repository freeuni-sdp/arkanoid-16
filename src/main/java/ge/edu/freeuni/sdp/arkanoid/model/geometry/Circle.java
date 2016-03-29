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
        Point c = _center;
        double radius = _radius;
        Point l = rectangle.getPosition();
        Point r = rectangle.getBottomRight();
        Size s = rectangle.getSize();
        boolean touchTop = Math.abs(c.Y - l.Y) <= radius && c.X >= l.X && c.X <= r.X + s.getWidth();
        boolean touchBottom = Math.abs(c.Y - r.Y) <= radius && c.X >= l.X && c.X <= r.X + s.getWidth();
        boolean touchLeft = Math.abs(c.X - l.X) <= radius && c.Y >= l.Y && c.Y <= r.Y + s.getHeight();
        boolean touchRight = Math.abs(c.X - r.X) <= radius && c.Y >= l.Y && c.Y <= r.Y + s.getHeight();
        return touchBottom || touchLeft || touchTop || touchRight;
    }
}
