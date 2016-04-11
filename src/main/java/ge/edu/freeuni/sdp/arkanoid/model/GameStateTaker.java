package ge.edu.freeuni.sdp.arkanoid.model;

/**
 * Created by root_pc on 4/11/2016.
 */
public class GameStateTaker {

    private Memento saveState;

    public void saveMemento(Memento memento){
        saveState = memento;
    }

    public Memento getMemento(){
        return this.saveState;
    }
}
