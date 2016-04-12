package ge.edu.freeuni.sdp.arkanoid.model;


import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class LevelBossBuilder extends FrameBuilder {

    // will be changed when new bricks are introduces in issue #8
    private BrickColor[][] monsterAppearance = new BrickColor[][] {
        { null, null, BrickColor.Red, null, null, null, null, null, BrickColor.Red, null, null },
        { null, null, BrickColor.Red, null, null, null, null, null, BrickColor.Red, null, null },
        { null, null, null, BrickColor.Red, null, null, null, BrickColor.Red, null, null, null },
        { null, null, null, BrickColor.Red, null, null, null, BrickColor.Red, null, null, null },
        { null, null, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red,
                BrickColor.Red, null, null },
        { null, null, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red,
                BrickColor.Red, null, null },
        { null, BrickColor.Red, BrickColor.Red, null, BrickColor.Red, BrickColor.Red, BrickColor.Red, null,
                BrickColor.Red, BrickColor.Red, null },
        { null, BrickColor.Red, BrickColor.Red, null, BrickColor.Red, BrickColor.Red, BrickColor.Red, null,
                BrickColor.Red, BrickColor.Red, null },
        { BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red,
                BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red },
        { BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red,
                BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red },
        { BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red,
                BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red },
        { BrickColor.Red, null, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red,
                BrickColor.Red, BrickColor.Red, BrickColor.Red, BrickColor.Red },
        { BrickColor.Red, null, BrickColor.Red, null, null, null, null, null, BrickColor.Red, null, BrickColor.Red },
        { BrickColor.Red, null, BrickColor.Red, null, null, null, null, null, BrickColor.Red, null, BrickColor.Red }
    };

    public LevelBossBuilder(Size size) {
        super(size);
    }

    public void build(Room room, ScoreCounter scoreCounter) {
        super.build(room, scoreCounter);
        int brickWidth = getNormalBrickWidth();
        int brickHeight = getNormalBrickHeight();
        int roomWidth = Configuration.getInstance().getSize().getWidth();

        createMonster(room, brickWidth, brickHeight, roomWidth);
    }

    private void createMonster(Room room, int brickWidth, int brickHeight, int roomWidth) {
        int monsterHeight = monsterAppearance.length;
        int monsterWidth = monsterAppearance[0].length;
        int upperLeftX = (roomWidth - monsterWidth * brickWidth) / 2;
        int upperLeftY = 2;

        for (int i = 0; i < monsterHeight; ++i) {
            for (int j = 0; j < monsterWidth; ++j) {
                if (monsterAppearance[i][j] != null) {
                    Point position = new Point(upperLeftX + j * brickWidth, upperLeftY + i * brickHeight);
                    Brick current = new NormalBrick(position, monsterAppearance[i][j],
                            new NullCapsule(null)); // TODO add every concrete type of capsule wp 0.3
                    room.add(current);
                }
            }
        }
    }

    private int getNormalBrickWidth() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize().getWidth();
    }

    private int getNormalBrickHeight() {
        return new NormalBrick(null, BrickColor.Red, null).getShape().getSize().getHeight();
    }
}
