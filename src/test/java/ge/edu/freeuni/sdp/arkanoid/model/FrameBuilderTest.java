package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.asm.tree.analysis.Frame;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FrameBuilderTest {
    @Mock Room room;
    @Mock Size size;
    @Mock Point position;
    @Mock ScoreCounter scoreCounter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuild() {
        FrameBuilder target = new FrameBuilder(size);
        target.build(room, scoreCounter);

        verify(room, times(4)).add(any(FrameBrick.class));
    }

    @Test
    public void testCreateTopFrameBrick() {
        FrameBuilder target = new FrameBuilder(size);
        FrameBrick frameBrick = target.createTopFrameBrick(position, size, room);

        assertThat(frameBrick.getClass(), is(equalTo(FrameBrick.class)));
        assertThat(frameBrick.getPosition(), is(equalTo(position)));
        assertThat(frameBrick.getShape().getSize(), is(equalTo(size)));
    }

    @Test
    public void testCreateBottomFrameBrick() {
        FrameBuilder target = new FrameBuilder(size);
        FrameBrick frameBrick = target.createBottomFrameBrick(position, size, room);

        assertThat(frameBrick.getClass(), is(equalTo(KillerBrick.class)));
        assertThat(frameBrick.getPosition(), is(equalTo(position)));
        assertThat(frameBrick.getShape().getSize(), is(equalTo(size)));
    }

    @Test
    public void testCreateRightFrameBrick() {
        FrameBuilder target = new FrameBuilder(size);
        FrameBrick frameBrick = target.createRightFrameBrick(position, size, room);

        assertThat(frameBrick.getClass(), is(equalTo(FrameBrick.class)));
        assertThat(frameBrick.getPosition(), is(equalTo(position)));
        assertThat(frameBrick.getShape().getSize(), is(equalTo(size)));
    }

    @Test
    public void testCreateLeftFrameBrick() {
        FrameBuilder target = new FrameBuilder(size);
        FrameBrick frameBrick = target.createLeftFrameBrick(position, size, room);

        assertThat(frameBrick.getClass(), is(equalTo(FrameBrick.class)));
        assertThat(frameBrick.getPosition(), is(equalTo(position)));
        assertThat(frameBrick.getShape().getSize(), is(equalTo(size)));
    }

}