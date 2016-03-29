package ge.edu.freeuni.sdp.arkanoid;

import ge.edu.freeuni.sdp.arkanoid.model.Level;
import ge.edu.freeuni.sdp.arkanoid.model.Level1Builder;
import ge.edu.freeuni.sdp.arkanoid.model.TestLevelBuilder;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

import java.util.ArrayList;
import java.util.List;

public class LevelRegistry {

    public static List<Level> getLevels(Size size) {
        List<Level> levels = new ArrayList<Level>();

        Level levelVerySimple = new Level(
                "Test level - one brick",
                "One line of bricks, paddle and that's it.",
                new TestLevelBuilder(size));

        Level level1 = new Level(
                "Level 1",
                "Destruct the wall. No power-ups.",
                new Level1Builder(size));

        levels.add(levelVerySimple);
        levels.add(level1);

        return levels;
    }
}
