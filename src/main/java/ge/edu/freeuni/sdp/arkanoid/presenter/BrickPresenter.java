package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Brick;
import ge.edu.freeuni.sdp.arkanoid.model.BrickColor;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class BrickPresenter extends RectanglePresenter<Brick> {


    BrickPresenter(Brick gameObject, Size size) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {
        Brick brick = getGameObject();
        BrickColor color = brick.getColor();
        return color2Content(color);
    }

    private CellContent color2Content(BrickColor color) {
        switch (color) {
            case Red:
                return CellContent.RedBrick;
        }
        return CellContent.None;
    }
}
