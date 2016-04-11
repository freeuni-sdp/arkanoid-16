package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Beam;
import ge.edu.freeuni.sdp.arkanoid.model.BrickColor;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class BeamPresenter extends RectanglePresenter<Beam> {

    BeamPresenter(Size size, Beam gameObject) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {
        Beam beam = getGameObject();
        return CellContent.Beam;
    }

}