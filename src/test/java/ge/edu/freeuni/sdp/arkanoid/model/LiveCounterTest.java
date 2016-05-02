package ge.edu.freeuni.sdp.arkanoid.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiveCounterTest {
    @Test
    public void testLiveCounterDefaultValue() {
        LiveCounter liveCounter = new LiveCounter();
        assertEquals(3, liveCounter.getLive());
    }

    @Test
    public void testLiveCounterWithSpecificValue() {
        int specificValue = 5;
        LiveCounter liveCounter = new LiveCounter(specificValue);
        assertEquals(specificValue, liveCounter.getLive());
    }

    @Test
    public void testLiveCounterIncrease() {
        int startValue = 5;
        int increaseBy = 10;

        LiveCounter liveCounter = new LiveCounter(startValue);
        for (int i = 0; i < increaseBy; ++i) {
            liveCounter.increase();
        }

        assertEquals(startValue + increaseBy, liveCounter.getLive());
    }

    @Test
    public void testLiveCounterDecrease() {
        int startValue = 10;
        int decreaseBy = 5;

        LiveCounter liveCounter = new LiveCounter(startValue);
        for (int i = 0; i < decreaseBy; ++i) {
            liveCounter.decrease();
        }

        assertEquals(startValue - decreaseBy, liveCounter.getLive());
    }

    @Test
    public void testLiveCounterDecreaseBelowZero() {
        int startValue = 5;
        int decreaseBy = 10;

        LiveCounter liveCounter = new LiveCounter(startValue);
        for (int i = 0; i < decreaseBy; ++i) {
            liveCounter.decrease();
        }

        assertEquals(0, liveCounter.getLive());
    }
}
