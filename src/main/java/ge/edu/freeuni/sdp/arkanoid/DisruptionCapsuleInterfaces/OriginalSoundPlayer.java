package ge.edu.freeuni.sdp.arkanoid.DisruptionCapsuleInterfaces;

import ge.edu.freeuni.sdp.arkanoid.SoundPlayer;

/**
 * Created by khrak on 5/4/16.
 */
public class OriginalSoundPlayer implements IPlayer {
    @Override
    public void play(String sound) {
        SoundPlayer.getInstance().play(sound);
    }
}
