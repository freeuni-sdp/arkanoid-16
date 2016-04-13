package ge.edu.freeuni.sdp.arkanoid.model;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.Random;
import java.util.ArrayList;
/**
 * Created by elene on 4/12/16.
 */
public class LevelWormholeBuilder extends FrameBuilder{
    private int _brickWidth;
    private int _brickHeight;
    private final double _distY = 1;
    private final double _distX = 0;
    private final int _nrows = 1;
    private ArrayList<PortalBrick> portals;
    private final int _nPortals = 4;

    public LevelWormholeBuilder(Size size){
        super(size);
        Size brickSize = new NormalBrick(null, BrickColor.Red, null).getShape().getSize();
        _brickWidth = brickSize.getWidth();
        _brickHeight = brickSize.getHeight();
        portals = new ArrayList<PortalBrick>();
    }

    public void build(Room room, ScoreCounter scoreCounter){
        super.build(room, scoreCounter);
        int roomWidth = Configuration.getInstance().getSize().getWidth();
        int nBricksPerRow =  (int)(roomWidth/(_brickWidth + _distX));
        ArrayList<Point> portalPoints = randomizePortals(nBricksPerRow, _nrows-1);
        for(int i = 0; i <= nBricksPerRow; i+= 1){
            for(int j = 0; j < _nrows; j += 1){
                room.add(createBrick(posX(i), posY(j), portalPoints));
            }
        }
        room.add(new WormholeBall(portals));
    }


    /*creates bricks. the portals are stored in the arraylist, each nth portal(where n is even) is connected with n+1-th portal.*/
    private Brick createBrick(double i, double j, ArrayList<Point> points){
        Point p = new Point(i, j);
        if(points.contains(p)){
            PortalBrick br = new PortalBrick(p, BrickColor.White, new NullCapsule(null));
            portals.add(br);
            return br;
        }
        return new NormalBrick(p, BrickColor.DarkBlue, new NullCapsule(null));
    }


    private double posX(int i){
        return i*(_brickWidth+_distX);
    }

    private double posY(int j){
        return j*(_brickHeight + _distY);
    }


    /* randomizes Portal objects.*/
    private ArrayList<Point> randomizePortals(int maxX, int maxY){
        ArrayList<Point> portalPoints =  new ArrayList<Point>();
        Random rand = new Random();
        for(int i = 0; i < _nPortals; i++){
            double x = posX(rand.nextInt(maxX));
            double y = posY(rand.nextInt(maxY+1));
            Point tmp = new Point(x,y);
            if(portalPoints.contains(tmp)){
                i--; continue;
            }
            if(i % 2 == 1){
                Point lastP = portalPoints.get(portalPoints.size()-1);
                if (tmp.Y == lastP.Y && (tmp.X == lastP.X - posX(1)  || tmp.X == lastP.X + posX(1) )){
                    i--;
                    continue;

                }
            }
            portalPoints.add(tmp);
        }

        return portalPoints;
    }



}
