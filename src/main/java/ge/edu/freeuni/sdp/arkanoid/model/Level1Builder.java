package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class Level1Builder extends FrameBuilder {

    public Level1Builder(Size size) {
        super(size);
    }

    public void build(Room room) {
        super.build(room);

        int width = Configuration.getInstance().getSize().getWidth();
        for (int i = 0; i < width; i += 2) {
            Point position = new Point(i, 5);
            Brick current = new Brick(position, BrickColor.Red);
            room.add(current);
        }
    }
}
