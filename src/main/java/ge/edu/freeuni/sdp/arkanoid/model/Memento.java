package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Point;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Speed;

import java.io.*;
import java.util.Properties;

/**
 * Created by root_pc on 4/11/2016.
 */
public class Memento {

    private GameState gameState;
    private GameStateWriter writer;

    public Memento(){}

    public Memento(GameState gameState){
        this.gameState = gameState;
        initialGameState(gameState.getBall(),gameState.getPaddle(),gameState.getScoreCounter(),gameState.getSize());
        this.writer = new WriteGameStateIntoFile();
    }
    
    public Memento(GameState gameState, GameStateWriter writer){
        this(gameState);
        this.writer = writer;
    }

    public void initialGameState(Ball ball,Paddle paddle,ScoreCounter scoreCounter,Size size) {
       createInitialFile(ball,paddle,scoreCounter,size);
    }

    private void createInitialFile(Ball ball,Paddle paddle,ScoreCounter scoreCounter,Size size) {
        this.writer.saveGameState(ball, paddle, scoreCounter, size);
    }

    public GameState getSavedState() {
        Properties properties = readProperty();
        gameState = new GameState();
        if(properties != null){
            gameState.setExistState(true);
            gameState.setSize(new Size(Integer.parseInt(properties.getProperty("game.sizeW")), Integer.parseInt(properties.getProperty("game.sizeH"))));
            gameState.setBall(getBallFromState(properties));
            gameState.setLevelIndex(Integer.parseInt(properties.getProperty("game.levelIndex")));
            gameState.setPaddle(getPaddleFromState(properties));
            ScoreCounter score = new ScoreCounter();
            score.incScore((Integer.parseInt(properties.getProperty("game.score"))));
            gameState.setScoreCounter(score);
        }else {
            gameState.setExistState(false);
        }
        return gameState;
    }

    private Ball getBallFromState(Properties properties){
        Ball ball = new Ball();
        ball.setPosition(new Point(Double.parseDouble(properties.getProperty("ball.positionX")),
                Double.parseDouble(properties.getProperty("ball.positionY"))));
        Double angel = Double.parseDouble(properties.getProperty("ball.angel"));
        Speed ballSpeed = new Speed(angel.intValue());
        ballSpeed.X = Double.parseDouble(properties.getProperty("ball.x"));
        ballSpeed.Y = Double.parseDouble(properties.getProperty("ball.y"));
        ball.setSpeed(ballSpeed);
        return ball;
    }


    private Paddle getPaddleFromState(Properties properties) {
        Size size = new Size(Integer.parseInt(properties.getProperty("paddle.sizeW")),Integer.parseInt(properties.getProperty("paddle.sizeH")));
        Paddle paddle = new Paddle(gameState.getSize());
        if(size.getWidth() > 5){
            paddle = new ExpandedPaddle(new Point(Double.parseDouble(properties.getProperty("paddle.x")),
                    Double.parseDouble(properties.getProperty("paddle.y"))));
        }
        paddle.setPosition(new Point(Double.parseDouble(properties.getProperty("paddle.x")),
                Double.parseDouble(properties.getProperty("paddle.y"))));
        return paddle;
    }

    private static  Properties readProperty(){
        try {
            InputStream is = GameState.class.getClassLoader().getResourceAsStream("state.properties");
            if(is != null) {
                Properties stateProp = new Properties();
                stateProp.load(is);
                return stateProp;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
