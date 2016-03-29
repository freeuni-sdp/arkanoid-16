package ge.edu.freeuni.sdp.arkanoid.model;

public class ScoreCounter {

    private int _score;

    public int getScore() {
        return _score;
    }

    public void incScore(int score) {
        _score += score;
    }
}
