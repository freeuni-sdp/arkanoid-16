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

    public Memento(){}

    public Memento(GameState gameState){
        this.gameState = gameState;
        initialGameState(gameState.getBall(),gameState.getPaddle(),gameState.getScoreCounter(),gameState.getSize());
    }

    public void initialGameState(Ball ball,Paddle paddle,ScoreCounter scoreCounter,Size size) {
       createInitialFile(ball,paddle,scoreCounter,size);
    }

    private void createInitialFile(Ball ball,Paddle paddle,ScoreCounter scoreCounter,Size size) {
        try {
            File file = new File("resources/state.properties");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
            Writer w = new BufferedWriter(osw);
            w.write("ball.x=" + ball.getSpeed().X + "\n");
            w.write("ball.y=" + ball.getSpeed().Y + "\n");
            w.write("ball.positionX=" + ball.getPosition().X + "\n");
            w.write("ball.positionY=" + ball.getPosition().Y + "\n");
            w.write("ball.angel=" + ball.getSpeed().getAngleDegrees() + "\n");
            w.write("paddle.x=" + paddle.getPosition().X + "\n");
            w.write("paddle.y=" + paddle.getPosition().Y + "\n");
            w.write("paddle.sizeW=" + paddle.getShape().getSize().getWidth() + "\n");
            w.write("paddle.sizeH=" + paddle.getShape().getSize().getHeight() + "\n");
            w.write("game.score=" + scoreCounter.getScore() + "\n");
            w.write("game.sizeH=" + size.getHeight() + "\n");
            w.write("game.sizeW=" + size.getWidth() + "\n");
            w.write("game.levelIndex=" + Configuration.getInstance().getSelectedLevelIndex() + "\n");
            w.flush();
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
