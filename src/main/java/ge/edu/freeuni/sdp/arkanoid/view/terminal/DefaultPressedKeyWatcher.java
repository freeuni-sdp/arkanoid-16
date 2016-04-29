package ge.edu.freeuni.sdp.arkanoid.view.terminal;

public class DefaultPressedKeyWatcher {

    private static DefaultPressedKeyWatcher getInstance = new DefaultPressedKeyWatcher();

    public static DefaultPressedKeyWatcher getInstance() {
        return getInstance;
    }

    private DefaultPressedKeyWatcher() {
    }

    public void init(){
        PressedKeyWatcher.init();
    }


    boolean isLeftPressed() {
        return PressedKeyWatcher.isLeftPressed();

    }

    boolean isRightPressed() {
        return PressedKeyWatcher.isRightPressed();
    }

    boolean isSpacePressed() {
        return PressedKeyWatcher.isSpacePressed();
    }

    boolean isEscapePressed() {
        return PressedKeyWatcher.isEscapePressed();
    }

    boolean isPausedPressed() {
        return PressedKeyWatcher.isPausedPressed();
    }





}
