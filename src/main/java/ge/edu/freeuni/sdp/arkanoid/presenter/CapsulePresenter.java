package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Capsule;
import ge.edu.freeuni.sdp.arkanoid.model.ExpandCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class CapsulePresenter extends RectanglePresenter<Capsule> {

    CapsulePresenter(Capsule gameObject, Size size) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {

        Capsule capsule = getGameObject();

        if (capsule instanceof ExpandCapsule) {
            return CellContent.ECapsule;
        }
        return CellContent.None;
    }
}
