package controller;

import java.util.ArrayList;
import java.util.List;
import model.Box;
import model.MovingObject;
import model.Sokoban;
import view.ViewRequestsHandler;

/**
 * @author Michal Kováčik
 * @since Mar 29, 2016
 */
public class AnimationController {

    private Sokoban sokoban = null;
    private Box box = null;
    private List<Integer> steps = null;

    private AnimationController() {
    }

    public static AnimationController getInstance() {
        return AnimationControllerHolder.INSTANCE;
    }

    private static class AnimationControllerHolder {

        private static final AnimationController INSTANCE = new AnimationController();
    }

    public Sokoban getSokoban() {
        return sokoban;
    }

    public void setSokoban(Sokoban sokoban) {
        this.sokoban = sokoban;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public List<Integer> getSteps() {
        return steps;
    }

    public void setSteps(List<Integer> steps) {
        this.steps = steps;
    }

    public void animationStep() {
        List<MovingObject> list = new ArrayList<>();
        if(!steps.isEmpty()){
            switch (steps.remove(0)) {
                    case 1:
                        sokoban = sokoban.moveLeft();
                        break;
                    case 2:
                        sokoban = sokoban.moveRight();
                        break;
                    case 3:
                        sokoban = sokoban.moveDown();
                        break;
                    case 4:
                        sokoban = sokoban.moveUp();
                        break;
                    case 5:
                        sokoban = sokoban.moveLeft();
                        box=box.moveLeft();
                        break;
                    case 6:
                        sokoban = sokoban.moveRight();
                        box=box.moveRight();
                        break;
                    case 7:
                        sokoban = sokoban.moveDown();
                        box=box.moveDown();
                        break;
                    case 8:
                        sokoban = sokoban.moveUp();
                        box=box.moveUp();
                        break;
                    default: break;
                }
        }
        list.add(box);
        list.add(sokoban);
        ViewRequestsHandler.repaintAll(list);
    }
}
