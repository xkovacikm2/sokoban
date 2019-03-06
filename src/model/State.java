package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class State implements Comparable<State> {

    private final int heuristicValue;
    private final List<Integer> operations;
    private final Sokoban sokoban;
    private final Box box;

    public static final int INITIAL = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_DOWN = 3;
    public static final int MOVE_UP = 4;
    public static final int PUSH_LEFT = 5;
    public static final int PUSH_RIGHT = 6;
    public static final int PUSH_DOWN = 7;
    public static final int PUSH_UP = 8;

    public State(List<Integer> operations, Integer operation, Sokoban sokoban, Box box) {
        this.operations = operations;
        this.operations.add(operation);
        this.sokoban = sokoban;
        this.box = box;
        heuristicValue = Heuristics.getInstance().evaluate(box);
    }

    /**
     * return value 0 != equals true.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(State o) {
        int BEFORE = -1;
        int EQ = 0;
        int AFTER = 1;

        if (this.value() < o.value()) {
            return BEFORE;
        }
        if (this.value() > o.value()) {
            return AFTER;
        }
        return EQ;
    }

    public int value() {
        return heuristicValue + operations.size();
    }

    public Sokoban getSokoban() {
        return sokoban;
    }

    public Box getBox() {
        return box;
    }

    @Override
    public int hashCode() {
        return sokoban.getCoordinates().hashCode()*10000+box.getCoordinates().hashCode();
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public List<Integer> getOperators() {
        return operations;
    }

    public List<State> generateFollowers() {
        List<State> followers = new ArrayList<>();

        followers.add(new State(new ArrayList<>(operations),
                MOVE_DOWN,
                sokoban.moveDown(),
                box)
        );

        followers.add(new State(new ArrayList<>(operations),
                MOVE_UP,
                sokoban.moveUp(),
                box)
        );

        followers.add(new State(new ArrayList<>(operations),
                MOVE_LEFT,
                sokoban.moveLeft(),
                box)
        );

        followers.add(new State(new ArrayList<>(operations),
                MOVE_RIGHT,
                sokoban.moveRight(),
                box)
        );

        if (sokoban.canPushDown(box)) {
            followers.add(new State(new ArrayList<>(operations),
                    PUSH_DOWN,
                    sokoban.moveDown(),
                    box.moveDown())
            );
        }

        if (sokoban.canPushUp(box)) {
            followers.add(new State(new ArrayList<>(operations),
                    PUSH_UP,
                    sokoban.moveUp(),
                    box.moveUp())
            );
        }

        if (sokoban.canPushLeft(box)) {
            followers.add(new State(new ArrayList<>(operations),
                    PUSH_LEFT,
                    sokoban.moveLeft(),
                    box.moveLeft())
            );
        }

        if (sokoban.canPushRight(box)) {
            followers.add(new State(new ArrayList<>(operations),
                    PUSH_RIGHT,
                    sokoban.moveRight(),
                    box.moveRight())
            );
        }

        return followers;
    }
}
