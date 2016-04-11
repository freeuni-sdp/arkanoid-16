package ge.edu.freeuni.sdp.arkanoid.model;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;


/**
 * Created by giorgi on 4/9/2016.
 */
public class LevelBombBricksDemoBuilder extends FrameBuilder {
    private int _brickWidth;
    private int _brickHeight;
    private int _numColumns = 4;
    private double _bombProbability = 0.2;
    private int _bombExplosionRadius = 2;

    public LevelBombBricksDemoBuilder(Size size) {
        super(size);
        Size normalBrickSize = new NormalBrick(null, BrickColor.Red, null).getShape().getSize();
        _brickWidth = normalBrickSize.getWidth();
        _brickHeight = normalBrickSize.getHeight();
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int roomWidth = Configuration.getInstance().getSize().getWidth();
        for (int i = 0; i < roomWidth; i += _brickWidth) {
            for (int j = 0; j < _numColumns; j += _brickHeight) {
                addBrickToRoom(room, i, j);
            }
        }
    }

    private void addBrickToRoom(Room room, int xCoord, int yCoord){
        Point position = new Point(xCoord, yCoord);
        Brick current;
        if (Math.random() <= _bombProbability){
            current = new BombBrick(position, BrickColor.Blue, new NullCapsule(null),
                    _bombExplosionRadius, room);
        } else {
            current = new NormalBrick(position, BrickColor.Red, new NullCapsule(null));
        }
        room.add(current);
    }
}
