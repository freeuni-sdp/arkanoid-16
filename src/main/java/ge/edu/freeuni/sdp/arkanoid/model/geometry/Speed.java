package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public final class Speed extends Point {

    public static Speed NULL = new Speed(0, 0);
    private static double DEFAULT_LENGTH = 0.15;

    public Speed(int angleDegrees) {
        this(angleDegrees, DEFAULT_LENGTH);
    }

    public Speed(int angleDegrees, double length) {
        super(1, 1);
        setAngleDegrees(angleDegrees);
        setLength(length);
    }

    public Speed(Point point) {
        super(point.X, point.Y);
    }

    public Speed mirrorY() {
        return new Speed(super.mirrorY());
    }

    public Speed mirrorX() {
        return new Speed(super.mirrorX());
    }

    public double getLength() {
        return getDistance(new Point(0, 0));
    }

    public void setLength(double length) {
        double angle = getAngle();
        X = Math.cos(angle) * length;
        Y = Math.sin(angle) * length;
    }

    public double getAngleDegrees() {
        return Math.round(Math.toDegrees(getAngle()));
    }

    public void setAngleDegrees(int angleDegrees) {
        double length = this.getLength();
        double angle = Math.toRadians(angleDegrees);
        X = Math.cos(angle) * length;
        Y = Math.sin(angle) * length;
    }

    private double getAngle() {
        return Math.atan2(Y, X);
    }
}
