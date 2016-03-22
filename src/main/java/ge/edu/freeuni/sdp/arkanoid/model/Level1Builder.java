package ge.edu.freeuni.sdp.arkanoid.model;

public class Level1Builder implements RoomBuilder {
    public void build(Room room) {
        int width = Configuration.getInstance().getSize().getWidth();
        for (int i = 0; i < width; i += 2) {
            Point position = new Point(i, 5);
            Brick current = new Brick(position, BrickColor.Red);
            room.add(current);
        }
    }
}
