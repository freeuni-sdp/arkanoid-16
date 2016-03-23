package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;
import ge.edu.freeuni.sdp.arkanoid.model.Size;

public class NullPresenter extends GobjPresenter {

    NullPresenter(Gobj gobj, Size size) {
        super(gobj, size);
    }

    @Override
    CellContent getContent() {
        return CellContent.None;
    }
}
