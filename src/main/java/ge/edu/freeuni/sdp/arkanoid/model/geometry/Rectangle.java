package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Rectangle implements Shape, ShapeVisitor{
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

    public boolean canOverlap(Point point) {
        return point.X > _position.X &&
                point.Y > _position.Y &&
                point.X <= _position.X + _size.getWidth() &&
                point.Y <= _position.Y + _size.getHeight();
    }

    private boolean canOverlap(Rectangle other) {
        Point l1 = this.getPosition();
        Point r1 = this.getBottomRight();

        Point l2 = other.getPosition();
        Point r2 = other.getBottomRight();

        return l1.X < r2.X &&
                r1.X > l2.X &&
                l1.Y < r2.Y &&
                r1.Y > l2.Y;
    }
    @Override
    public boolean visit(Circle elem) {
        return canOverlap(elem.getCenter());
    }

    @Override
    public boolean visit(Rectangle elem) {
        return canOverlap(elem);
    }

    @Override
    public boolean canOverlap(ShapeVisitor other) {
        if (other instanceof Circle)
            return visit((Circle)other);
        return other.visit(this);
    }
}
