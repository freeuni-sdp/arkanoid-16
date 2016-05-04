package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Koba on 29/04/2016.
 */
public class ConfigurationTest {

    private Size size;
    private List<Level> levels;
    private Level levelOne;
    private Level levelTwo;

    @Before
    public void setup() {
        size = mock(Size.class);
        levelOne = mock(Level.class);
        when(levelOne.getName()).thenReturn("Level 1");
        levelTwo = mock(Level.class);
        when(levelTwo.getName()).thenReturn("Level 2");
        levels = new ArrayList<>();
        levels.add(levelOne);
        levels.add(levelTwo);
        //Configuration.setInstanceForTesting(null);
    }

    /*
        test getInstance() when instance is null
     */
    @Test(expected = IllegalStateException.class)
    public void testUninitializedInstance() {
        Configuration c = Configuration.getInstance();
    }

    /*
        test getSize() method
     */
    @Test
    public void testSize() {
        Size s = mock(Size.class);
        Configuration.init(s, levels);
        Configuration c = Configuration.getInstance();
        assertEquals(s, c.getSize());
    }

    /*
        test getLevelNames method
     */
    @Test
    public void testLevelNames() {
        List l = new ArrayList<>();
        Level l1 = levelOne = mock(Level.class);
        Level l2 = mock(Level.class);
        Level l3 = mock(Level.class);
        l.add(l1); l.add(l2); l.add(l3);
        when(l1.getName()).thenReturn("l1");
        when(l2.getName()).thenReturn("l2");
        when(l3.getName()).thenReturn("l3");
        Configuration.init(size, l);
        Configuration c = Configuration.getInstance();
        String[] names = new String[3];
        names[0] = "l1";
        names[1] = "l2";
        names[2] = "l3";
        assertArrayEquals(names, c.getLevelNames());
        l.remove(2);
        names = new String[2];
        names[0] = "l1";
        names[1] = "l2";
        assertArrayEquals(names, c.getLevelNames());
        l.remove(1);
        names = new String[1];
        names[0] = "l1";
        assertArrayEquals(names, c.getLevelNames());
        l.remove(0);
        names = new String[0];
        assertArrayEquals(names, c.getLevelNames());
        verify(l1, times(3)).getName();
        verify(l2, times(2)).getName();
        verify(l3).getName();
    }

    /*
        test getInstance() when instance is not null, also
        check if provided size and levels arguments match with
        the ones returned by the instance
     */
    @Test
    public void testInitializedInstanceWithSizeAndNames() {
        Configuration.init(size, levels);
        Configuration c = Configuration.getInstance();
        assertEquals(size, c.getSize());
        String[] names = new String[2];
        names[0] = "Level 1";
        names[1] = "Level 2";
        assertArrayEquals(names, c.getLevelNames());
    }

    /*
        test setting and getting selected level index
     */
    @Test
    public void testSelectedLevelIndex() {
        Configuration.init(size, levels);
        Configuration c = Configuration.getInstance();
        for(int i = 0; i < 5; i++) {
            c.setSelectedLevelIndex(i);
            assertEquals(i, c.getSelectedLevelIndex());
        }
    }

    /*
        test getSelectedLevel() method
     */
    @Test
    public void testSelectedLevel() {
        Configuration.init(size, levels);
        Configuration c = Configuration.getInstance();
        for(int i = 0; i < levels.size(); i++) {
            c.setSelectedLevelIndex(i);
            assertEquals(levels.get(i), c.getSelectedLevel());
        }
    }

    /*
        test init. first time init is called, it should initialize the object and if it does,
        all the other times it should throw IllegalStateException
     */
    @Test(expected = IllegalStateException.class)
    public void testDoubleInitialization() {
        Configuration.init(size, levels);
        Configuration.init(size, levels);
    }

}
