package ge.edu.freeuni.sdp.arkanoid;

import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.ArrayList;
import java.util.List;

class LevelRegistry {

    static List<Level> getLevels(Size size) {
        List<Level> levels = new ArrayList<>();

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

        levels.add(levelVerySimple);
        levels.add(level1);
        levels.add(levelSinkingWall);
        levels.add(levelScrollingWall);

        return levels;
    }
}
