package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import javax.swing.JFileChooser;
import model.Box;
import model.Coordinates;
import model.Map;
import model.MapParser;
import model.MovingObject;
import model.Sokoban;
import model.State;
import model.StateValidator;
import view.ViewRequestsHandler;

/**
 * @author Michal Kováčik
 * @since Mar 3, 2016
 */
public class SimulationController {

    private SimulationController() {
    }

    private Map map;
    private PriorityQueue<State> stateQueue;
    private HashMap<Integer, State> existingStates;

    public static SimulationController getInstance() {
        return ModePickControllerHolder.INSTANCE;
    }

    private void showGUI() {
        ViewRequestsHandler.prepareView();
        ViewRequestsHandler.drawMap(map);
        List<MovingObject> list = new ArrayList<>();
        list.add(stateQueue.peek().getSokoban());
        list.add(stateQueue.peek().getBox());
        ViewRequestsHandler.repaintAll(list);
    }

    private static class ModePickControllerHolder {

        private static final SimulationController INSTANCE = new SimulationController();
    }

    public void initCalculation() {
        map = MapParser.getInstance().parseFile(loadFile());
        stateQueue = new PriorityQueue<>();
        stateQueue.add(createInitState());
        showGUI();
        existingStates = new HashMap<>();
        verbose(calculatePath());
    }

    private State createInitState() {
        return new State(new ArrayList<>(), State.INITIAL, new Sokoban(map.getSokobanInitialPosition()), new Box(map.getBoxInitialPosition()));
    }

    private List<Integer> calculatePath() {
        while (!stateQueue.isEmpty()) {
            State s = stateQueue.poll();

            if (StateValidator.isFinal(s)) {
                return s.getOperators();
            }

            s.generateFollowers().forEach(f -> {
                if (!existingStates.containsKey(f.hashCode()) && StateValidator.isValid(f)) {
                    stateQueue.add(f);
                    existingStates.put(f.hashCode(), f);
                }
            });
        }
        return null;
    }

    private void verbose(List<Integer> steps) {
        if (steps == null) {
            ViewRequestsHandler.consolePrintln("Failed");
        } else {
            prepareAnimation(new ArrayList<>(steps), new Sokoban(map.getSokobanInitialPosition()), new Box(map.getBoxInitialPosition()));
            steps.forEach(s -> {
                switch (s) {
                    case 0:
                        ViewRequestsHandler.consolePrintln("INITIAL");
                        break;
                    case 1:
                        ViewRequestsHandler.consolePrintln("MOVE_LEFT");
                        break;
                    case 2:
                        ViewRequestsHandler.consolePrintln("MOVE_RIGHT");
                        break;
                    case 3:
                        ViewRequestsHandler.consolePrintln("MOVE_DOWN");
                        break;
                    case 4:
                        ViewRequestsHandler.consolePrintln("MOVE_UP");
                        break;
                    case 5:
                        ViewRequestsHandler.consolePrintln("PUSH_LEFT");
                        break;
                    case 6:
                        ViewRequestsHandler.consolePrintln("PUSH_RIGHT");
                        break;
                    case 7:
                        ViewRequestsHandler.consolePrintln("PUSH_DOWN");
                        break;
                    case 8:
                        ViewRequestsHandler.consolePrintln("PUSH_UP");
                        break;
                }
            });
        }
    }

    private void prepareAnimation(List<Integer> steps, Sokoban sokoban, Box box) {
        steps.remove(0);
        AnimationController.getInstance().setBox(box);
        AnimationController.getInstance().setSokoban(sokoban);
        AnimationController.getInstance().setSteps(steps);
        ViewRequestsHandler.setBtnStepVisible();
    }

    private File loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(fileChooser);
        return fileChooser.getSelectedFile();
    }

    public Map getMap() {
        return map;
    }

}
