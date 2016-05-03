package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Configuration;
import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.Level;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Direction;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by nika on 5/3/16.
 */
public class RoomPresenterTest {

    private TestableRoomPresenter roomPresenter;
    @Mock private Configuration mConfiguration;
    @Mock private GameFacade mGameFacade;
    @Mock private GobjPresenterFactory mGobjPresenterFactory;
    @Mock private Level mSelectedLevel;
    @Mock private StatusUpdateListener mStatusUpdateListener;
    @Mock private Gobj mGobj1;
    @Mock private Gobj mGobj2;
    private TestCellUpdateListener cellUpdateListener;
    private Set<Gobj> gameGobjs1 = new HashSet<>();
    private Set<Gobj> gameGobjs12 = new HashSet<>();
    private String[] levelNames = {"1", "2", "3"};
    private int gameLives = 5, gameScore = 6;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Size gameSize = new Size(10, 17);
        when(mGameFacade.getSize()).thenReturn(gameSize);

        roomPresenter = new TestableRoomPresenter(mGameFacade, mGobjPresenterFactory);
        roomPresenter.setConfiguration(mConfiguration);

        mSelectedLevel = mock(Level.class);
        when(mConfiguration.getSelectedLevel()).thenReturn(mSelectedLevel);
        when(mConfiguration.getLevelNames()).thenReturn(levelNames);

        when(mGameFacade.geLives()).thenReturn(gameLives);
        when(mGameFacade.getScore()).thenReturn(gameScore);

        cellUpdateListener = new TestCellUpdateListener();
        roomPresenter.setStatusUpdateListener(mStatusUpdateListener);

        gameGobjs1.add(mGobj1);
        gameGobjs12.add(mGobj1);
        gameGobjs12.add(mGobj2);

        roomPresenter.init();
    }

    @After
    public void tearDown() {
        gameGobjs1.clear();
        gameGobjs12.clear();
    }

    @Test
    public void init_gameInitWithSelectedLevel() throws Exception {
        verify(mGameFacade, times(1)).init(eq(mSelectedLevel));
    }

    @Test
    public void isGameOver_gameOver_returnTrue() throws Exception {
        when(mGameFacade.isGameOver()).thenReturn(true);
        assertTrue(roomPresenter.isGameOver());
    }

    @Test
    public void isGameOver_gameNotOver_returnFalse() throws Exception {
        when(mGameFacade.isGameOver()).thenReturn(false);
        assertFalse(roomPresenter.isGameOver());
    }

    @Test
    public void execute_None_gameCallMoveWithNone() throws Exception {
        roomPresenter.execute(Command.None);
        verify(mGameFacade).move(Direction.NONE);
    }

    @Test
    public void execute_Stop_gameCallMoveWithNone() throws Exception {
        roomPresenter.execute(Command.Stop);
        verify(mGameFacade).move(Direction.NONE);
    }

    @Test
    public void execute_Left_gameCallMoveWithLEFT() throws Exception {
        roomPresenter.execute(Command.Left);
        verify(mGameFacade).move(Direction.LEFT);
    }

    @Test
    public void execute_Right_gameCallMoveWithRIGHT() throws Exception {
        roomPresenter.execute(Command.Right);
        verify(mGameFacade).move(Direction.RIGHT);
    }

    @Test
    public void execute_Fire_gameCallFire() throws Exception {
        roomPresenter.execute(Command.Fire);
        verify(mGameFacade).fire();
    }

    @Test
    public void execute_Fire_gameCallMoveWithNone() throws Exception {
        roomPresenter.execute(Command.Fire);
        verify(mGameFacade).move(Direction.NONE);
    }

    @Test
    public void execute_Pause_gameCallPause() throws Exception {
        roomPresenter.execute(Command.Pause);
        verify(mGameFacade).pause();
    }

    @Test
    public void execute_Pause_gameCallMoveWithNone() throws Exception {
        roomPresenter.execute(Command.Pause);
        verify(mGameFacade).move(Direction.NONE);
    }

    @Test
    public void execute_levelNotClearedFirstLevel_NoLevelChange() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(0);
        when(mGameFacade.isLevelCleared()).thenReturn(false);
        roomPresenter.execute(Command.None);
        verify(mConfiguration, times(0)).setSelectedLevelIndex(anyInt());
    }

    @Test
    public void execute_levelNotClearedFirstLevel_NoGameOver() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(0);
        when(mGameFacade.isLevelCleared()).thenReturn(false);
        roomPresenter.execute(Command.None);
        assertFalse(roomPresenter.isGameOver());
    }

    @Test
    public void execute_levelNotClearedLastLevel_NoLevelChange() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(levelNames.length - 1);
        when(mGameFacade.isLevelCleared()).thenReturn(false);
        roomPresenter.execute(Command.None);
        verify(mConfiguration, times(0)).setSelectedLevelIndex(anyInt());
    }

    @Test
    public void execute_levelNotClearedLastLevel_NoGameOver() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(levelNames.length - 1);
        when(mGameFacade.isLevelCleared()).thenReturn(false);
        roomPresenter.execute(Command.None);
        assertFalse(roomPresenter.isGameOver());
    }

    @Test
    public void execute_levelClearedFirstLevel_levelChangedToNext() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(0);
        when(mGameFacade.isLevelCleared()).thenReturn(true);
        roomPresenter.execute(Command.None);
        verify(mConfiguration).setSelectedLevelIndex(eq(1));
    }

    @Test
    public void execute_levelClearedFirstLevel_gameNotOver() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(0);
        when(mGameFacade.isLevelCleared()).thenReturn(true);
        roomPresenter.execute(Command.None);
        assertFalse(roomPresenter.isGameOver());
    }

    @Test
    public void execute_levelClearedLastLevel_noLevelChanged() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(levelNames.length - 1);
        when(mGameFacade.isLevelCleared()).thenReturn(true);
        roomPresenter.execute(Command.None);
        verify(mConfiguration, times(0)).setSelectedLevelIndex(anyInt());
    }

    @Test
    public void execute_levelClearedLastLevel_gameOver() throws Exception {
        when(mConfiguration.getSelectedLevelIndex()).thenReturn(levelNames.length - 1);
        when(mGameFacade.isLevelCleared()).thenReturn(true);
        roomPresenter.execute(Command.None);
        assertTrue(roomPresenter.isGameOver());
    }

    @Test
    public void execute_equalLives_notUpdateLives() throws Exception {
        when(mGameFacade.geLives()).thenReturn(gameLives);
        roomPresenter.execute(Command.None);
        verify(mStatusUpdateListener, times(0)).updateLives(anyInt());
    }

    @Test
    public void execute_notEqualLives_updateLives() throws Exception {
        int newLives = gameLives + 1;
        when(mGameFacade.geLives()).thenReturn(newLives);
        roomPresenter.execute(Command.None);
        verify(mStatusUpdateListener, times(1)).updateLives(newLives);
    }

    @Test
    public void execute_equalScores_notUpdateScores() throws Exception {
        when(mGameFacade.getScore()).thenReturn(gameScore);
        roomPresenter.execute(Command.None);
        verify(mStatusUpdateListener, times(0)).updateScore(anyInt());
    }

    @Test
    public void execute_notEqualScores_notUpdateScores() throws Exception {
        int newScore = gameScore + 1;
        when(mGameFacade.getScore()).thenReturn(newScore);
        roomPresenter.execute(Command.None);
        verify(mStatusUpdateListener, times(1)).updateScore(newScore);
    }

    @Test
    public void execute_emptyGobjs_noCellUpdate() throws Exception {
        when(mGameFacade.getGobjs()).thenReturn(Collections.emptySet());
        roomPresenter.set_cellUpdateListener(cellUpdateListener);
        roomPresenter.execute(Command.None);
        assertEquals(cellUpdateListener.numCalls(), 0);
    }

    @Test
    public void execute_gameGobjs1_presenterFactoryCallGetPresenter() throws Exception {
        when(mGameFacade.getGobjs()).thenReturn(gameGobjs1);
        GobjPresenter mPresenter = mock(GobjPresenter.class);
        when(mGobjPresenterFactory.getPresenter(mGobj1)).thenReturn(mPresenter);
        roomPresenter.execute(Command.None);
        verify(mGobjPresenterFactory).getPresenter(eq(mGobj1));
    }

    @Test
    public void execute_gameGobjs1_presenterCallDraw() throws Exception {
        when(mGameFacade.getGobjs()).thenReturn(gameGobjs1);
        GobjPresenter mPresenter = mock(GobjPresenter.class);
        when(mGobjPresenterFactory.getPresenter(mGobj1)).thenReturn(mPresenter);
        roomPresenter.execute(Command.None);
        verify(mPresenter).Draw(any());
    }

    @Test
    public void execute_gameGobjs1_cellUpdate() throws Exception {
        when(mGameFacade.getGobjs()).thenReturn(gameGobjs1);
        CellPosition position = new CellPosition(0, 0);
        OnePieceGobjPresenter mPresenter = new OnePieceGobjPresenter(
                mGameFacade.getSize(), mGobj1,
                position, CellContent.ACapsule
        );
        when(mGobjPresenterFactory.getPresenter(mGobj1)).thenReturn(mPresenter);
        roomPresenter.set_cellUpdateListener(cellUpdateListener);
        roomPresenter.execute(Command.None);
        assertEquals(cellUpdateListener.numCalls(), 1);
        assertEquals(cellUpdateListener.peekPosition().x, position.x);
        assertEquals(cellUpdateListener.peekPosition().y, position.y);
        assertEquals(cellUpdateListener.peekConente(), CellContent.ACapsule);
    }

    @Test
    public void executeTwice_gameGobjs1_cellUpdateOnce() throws Exception {
        when(mGameFacade.getGobjs()).thenReturn(gameGobjs1);
        CellPosition position = new CellPosition(0, 0);
        OnePieceGobjPresenter mPresenter = new OnePieceGobjPresenter(
                mGameFacade.getSize(), mGobj1,
                position, CellContent.ACapsule
        );
        when(mGobjPresenterFactory.getPresenter(mGobj1)).thenReturn(mPresenter);
        roomPresenter.set_cellUpdateListener(cellUpdateListener);
        roomPresenter.execute(Command.None);
        roomPresenter.execute(Command.None);
        assertEquals(cellUpdateListener.numCalls(), 1);
    }

    @Test
    public void execute_gameGobjs1NoUpdateListener_noUpdates() throws Exception {
        when(mGameFacade.getGobjs()).thenReturn(gameGobjs1);
        CellPosition position = new CellPosition(0, 0);
        OnePieceGobjPresenter mPresenter = new OnePieceGobjPresenter(
                mGameFacade.getSize(), mGobj1,
                position, CellContent.ACapsule
        );
        when(mGobjPresenterFactory.getPresenter(mGobj1)).thenReturn(mPresenter);
        roomPresenter.execute(Command.None);
        roomPresenter.execute(Command.None);
        assertEquals(cellUpdateListener.numCalls(), 0);
    }

    @Test
    public void executeTwise_gameGobjs1AndGameGobjs12_cellUpdateTwice() throws Exception {
        CellPosition position1 = new CellPosition(0, 0);
        CellPosition position2 = new CellPosition(0, 1);
        CellContent content1 = CellContent.ACapsule;
        CellContent content2 = CellContent.Ball;
        OnePieceGobjPresenter mPresenter1 = new OnePieceGobjPresenter(
                mGameFacade.getSize(), mGobj1,
                position1, content1
        );
        OnePieceGobjPresenter mPresenter2 = new OnePieceGobjPresenter(
                mGameFacade.getSize(), mGobj1,
                position2, content2
        );
        roomPresenter.set_cellUpdateListener(cellUpdateListener);
        when(mGobjPresenterFactory.getPresenter(mGobj1)).thenReturn(mPresenter1);
        when(mGobjPresenterFactory.getPresenter(mGobj2)).thenReturn(mPresenter2);
        when(mGameFacade.getGobjs()).thenReturn(gameGobjs1);
        roomPresenter.execute(Command.None);
        when(mGameFacade.getGobjs()).thenReturn(gameGobjs12);
        roomPresenter.execute(Command.None);

        assertEquals(cellUpdateListener.numCalls(), 2);

        // Assert last update is of gobj2
        assertEquals(cellUpdateListener.peekPosition().x, position2.x);
        assertEquals(cellUpdateListener.peekPosition().y, position2.y);
        assertEquals(cellUpdateListener.peekConente(), content2);
        cellUpdateListener.pop();

        // Assert first update is of gobj1
        assertEquals(cellUpdateListener.peekPosition().x, position1.x);
        assertEquals(cellUpdateListener.peekPosition().y, position1.y);
        assertEquals(cellUpdateListener.peekConente(), content1);
    }

}

class TestableRoomPresenter extends RoomPresenter {

    private Configuration conf;

    TestableRoomPresenter(GameFacade game, GobjPresenterFactory gobjPresenterFactory) {
        super(game, gobjPresenterFactory);
    }

    public void setConfiguration(Configuration conf) {
        this.conf = conf;
    }

    @Override
    protected Configuration getConfiguration() {
        return this.conf;
    }

}

class OnePieceGobjPresenter extends GobjPresenter {

    private CellPosition position;
    private CellContent value;

    OnePieceGobjPresenter(Size size, Gobj gameObject,
                          CellPosition position, CellContent value) {
        super(size, gameObject);
        this.position = position;
        this.value = value;
    }

    @Override
    void Draw(CellContent[][] cells) {
        cells[this.position.x][this.position.y] = this.value;
    }
}

class TestCellUpdateListener implements CellUpdateListener {

    private Stack<CellPosition> updatedPositions;
    private Stack<CellContent> updatedContent;

    TestCellUpdateListener() {
        this.updatedPositions = new Stack<>();
        this.updatedContent = new Stack<>();
    }

    public CellPosition peekPosition() {
        return updatedPositions.peek();
    }

    public CellContent peekConente() {
        return updatedContent.peek();
    }

    public void pop() {
        updatedPositions.pop();
        updatedContent.pop();
    }

    public int numCalls() {
        return updatedPositions.size();
    }

    @Override
    public void updateCell(CellPosition position, CellContent content) {
        updatedPositions.push(position);
        updatedContent.push(content);
    }
}
