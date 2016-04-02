package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class TestECapsuleLevelBuilder extends TestFrameBuilder {

    private DeathCountListener _countListener;
    private int _numGameObjects;

    public TestECapsuleLevelBuilder(Size size) {
        super(size);
        _countListener = new DeathCountListener(0);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickDistance = 5;
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        _numGameObjects = 0;
        for (int i = 1; i < roomWidth; i += brickDistance) {
            Point position = new Point(i, 5);
            Capsule capsule = new ExpandCapsule(position, room);
            Brick current = new NormalBrick(position, BrickColor.Red, capsule);
            current.setDeathListener(_countListener);
            room.add(current);
            _numGameObjects++;
        }
        _countListener.setCount(_numGameObjects);
    }

    @Override
    public void setLevelOverListener(LevelOverListener listener) {
        _countListener.setLevelOverListener(listener);
    }
}
