package view;

import controller.AnimationController;
import controller.SimulationController;
import java.awt.BorderLayout;
import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Michal Kováčik
 * @since Mar.3.2016
 */
public class GUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BUTTON_OFFSET = 10;
    public static final int BUTTON_HEIGHT = 20;
    public static final int BUTTON_WIDTH = 120;

    private JPanel menu;
    private TextArea console;
    private JButton stepBtn;

    public GUI(Surface surface) {
        setTitle("Sokoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setMenu();
        setConsole();
        add(surface, BorderLayout.CENTER);
        add(menu, BorderLayout.EAST);
        add(console, BorderLayout.SOUTH);
        setFocusable(true);
    }

    /**
     * Appends string to {@link GUI#console}
     *
     * @param s
     */
    public void consoleWrite(String s) {
        console.append(s);
    }

    private void setConsole() {
        console = new TextArea();
        console.setSize(WIDTH, 200);
    }

    private void setMenu() {
        menu = new JPanel();
        createButton("Choose File", menu).addActionListener(e -> SimulationController.getInstance().initCalculation());
        stepBtn = createButton("Step", menu);
        stepBtn.addActionListener(e -> AnimationController.getInstance().animationStep());
        stepBtn.setVisible(false);
    }

    private JButton createButton(String name, JPanel parent) {
        JButton button = new JButton(name);
        int x = parent.getBounds().x + (BUTTON_WIDTH * (parent.getComponentCount() % 2)) + (BUTTON_OFFSET * (parent.getComponentCount() % 2));
        int y = parent.getBounds().y + (BUTTON_HEIGHT * (parent.getComponentCount() / 2)) + (BUTTON_OFFSET * (parent.getComponentCount() / 2));
        button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        parent.add(button);
        return button;
    }

    public void setBtnStepVisible() {
        stepBtn.setVisible(true);
    }

}
