package ge.edu.freeuni.sdp.arkanoid;

import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.ArrayList;
import java.util.List;

class LevelRegistry {

    static List<Level> getLevels(Size size) {
        List<Level> levels = new ArrayList<>();

        Level levelTestGray = new Level(
                "Test Level gray brick",
                "Test Level gray brick.",
                new GrayTestLevelBuilder(size));

        Level autoPaddleLevel = new Level(
                "Test #12 Autopilot Capsule",
                "This level is a test for #12 Autopilot Capsule feature.",
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
                "Approximately 20% of bricks are bombs. If bomb is hit, " +
                        "it explodes neighbours within radius of 2 bricks" +
                        "(bomb bricks are Blue)",
                new LevelBombBricksDemoBuilder(size)
        );

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

        Level levelBreakCapsuleDemo = new Level(
                "Level Break Capsule Demo",
                "This level is a test for BreakCapsule thus half of the " +
                        "bricks contains BreakCapsule",
                new LevelBreakCapsuleBuilder(size)
        );

        levels.add(autoPaddleLevel);
        Level levelWithLives = new Level(
                "P Capsule",
                "Contains one brick which is hit when the game starts",
                new FrameBuilderWithLives(size)
        );

        Level levelBoss = new Level(
                "Level Boss",
                "Monster contains every capsule in the game",
                new LevelBossBuilder(size)
        );


        Level DisruptionCapsuleLevel = new Level(
                "Test Disruption Capsule",
                "This level is a test for #12 Autopilot Capsule feature.",
                new TestDCapsuleLevelBuilder(size));

        Level levelWormhole = new Level(
                "Level Wormhole",
                "This level contains two portals",
                new LevelWormholeBuilder(size)
        );

        Level levelKillCapsuleDemo = new Level(
                "Kill Capsule!",
                "Bricks contain killer capsules, that you must avoid",
                new LevelKillCapsuleBuilder(size)
        );

        Level levelLaserCapsuleDemo = new Level(
                "Laser Capsule!",
                "laser capsules generate laser beam if catched by paddle",
                new TestLCapsuleLevelBuilder(size)
        );
        
        levels.add(levelTestGray);
        levels.add(levelClearDemo);
        levels.add(levelVerySimple);
        levels.add(level1);
        levels.add(levelBreakCapsuleDemo);
        levels.add(levelSinkingWall);
        levels.add(levelScrollingWall);
        levels.add(levelBombBricksDemo);
        levels.add(levelGameEndDemo);
        levels.add(levelWithLives);
        levels.add(levelBoss);
        levels.add(DisruptionCapsuleLevel);
        levels.add(levelWormhole);
        levels.add(levelKillCapsuleDemo);
        levels.add(levelLaserCapsuleDemo);

        return levels;
    }
}
