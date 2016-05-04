package ge.edu.freeuni.sdp.arkanoid.model;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by Rakhdakh on 5/4/2016.
 */
public class ExpandCapsuleTest {

    @Mock Point position;
    @Mock Room room;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void capsule_is_the_kind(){
        ExpandCapsule capsule = new ExpandCapsule(position,room);
        GobjKind kind = capsule.getKind();
        assertEquals(GobjKind.Capsule,kind);
    }

    @Test
    public void capsule_in_the_room(){
        ExpandCapsule capsule = new ExpandCapsule(position,room);
        Room curRoom = capsule.getRoom();
        assertEquals(room,curRoom);
    }

    @Test
    public void is_one_by_one_rectangle_shape(){
        Rectangle rec = new Rectangle(position,new Size(1,1));
        ExpandCapsule capsule = new ExpandCapsule(position,room);
        assertEquals(capsule.getShape(),rec);
    }

    @Test
    public void extends_check(){
        ExpandCapsule capsule = new ExpandCapsule(position,room);
        Paddle paddle = new Paddle(mock(Size.class));
        Game game = new Game(mock(Size.class));
        game.init(mock(Level.class));
        Rectangle rec =  new Rectangle(position,new Size(9,1));
        paddle.addListener(game);
        capsule.interact(paddle);
        Set<Gobj> objects = game.getGobjs();
        boolean foundIt=false;
        for (Gobj gobj : objects){
            if (gobj.getClass() == ExpandedPaddle.class){
                assertTrue(gobj.getShape().getClass() == Rectangle.class);
                Rectangle gobjRec = (Rectangle) gobj.getShape();
                assertTrue(gobjRec.getSize().getHeight() == rec.getSize().getHeight()
                && gobjRec.getSize().getWidth() == rec.getSize().getWidth());
                foundIt=true;
                break;
            }
        }
        assertTrue(foundIt);
    }
}
