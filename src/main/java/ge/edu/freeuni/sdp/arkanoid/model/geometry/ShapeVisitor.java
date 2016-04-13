package ge.edu.freeuni.sdp.arkanoid.model.geometry;

/**
 * Created by rezo on 4/13/16.
 */
public interface ShapeVisitor {
     default boolean visit(Circle elem){
         return false;
     }
    default boolean visit(Rectangle elem){
        return false;
    }
}
