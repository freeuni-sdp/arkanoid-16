package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.Brick;
import ge.edu.freeuni.sdp.arkanoid.model.Gobj;

public class GobjPresenterFactory {
    GobjPresenter getPresenter(Gobj gobj) {
        switch (gobj.getKind()) {

            case Brick:
                return new BrickPresenter((Brick) gobj);
            case Paddle:
                break;
            case Ball:
                break;
            case Capsule:
                break;
        }
        return new NullPresenter(gobj);
    }
}
