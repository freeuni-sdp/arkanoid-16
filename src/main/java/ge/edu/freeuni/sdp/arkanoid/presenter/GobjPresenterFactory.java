package ge.edu.freeuni.sdp.arkanoid.presenter;

import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.model.geometry.Size;

public class GobjPresenterFactory {

    private final Size _size;

    GobjPresenterFactory(Size size) {

        _size = size;
    }

    GobjPresenter getPresenter(Gobj gobj) {
        switch (gobj.getKind()) {

            case Brick:
                return new BrickPresenter((Brick) gobj, _size);
            case Paddle:
                return new PaddlePresenter((Paddle) gobj, _size);
            case Ball:
                return new BallPresenter((Ball) gobj, _size);
            case Capsule:
                return new CapsulePresenter((Capsule) gobj, _size);
        }
        return new NullPresenter(gobj, _size);
    }
}
