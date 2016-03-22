package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Gobj;

public class NullPresenter extends GobjPresenter {

    NullPresenter(Gobj gobj) {
        super(gobj);
    }

    @Override
    CellContent getContent() {
        return CellContent.None;
    }
}
