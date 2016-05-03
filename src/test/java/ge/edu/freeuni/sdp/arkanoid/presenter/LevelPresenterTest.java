package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Configuration;
import ge.edu.freeuni.sdp.arkanoid.model.Game;
import ge.edu.freeuni.sdp.arkanoid.model.GameFacade;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class LevelPresenterTest {

    @Mock Configuration configurationMock;
    @Mock LevelSelectionListener levelSelectionListenerMock;
    private LevelPresenter levelPresenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Configuration.setInstanceForTesting(configurationMock);
        GameFacade gameFacade = new Game(new Size(0, 0));
        PresenterFactory presenterFactory = new PresenterFactory(gameFacade, new Size(0, 0));
        levelPresenter = presenterFactory.getLevelPresenter();
    }

    @Test
    public void setSelection_IndexOutOfTheLeftBound_ReturnsFalse(){
        boolean result = levelPresenter.setSelection(-1);
        assertFalse(result);
    }

    @Test
    public void setSelection_IndexOutOfTheRightBound_ReturnFalse(){
        LevelPresenter levelPresenterSpy = getLevelNamesMock();
        boolean result = levelPresenterSpy.setSelection(3);
        assertFalse(result);
    }

    @Test
    public void setSelection_SelectLevelZero_ConfigurationSetSelectedLevelIndexIsCalled(){
        LevelPresenter levelPresenterSpy = getLevelNamesMock();
        levelPresenterSpy.setLevelSelectionListener(levelSelectionListenerMock);
        levelPresenterSpy.setSelection(0);
        verify(configurationMock, times(1)).setSelectedLevelIndex(0);
    }

    @Test
    public void setSelection_SelectLevelOne_LevelSelectListenerIsNotified(){
        LevelPresenter levelPresenterSpy = getLevelNamesMock();
        levelPresenterSpy.setLevelSelectionListener(levelSelectionListenerMock);
        levelPresenterSpy.setSelection(1);
        verify(levelSelectionListenerMock, times(1)).levelSelectionChanged();
    }

    private LevelPresenter getLevelNamesMock(){
        LevelPresenter levelPresenterSpy = spy(levelPresenter);
        when(levelPresenterSpy.getLevelNames()).thenReturn(new String[]{"level1", "level2"});
        return levelPresenterSpy;
    }
}

