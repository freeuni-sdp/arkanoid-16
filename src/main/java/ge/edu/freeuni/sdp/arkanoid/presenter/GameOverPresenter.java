/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

/**
 *
 * @author dato
 */
public class GameOverPresenter extends Presenter {
    
    private final GameFacade _game;
    private boolean gameIsActive;
 
    public GameOverPresenter(GameFacade gameFacade){
        _game = gameFacade;
    }
    
    public Size getRoomSize(){
        return _game.getSize();
    }
    
    public void setGameActivity(boolean active){
        gameIsActive = active;
    }

    public boolean isGameActive() {
        return gameIsActive;
    }
}
