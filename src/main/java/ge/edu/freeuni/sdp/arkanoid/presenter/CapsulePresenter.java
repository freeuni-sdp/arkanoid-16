package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Capsule;
import ge.edu.freeuni.sdp.arkanoid.model.Size;

public class CapsulePresenter extends GobjPresenter<Capsule> {

    CapsulePresenter(Capsule gameObject, Size size) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {
        return CellContent.Capsule;
    }
}
