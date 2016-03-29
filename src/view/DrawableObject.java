package view;

import java.awt.geom.Rectangle2D;

/**
 * @author Michal Kováčik
 * @since Mar.3.2016
 */
public class DrawableObject {

    public final Boolean sokoban;

    /**
     * Geometrical representation of object
     */
    public final Rectangle2D.Float rect;

    public DrawableObject(Boolean sokoban, Rectangle2D.Float rect) {
        this.sokoban = sokoban;
        this.rect = rect;
    }

}
