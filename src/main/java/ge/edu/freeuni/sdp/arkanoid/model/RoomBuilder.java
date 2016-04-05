package ge.edu.freeuni.sdp.arkanoid.model;

interface RoomBuilder {
    void build(Room room, ScoreCounter scoreCounter);
    void setLevelClearedListener(LevelClearedListener listener);
}


