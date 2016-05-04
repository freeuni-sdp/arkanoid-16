/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author dato
 */
public class WriteGameStateIntoFile implements GameStateWriter {

    public WriteGameStateIntoFile(){}
    
    @Override
    public void saveGameState(Ball ball, Paddle paddle, ScoreCounter scoreCounter, Size size) {
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
    
}
