/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.edu.freeuni.sdp.arkanoid.model;

import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

/**
 *
 * @author dato
 */
public class MementoTest {
    
    @Mock private GameState gameState;
    @Mock private Ball ball;
    @Mock private Paddle paddle;
    
    private StubGameStateWriter stubWriter;
    
    @Before
    public void setUp() {
        stubWriter = new StubGameStateWriter();
    }

    /**
     * Test of initialGameState method, of class Memento.
     */
//    @Test
//    public void initialGameStateSaving() {
////        Memento memento = new Memento(gameState, stubWriter);
////        memento.initialGameState(ball, paddle, new ScoreCounter(), new Size(10, 10));
//    }

    /**
     * Test of getSavedState method, of class Memento.
     */
//    @Test
//    public void getSavedState() {
//    }
    
}
