package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

class CapsulePresenter extends RectanglePresenter<Capsule> {

    CapsulePresenter(Capsule gameObject, Size size) {
        super(gameObject, size);
    }

    @Override
    CellContent getContent() {

        Capsule capsule = getGameObject();

        if (capsule instanceof ExpandCapsule) {
            return CellContent.ECapsule;
        } else if (capsule instanceof BreakCapsule) {
            return CellContent.BCapsule;
        } else if (capsule instanceof AutopilotCapsule) {
            return CellContent.ACapsule;
        } else if (capsule instanceof ExtraLiveCapsule) {
            return CellContent.PCapsule;
        } else if (capsule instanceof DisruptionCapsule) {
            return CellContent.DCapsule;
        } else if (capsule instanceof KillCapsule) {
            return CellContent.KillCapsule;
        } else if (capsule instanceof SlowBallCapsule) {
            return CellContent.SCapsule;
        } else if (capsule instanceof LaserCapsule) {
            return CellContent.LCapsule;
        } else if (capsule instanceof StickyPaddleCapsule) {
            return CellContent.CCapsule;
        }

        return CellContent.None;
    }
}
