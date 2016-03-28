package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Point {

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
}