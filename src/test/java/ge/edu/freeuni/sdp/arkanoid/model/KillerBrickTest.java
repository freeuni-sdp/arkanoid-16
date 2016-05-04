package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Mockito.mock;

/**
 * Created by root on 5/3/2016.
 */

public class KillerBrickTest {


    @Mock
    MovingBrick movingBrick;
    private KillerBrick killerBrick;
    private Game game;
    private Ball ball;
    private Room room;
    private Size size;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        size = new Size(20,20);
        game = new Game(size);
        ball = new Ball();
        room = new Room();
        killerBrick = new KillerBrick(new Point(0,0),size,room);
    }

    @Test
    public void decrease_ballNumber_interact_killerBrick(){
        ball.increaseNumBalls();
        ball.interact(killerBrick);
        assertEquals(0, ball.getNumBalls());
    }

    @Test
    public void remove_ball_from_game_interact_killerBrick(){
        Level level = mock(Level.class);
        game.init(level);
        game.ballAdded(ball);
        ball.interact(killerBrick);
        assertEquals(game.geLives(),2);
    }


    @Test
    public void check_killerBrick_kind (){
        GobjKind kind = killerBrick.getKind();
        assertEquals(kind, GobjKind.Brick);
    }


    @Test
    public void check_shape_killerBrick(){
        Class<? extends Shape> shape = killerBrick.getShape().getClass();
        assertEquals(shape, Rectangle.class);
    }

    @Test
    public void paddle_life_after_crash_movingBrick(){
        Configuration.init(size, anyList());
        room.setLiveCounter(new LiveCounter(3));
        MovementKillerBrick movementKillerBrick = new MovementKillerBrick(new Point(0,0),size,room);
        movementKillerBrick.interact(movingBrick);
        assertEquals(room.getLiveCounter().getLive(),2);
    }

    @Test
    public void check_position(){
        assertEquals(killerBrick.getPosition(),new Point(0,0));
    }



}
