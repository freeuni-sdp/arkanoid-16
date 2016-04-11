package ge.edu.freeuni.sdp.arkanoid.model;

/**
 * Created by root_pc on 4/11/2016.
 */
public class Originator {

    private GameState gameState;


    public void set(GameState gameState){
        this.gameState =  gameState;
    }

    public Memento saveToMemento(){
        return new Memento(gameState);
    }

    public void restoreFromMemento(Memento m) {
        gameState = m.getSavedState();
    }


}
