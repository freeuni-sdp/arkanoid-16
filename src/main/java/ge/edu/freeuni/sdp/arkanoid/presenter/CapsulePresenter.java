package ge.edu.freeuni.sdp.arkanoid.presenter;


import ge.edu.freeuni.sdp.arkanoid.model.AutopilotCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.BreakCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.Capsule;
import ge.edu.freeuni.sdp.arkanoid.model.ExpandCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.ExtraLiveCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.KillCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;
import ge.edu.freeuni.sdp.arkanoid.model.SlowBallCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.DisruptionCapsule;
import ge.edu.freeuni.sdp.arkanoid.model.LaserCapsule;

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
        }
        return CellContent.None;
    }
}
