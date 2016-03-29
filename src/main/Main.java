
package main;

import controller.SimulationController;
import main.tests.TestMapParser;

/**
 * @author Michal Kováčik
 * @since Mar 3, 2016
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //runTests();
        SimulationController.getInstance().initCalculation();
    }

    private static void runTests(){
        new TestMapParser().testMapParser();
    }
}
