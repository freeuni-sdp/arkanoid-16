package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Point {

    private static final Point CENTER_OFFSET = new Point(-0.5, -0.5);
    public double X;
    public double Y;

    public Point(double x, double y) {
        X = x;
        Y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (Double.doubleToLongBits(X) % Integer.MAX_VALUE);
        result = prime * result + (int) (Double.doubleToLongBits(Y) % Integer.MAX_VALUE);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (X != other.X)
            return false;
        return Y == other.Y;
    }

    public Point add(Point point) {
        return new Point(this.X + point.X, this.Y + point.Y);
    }

    public double getDistance(Point other) {
        return Math.hypot(this.X - other.X, this.Y - other.Y);
    }

    public Point round() {
        return new Point(Math.round(this.X), Math.round(this.Y));
    }

    public Point reverse() {
        return new Point(-X, -Y);
    }

    public GridIndex toGridIndex() {
        Point shifted = this.add(CENTER_OFFSET);
        Point rounded = shifted.round();
        return new GridIndex((int) rounded.X, (int) rounded.Y);
    }

    public Point smoothen() {
        Point shifted = this.add(CENTER_OFFSET);
        Point rounded = shifted.round();

        boolean isAlmostOnGrid = shifted.getDistance(rounded) < 0.1;
        if (isAlmostOnGrid)
            return rounded.add(CENTER_OFFSET.reverse());
        else return this;
    }
}