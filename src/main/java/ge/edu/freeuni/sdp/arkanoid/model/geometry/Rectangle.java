package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Rectangle extends Shape {
    private final Point _position;
    private final Size _size;

    public Rectangle(Point position, Size size) {
        _position = position;
        _size = size;
    }

    public Point getPosition() {
        return _position;
    }

    public Size getSize() {
        return _size;
    }

    public Point getBottomRight() {
        return new Point(_position.X + _size.getWidth(), _position.Y + _size.getHeight());
    }

    public boolean canOverlap(Rectangle other) {
        Point l1 = this.getPosition();
        Point r1 = this.getBottomRight();

        Point l2 = other.getPosition();
        Point r2 = other.getBottomRight();

        if (l1.X > r2.X || l2.X > r1.X)
            return false;

        // If one rectangle is above other
        return !(l1.Y < r2.Y || l2.Y < r1.Y);

    }

    public boolean canOverlap(Circle other) {
        return other.canOverlap(this);
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
}
