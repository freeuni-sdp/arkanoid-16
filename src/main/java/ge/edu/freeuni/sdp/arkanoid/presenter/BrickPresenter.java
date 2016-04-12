package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Brick;
import ge.edu.freeuni.sdp.arkanoid.model.BrickColor;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class BrickPresenter extends RectanglePresenter<Brick> {


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
            case Blue:
                return CellContent.BlueBrick;
            case White:
                return CellContent.WhiteBrick;
            case Orange:
                return CellContent.OrangeBrick;
            case LightBlue:
                return CellContent.LightBlueBrick;
            case Green:
                return CellContent.GreenBrick;
            case DarkBlue:
                return CellContent.DarkBlueBrick;
            case LightOrange:
                return CellContent.LightOrangeBrick;
            case Yellow:
                return CellContent.YellowBrick;

        }
        return CellContent.None;
    }
}
