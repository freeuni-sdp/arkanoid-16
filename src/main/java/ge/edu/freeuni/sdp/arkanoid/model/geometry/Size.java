package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Size {
    private int _width;
    private int _height;

    public Size(int width, int height) {
        if (width < 0) throw new IllegalArgumentException("width");
        if (height < 0) throw new IllegalArgumentException("height");

        _width = width;
        _height = height;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public boolean isInRange(Point current) {
        return current.X >= 0 &&
                current.X < this.getWidth() &&
                current.Y >= 0 &&
                current.Y < this.getHeight();
    }

    public Point toPoint() {
        return new Point(getWidth(), getHeight());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Size other = (Size) obj;
        //noinspection SimplifiableIfStatement
        if (_width != other._width)
            return false;
        return _height == other._height;
    }
}
