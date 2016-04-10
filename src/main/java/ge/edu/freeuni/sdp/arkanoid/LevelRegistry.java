package ge.edu.freeuni.sdp.arkanoid;

import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.ArrayList;
import java.util.List;

class LevelRegistry {

    static List<Level> getLevels(Size size) {
        List<Level> levels = new ArrayList<>();

        Level autoPaddleLevel = new Level(
                "Test #6 Autopilot Capsule",
                "This level is a test for #6 Autopilot Capsule feature.",
                new TestACapsuleLevelBuilder(size));

        Level levelVerySimple = new Level(
                "Test #3 Expand Capsule",
                "This level is a test for #3 Expand Capsule feature.",
                new TestECapsuleLevelBuilder(size));

        Level level1 = new Level(
                "Level 1",
                "Destruct the wall. No power-ups.",
                new Level1Builder(size));

        Level levelSinkingWall = new Level(
                "Level 2 Sinking Wall",
                "Wall is sinking. Make sure it doesn't reach you.",
                new SinkingWallLevelBuilder(size));

        Level levelScrollingWall = new Level(
                "Level 3 Scrolling Wall",
                "Wall is scrolling. Make sure it doesnt reach left bound.",
                new ScrollingWallLevelBuilder(size));

        Level levelBombBricksDemo = new Level(
                "Level Bomb Bricks Demo",
                "Approximately 20% of bricks are bombs. If bomb is hit, it explodes neighbours within radius of 2 bricks",
                new LevelBombBricksDemoBuilder(size)
        );

        levels.add(autoPaddleLevel);
        Level levelClearDemo = new Level(
                "Simulate level clear",
                "Contains one brick which is hit when the game starts",
                new LevelSingleBrickBuilder(size)
        );

        Level levelGameEndDemo = new Level(
                "Simulate game finish",
                "Contains one brick which is hit when the game starts",
                new LevelSingleBrickBuilder(size)
        );

        levels.add(autoPaddleLevel);
        levels.add(levelClearDemo);
        levels.add(levelVerySimple);
        levels.add(level1);
        levels.add(levelSinkingWall);
        levels.add(levelScrollingWall);
        levels.add(levelBombBricksDemo);
        levels.add(levelGameEndDemo);

        return levels;
    }
}
