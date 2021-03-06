package ge.edu.freeuni.sdp.arkanoid;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundPlayer {

    public static final String INTRO = "intro.wav";
    public static final String BOUNCE = "bounce.wav";
    public static final String PARRY = "parry.wav";
    public static final String EXTEND = "extend.wav";
    public static final String START = "start.wav";
    public static final String COLLIDE = "collide.wav";
    public static final String PAUSE = "pause.wav";
    public static final String AUTOPILOT_START = "power_up_a.wav";
    public static final String AUTOPILOT_EXPIRE = "power_up_a_expire.wav";

    private static SoundPlayer _instance;
    private final Map<String, Clip> _clipsCache;
    private final ClassLoader _classLoader;

    private SoundPlayer() {
        _clipsCache = new HashMap<>();
        _classLoader = getClass().getClassLoader();
    }

    //TODO: This singleton violates layering. Must be refactored.
    public static SoundPlayer getInstance() {
        if (_instance == null) {
            _instance = new SoundPlayer();
            _instance.init();
        }
        return _instance;
    }

    private void init() {
        getOrOpen(INTRO);
        getOrOpen(BOUNCE);
        getOrOpen(PARRY);
        getOrOpen(EXTEND);
        getOrOpen(START);
        getOrOpen(COLLIDE);
        getOrOpen(PAUSE);
        getOrOpen(AUTOPILOT_START);
        getOrOpen(AUTOPILOT_EXPIRE);
    }

    public void play(String soundName) {
        Clip clip = getOrOpen(soundName);
        if (clip == null) return;
        clip.setFramePosition(0);
        clip.start();
    }


    public void loop(String soundName) {
        Clip clip = getOrOpen(soundName);
        if (clip == null) return;
        clip.setFramePosition(0);
        clip.loop(42);
    }

    void close() {
        stopAll();
        _clipsCache.values().forEach(Clip::close);
    }

    public void stopAll() {
        _clipsCache.values().forEach(Clip::stop);
    }

    private Clip getOrOpen(String sound) {
        if (sound == null) return null;
        Clip clip = _clipsCache.get(sound);
        if (clip != null) return clip;
        clip = createClip(sound);
        if (clip != null) _clipsCache.put(sound, clip);
        return clip;
    }

    private Clip createClip(String sound) {
        try {
            Clip clip = AudioSystem.getClip();
            URL resource = _classLoader.getResource(sound);
            if (resource == null) return null;
            clip.open(AudioSystem.getAudioInputStream(resource.openStream()));
            return clip;
        } catch (LineUnavailableException e) {
            System.err.println("Ups! HTTP 404 - Not found ...");
            return null;
        } catch (IOException e) {
            System.err.println("Ups! HTTP 403 - Forbidden ...");
            return null;
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Ups! HTTP 415 Unsupported Media Type ...");
            return null;
        }
    }

    public static void setSoundPlayer(SoundPlayer soundPlayer){
        _instance = soundPlayer;
    }
}
