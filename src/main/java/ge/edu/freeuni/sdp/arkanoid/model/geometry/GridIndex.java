package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class GridIndex {

    public final int X;
    public final int Y;

    GridIndex(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + X;
        result = prime * result + Y;
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
        //noinspection SimplifiableIfStatement
        if (X != other.X)
            return false;
        return Y == other.Y;
    }

    public Point toPoint() {
        return new Point(X, Y);
    }
}
