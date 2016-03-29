package ge.edu.freeuni.sdp.arkanoid.model.geometry;

public class Speed extends Point {

    public static Speed NULL = new Speed(0, 0);
    public static double LENGTH = 0.3;

    public Speed(int angleDegrees) {
        this(Math.cos(Math.toRadians(angleDegrees)) * LENGTH, (float) Math.sin(Math.toRadians(angleDegrees)) * LENGTH);
    }

    protected Speed(double x, double y) {
        super(x, y);
    }

    public Speed mirrorVertically() {
        return new Speed(this.X, -this.Y);
    }

    public Speed mirrorHorizontally() {
        return new Speed(-this.X, this.Y);
    }

    public Speed mirror() {
        return this.mirrorHorizontally().mirrorVertically();
    }

}
