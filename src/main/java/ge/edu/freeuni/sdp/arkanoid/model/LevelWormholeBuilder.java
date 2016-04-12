package ge.edu.freeuni.sdp.arkanoid.model;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
/**
 * Created by elene on 4/12/16.
 */
public class LevelWormholeBuilder extends FrameBuilder{
    private int _brickWidth;
    private int _brickHeight;
    private final double _dist = 1;
    private final int _nrows = 2;

    public LevelWormholeBuilder(Size size){
        super(size);
        Size brickSize = new NormalBrick(null, BrickColor.Red, null).getShape().getSize();
        _brickWidth = brickSize.getWidth();
        _brickHeight = brickSize.getHeight();
    }

    public void build(Room room, ScoreCounter scoreCounter){
        super.build(room, scoreCounter);
        int roomWidth = Configuration.getInstance().getSize().getWidth();
        for(double i = 0; i < roomWidth; i+= _brickWidth){
            for(double j = 0; j < (_brickHeight + _dist)*_nrows; j += _brickHeight + _dist){
                room.add(createBrick(i, j));
            }
        }
    }

    private Brick createBrick(double i, double j){
        Point p = new Point(i, j);
        return new NormalBrick(p, BrickColor.DarkBlue, new NullCapsule(null));
    }


}
