/*
 * Copyright (C) 2015, eProvement s.r.o. All rights reserved.
 */
package model;

import controller.SimulationController;
import java.util.List;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class StateValidator {

    public static boolean isValid(State state) {
        return (notInWalls(state)
                & sokobanNotInBox(state)
                & isBoxMovable(state));
    }

    public static boolean isFinal(State state) {
        return SimulationController.getInstance().getMap().getFinalPosition().match(state.getBox().getCoordinates());
    }

    private static boolean isBoxMovable(State state) {
        Box box = state.getBox();
        List<Coordinates> c = SimulationController.getInstance().getMap().getWallsPositions();
        if (!isFinal(state)) {
            //wall right and up of the box
            if (c.contains(new Coordinates(box.getCoordinates().x + 1, box.getCoordinates().y))
                    && c.contains(new Coordinates(box.getCoordinates().x, box.getCoordinates().y - 1))) {
                return false;
            }

            //wall right and down of the box
            if (c.contains(new Coordinates(box.getCoordinates().x + 1, box.getCoordinates().y))
                    && c.contains(new Coordinates(box.getCoordinates().x, box.getCoordinates().y + 1))) {
                return false;
            }

            //wall left and down of the box
            if (c.contains(new Coordinates(box.getCoordinates().x - 1, box.getCoordinates().y))
                    && c.contains(new Coordinates(box.getCoordinates().x, box.getCoordinates().y + 1))) {
                return false;
            }

            //wall left and up of the box
            if (c.contains(new Coordinates(box.getCoordinates().x - 1, box.getCoordinates().y))
                    && c.contains(new Coordinates(box.getCoordinates().x, box.getCoordinates().y - 1))) {
                return false;
            }
        }
        return true;
    }

    private static boolean notInWalls(State state) {
        for (Coordinates c : SimulationController.getInstance().getMap().getWallsPositions()) {
            if (c.match(state.getBox().getCoordinates()) || c.match(state.getSokoban().getCoordinates())) {
                return false;
            }
        }
        return true;
    }

    private static boolean sokobanNotInBox(State state) {
        return (!state.getBox().getCoordinates().match(state.getSokoban().getCoordinates()));
    }
}
