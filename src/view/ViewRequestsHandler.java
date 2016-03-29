
package view;

import java.awt.geom.Rectangle2D;
import java.util.List;
import javax.swing.SwingUtilities;
import model.Map;
import model.MovingObject;

/**
 *Utility static class for communication between view and model
 * @author kovko
 */
public final class ViewRequestsHandler {
    /**
     * Translation constant from matrix to pixel representation
     */
    public static final int DEFAULT_MAP_RECT_SIZE = 40;
    
    private static Surface surface;
    private static GUI gui;
    
    /**
     * Utility class is not ment to be instanced!!!
     */
    private ViewRequestsHandler() throws Exception{
        throw new Exception("Utility class is not ment to be instanced!!!");
    }
    
    /**
     * Adds newline to string and calls {@link GUI#consoleWrite(java.lang.String) }
     * @param s - string to print to console
     */
    public static void consolePrintln(String s){
        gui.consoleWrite(s+"\n");
    }
    
    /**
     * Converts number to string and calls overloaded method {@link ViewRequestsHandler#consolePrintln(java.lang.String)  consolePrintln}
     * @param n - Number to print to console
     */
    public static void consolePrintln(Number n){
        consolePrintln(n.toString());
    }
    
    /**
     * Creates instances of GUI, creates menus, prepares Canvas
     */
    public static void prepareView(){
        surface = new Surface();
        gui = new GUI(surface);
        SwingUtilities.invokeLater(()->{gui.setVisible(true);});
    }
    
    /**
     * move animation request handler
     * @param mo
     */
    public static void repaintAll(List<MovingObject> mo) {
        surface.resetObjects();
        mo.forEach((o) -> {
            Rectangle2D.Float rect = new Rectangle2D.Float(o.getCoordinates().x*DEFAULT_MAP_RECT_SIZE, o.getCoordinates().y*DEFAULT_MAP_RECT_SIZE, DEFAULT_MAP_RECT_SIZE, DEFAULT_MAP_RECT_SIZE);
            surface.addObjects(new DrawableObject(o.isSokoban(), rect));
        });
        gui.repaint();
        gui.requestFocus();
    }
    
    /**
     * draws map on canvas
     * @param map - map to be drawn
     */
    public static void drawMap(Map map){
        char mapArray[][]=map.getMapArray();
        
        surface.setBackground(0, 0, DEFAULT_MAP_RECT_SIZE*15);
        
        for(int y=0; y<mapArray.length; y++){
            ViewRequestsHandler.consolePrintln(String.copyValueOf(mapArray[y]));
            for(int x=0; x<mapArray[y].length; x++){
                if(mapArray[y][x]=='1'){
                    surface.addWalls((x)*DEFAULT_MAP_RECT_SIZE, (y)*DEFAULT_MAP_RECT_SIZE, DEFAULT_MAP_RECT_SIZE);
                }
                else if(mapArray[y][x]=='C'){
                    surface.setExit(x*DEFAULT_MAP_RECT_SIZE, y*DEFAULT_MAP_RECT_SIZE, DEFAULT_MAP_RECT_SIZE);
                }
            }
        }
        
        surface.repaint();
    }
}
