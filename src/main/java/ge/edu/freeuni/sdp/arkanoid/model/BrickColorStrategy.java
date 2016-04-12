package ge.edu.freeuni.sdp.arkanoid.model;

/**
 * Created by vato on 4/12/16.
 */
abstract class BrickColorStrategy {

    abstract int getScore();

    static BrickColorStrategy getStrategy(BrickColor color) {
        switch (color) {
            case White:
                return new WhiteBrickStrategy();
            case Orange:
                return new OrangeBrickStrategy();
            case LightBlue:
                return new LightBlueBrickStrategy();
            case Green:
                return new GreenBrickStrategy();
            case Red:
                return new RedBrickStrategy();
            case DarkBlue:
                return new DarkBlueBrickStrategy();
            case LightOrange:
                return new LightOrangeBrickStrategy();
            case Yellow:
                return new YellowBrickStrategy();
            default:
                return null;
        }
    }
}

class WhiteBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 50;
    }

}

class OrangeBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 60;
    }

}

class LightBlueBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 70;
    }

}

class GreenBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 80;
    }

}

class RedBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 90;
    }

}

class DarkBlueBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 100;
    }

}

class LightOrangeBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 110;
    }

}

class YellowBrickStrategy extends BrickColorStrategy{

    @Override
    public int getScore() {
        return 50;
    }

}