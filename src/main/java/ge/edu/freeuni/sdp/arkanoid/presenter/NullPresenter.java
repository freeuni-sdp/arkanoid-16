package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class NullPresenter extends RectanglePresenter<Gobj> {

    NullPresenter(Gobj gobj, Size size) {
        super(gobj, size);
    }

    @Override
    CellContent getContent() {
        return CellContent.None;
    }
}
