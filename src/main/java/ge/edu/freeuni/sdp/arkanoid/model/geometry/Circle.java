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

    public boolean hasOverlap(Shape other) {
        if (other instanceof Circle) {
            return hasOverlap((Circle) other);
        }

        if (other instanceof Rectangle) {
            return hasOverlap((Rectangle) other);
        }

        return false;
    }

    public boolean hasOverlap(Circle other) {
        Point c1 = this.getCenter();
        Point c2 = other.getCenter();
        double distance = Math.hypot(c1.X - c2.X, c1.Y - c2.Y);
        return distance < this.getRadius() + other.getRadius();
    }

    public boolean hasOverlap(Rectangle rectangle) {
        Point c = this.getCenter();
        Point l = rectangle.getPosition();
        Size s = rectangle.getSize();
        Point r = rectangle.getBottomRight();
        boolean isInside = l.X < c.X && l.Y < c.Y && r.X > c.X && r.Y > c.Y;
        if (isInside) return true;

        boolean touchTop = Math.abs(c.Y - l.Y) <= _radius && c.X >= l.X && c.X <= l.X + s.getWidth();
        if (touchTop) return true;
        boolean touchBottom = Math.abs(c.Y - r.Y) <= _radius && c.X >= r.X && c.X <= r.X + s.getWidth();
        if (touchBottom) return true;
        boolean touchLeft = Math.abs(c.X - l.X) <= _radius && c.Y >= l.Y && c.Y <= l.Y + s.getHeight();
        if (touchLeft) return true;
        boolean touchRight = Math.abs(c.X - r.X) <= _radius && c.Y >= r.Y && c.Y <= r.Y + s.getHeight();
        return touchRight;

    }
}
