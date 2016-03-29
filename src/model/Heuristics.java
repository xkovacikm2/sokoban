package model;

import controller.SimulationController;

/**
 * @author Michal Kováčik
 * @since Mar 28, 2016
 */
public class Heuristics {

    private Heuristics() {
    }

    public static Heuristics getInstance() {
        return HeuristicsHolder.INSTANCE;
    }

    private static class HeuristicsHolder {

        private static final Heuristics INSTANCE = new Heuristics();
    }

    public int evaluate(Box box) {
        Coordinates finalpos = SimulationController.getInstance().getMap().getFinalPosition();
        Coordinates bc = box.getCoordinates();

        return (Math.abs(bc.x - finalpos.x) + Math.abs(bc.y - finalpos.y));
    }
}
