package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Capsule;

public class CapsulePresenter extends GobjPresenter<Capsule> {

    CapsulePresenter(Capsule gameObject) {
        super(gameObject);
    }

    @Override
    CellContent getContent() {
        return CellContent.Capsule;
    }
}
