package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Rectangle;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Nika Doghonadze.
 */
public class MovingBrickTest {
    @Mock Point pointMock;
    @Mock Capsule capsuleMock;
    @Mock Direction directionMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPositionOrigin() {
        Point origin = new Point(0, 0);
        MovingBrick movingBrick = new MovingBrick(origin, BrickColor.Blue, capsuleMock, directionMock, 0);

        assertTrue(origin.equals(movingBrick.getPosition()));
    }

    @Test
    public void testGetPositionNegative() {
        Point position = new Point(-1, -1);
        MovingBrick movingBrick = new MovingBrick(position, BrickColor.Blue, capsuleMock, directionMock, 0);

        assertTrue(position.equals(movingBrick.getPosition()));
    }


    @Test
    public void testChangePosition() {
        Point startPosition = new Point(0, 0);
        Point endPosition = new Point(12, 65);
        MovingBrick movingBrick = new MovingBrick(startPosition, BrickColor.Green, capsuleMock, directionMock, 0);
        movingBrick.setPosition(endPosition);

        assertTrue(endPosition.equals(movingBrick.getPosition()));
    }

    @Test
    public void testGetColor() {
        BrickColor color = BrickColor.Green;
        MovingBrick movingBrick = new MovingBrick(pointMock, color, capsuleMock, directionMock, 0);

        assertEquals(color, movingBrick.getColor());
    }


    @Test
    public void testGetKind() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 0);

        assertEquals(GobjKind.Brick, movingBrick.getKind());
    }


    @Test
    public void testGetScoreFromColorStrategy() {
        BrickColorStrategy strategyMock = Mockito.mock(BrickColorStrategy.class);
        when(strategyMock.getScore()).thenReturn(0);
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 100);

        movingBrick.setBrickColor(strategyMock);

        assertEquals(0, movingBrick.getScore());
    }


    @Test
    public void testGetShape() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 100);

        assertTrue(movingBrick.getShape().getClass() == Rectangle.class);
    }

    @Test
    public void testGetNullSpeed() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 100);
        movingBrick.setSpeed(Speed.NULL);

        assertTrue(Speed.NULL.equals(movingBrick.getSpeed()));
    }


    @Test
    public void testGetNotNullSpeed() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 100);
        Speed speed = new Speed(new Point(-8, 5));

        movingBrick.setSpeed(speed);

        assertTrue(speed.equals(movingBrick.getSpeed()));
    }


    @Test
    public void testNewBrickIsKillable() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 0);

        assertTrue(movingBrick.isKillable());
    }

    @Test
    public void testNewBrickIsAlive() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 0);

        assertTrue(movingBrick.isAlive());
    }

    @Test
    public void testMovingBrickNotMoveEarly() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Green, capsuleMock, directionMock, 999999999);
        Point point = movingBrick.getPosition();

        movingBrick.move();

        assertTrue(point.equals(movingBrick.getPosition()));
    }

    @Test
    public void testMoveBrickLeftInstantly() {
        Point startPosition = new Point(0, 0);
        Direction direction = Direction.LEFT;
        MovingBrick movingBrick = new MovingBrick(startPosition, BrickColor.Green, capsuleMock, direction, 0);
        Point point = movingBrick.getPosition();
        Point endPoint = point.add(direction.toPoint().multiply(movingBrick.getShape().getSize().toPoint()));

        movingBrick.move();

        assertTrue(endPoint.equals(movingBrick.getPosition()));
    }

    @Test
    public void testMoveBrickDownInstantly() {
        Point startPosition = new Point(56, -8);
        Direction direction = Direction.DOWN;
        MovingBrick movingBrick = new MovingBrick(startPosition, BrickColor.Green, capsuleMock, direction, 0);
        Point point = movingBrick.getPosition();
        Point endPoint = point.add(direction.toPoint().multiply(movingBrick.getShape().getSize().toPoint()));

        movingBrick.move();

        assertTrue(endPoint.equals(movingBrick.getPosition()));
    }

    @Test
    public void testMoveBrickZeroSpeedInstantly() {
        Point startPosition = new Point(7, -8);
        Direction direction = Direction.NONE;
        MovingBrick movingBrick = new MovingBrick(startPosition, BrickColor.Green, capsuleMock, direction, 0);

        movingBrick.move();

        assertTrue(startPosition.equals(movingBrick.getPosition()));
    }

    @Test
    public void testInteractWithBall() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.White, capsuleMock, directionMock, 0);
        Ball ball = Mockito.mock(Ball.class);

        movingBrick.interact(ball);

        assertFalse(movingBrick.isAlive());
    }

    @Test
    public void testInteractWithMovementKiller() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.White, capsuleMock, directionMock, 0);
        MovementKillerBrick movementKillerBrick = Mockito.mock(MovementKillerBrick.class);

        movingBrick.interact(movementKillerBrick);

        assertFalse(movingBrick.isAlive());
    }

    @Test
    public void testGoldenBrickMustNotDie() {
        MovingBrick movingBrick = new MovingBrick(pointMock, BrickColor.Gold, capsuleMock, directionMock, 0);
        Ball ball = Mockito.mock(Ball.class);

        movingBrick.interact(ball);

        assertTrue(movingBrick.isAlive());
    }
}