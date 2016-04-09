/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.view.terminal;

import ge.edu.freeuni.sdp.arkanoid.view.GameOverView;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.presenter.GameOverPresenter;

/**
 *
 * @author dato
 */
public class TerminalGameOverView extends GameOverView {

    private final Terminal _terminal;
    private final GameOverPresenter _presenter;
    private final Size _roomSize;
    
    public TerminalGameOverView(GameOverPresenter presenter, Terminal terminal) {
        super(presenter);
        _terminal = terminal;
        _presenter = presenter;
        _roomSize = presenter.getRoomSize();
    }

    @Override
    protected void show() {
        draw();
        
        while (true){
            Key input = _terminal.readInput();
            if (input == null) continue;
            if (input.getCharacter() == 'y'){
                _presenter.setGameActivity(true);
                break;
            }
            if (input.getKind() == Key.Kind.Escape ||
                input.getCharacter() == 'n') {
                _presenter.setGameActivity(false);
                break;
            }
        }
    }
    
    private void draw(){
        _terminal.clearScreen();
        Size roomSize = _presenter.getRoomSize();
        
        String[] texts = new String[GAME_OVER_TEXTS_COUNT];
        fillTexts(texts);
        
        for (int i = 0; i < texts.length; i++) {
            String currentText = texts[i];
            int column = (_roomSize.getWidth() - currentText.length()) / 2;
            int row = _roomSize.getHeight()/2 - (texts.length/2 - i);
            
            writeLine(currentText, column, row);
        }
        
        _terminal.flush();
    }
    
    private void fillTexts(String[] texts) {
        texts[0] = GAME_OVER_MESSAGE;
        texts[1] = GAME_OVER_QUESTION;
        texts[2] = GAME_CONTINUE;
        texts[3] = GAME_QUIT;
    }
    
    private void writeLine(String text, int col, int row){
        _terminal.moveCursor(col, row);
        writeText(text);
    }
    
    private void writeText(String line){
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++){
            _terminal.putCharacter(chars[i]);
        }
    }
    
    private static final String GAME_OVER_MESSAGE =  "Game Over";
    private static final String GAME_OVER_QUESTION = "Do you want to play again?";
    private static final String GAME_CONTINUE =      "Press Y to play again.    ";
    private static final String GAME_QUIT =          "Press N or ESC to quit.   ";
    
    private static final int GAME_OVER_TEXTS_COUNT = 4;

}
