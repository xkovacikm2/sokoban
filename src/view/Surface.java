package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Michal Kováčik
 * @since Mar.3.2016
 */
public class Surface extends JPanel{
    
    private Rectangle2D.Float background;
    private Rectangle2D.Float exit;
    private final List<Rectangle2D.Float> walls;
    private List<DrawableObject> objects;
    
    private BufferedImage wallTexture;
    private BufferedImage exitTexture;
    private BufferedImage sokobanTexture;
    private BufferedImage boxTexture;
    private final Color backgroundColor;
    
    public Surface(){
        super();
        this.loadImages();
        this.walls = new ArrayList<>();
        this.objects = new ArrayList<>();
        this.backgroundColor = Color.white;
    }
    
    private void loadImages(){
        try {
            wallTexture = ImageIO.read(new File("images/brickwall.png"));
            exitTexture = ImageIO.read(new File("images/exit.png"));
            sokobanTexture = ImageIO.read(new File("images/sokoban.png"));
            boxTexture = ImageIO.read(new File("images/box.png"));
        } 
        catch (IOException ex) {
            JOptionPane.showMessageDialog(Surface.this, ex.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Prepares Graphics component to be drawn at and calls {@link Surface#doDrawing(java.awt.Graphics) }
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(backgroundColor);
        g2d.fill(background);
        
        walls.stream().forEach((wall) -> {
            g2d.drawImage(wallTexture, (int)wall.x, (int)wall.y, (int)wall.width, (int)wall.height, null);
        });
        
        g2d.drawImage(exitTexture, (int)exit.x, (int)exit.y, (int)exit.width, (int)exit.height, null);
                
        objects.stream().forEach((object) -> {
            if(object.sokoban){
                g2d.fill(object.rect);
                g2d.drawImage(sokobanTexture, (int)object.rect.x, (int)object.rect.y, (int)object.rect.width-5, (int)object.rect.height-5, null);
            }
            else{
                g2d.drawImage(boxTexture, (int)object.rect.x, (int)object.rect.y, (int)object.rect.width, (int)object.rect.height, null);
            }
        });
              
    }

    /**
     * Setter for {@link Surface#background}
     * @param x
     * @param y
     * @param size 
     */
    public void setBackground(int x, int y, int size) {
        this.background = new Rectangle2D.Float(x, y, size, size);
    }

    /**
     * Setter for {@link Surface#exit}
     * @param x
     * @param y
     * @param size 
     */
    public void setExit(int x, int y, int size) {
        this.exit = new Rectangle2D.Float(x, y, size, size);
    }
    
    /**
     * Creates new wall with given params and adds it to {@link Surface#walls}
     * @param x
     * @param y
     * @param size 
     */
    public void addWalls(int x, int y, int size) {
        this.walls.add(new Rectangle2D.Float(x, y, size, size));
    }

    /**
     * Adds Drawable object to {@link Surface#objects}
     * @param object 
     */
    public void addObjects(DrawableObject object) {
        this.objects.add(object);
    }
    
    /**
     * Empties {@link Surface#objects}
     */
    public void resetObjects(){
        this.objects= new ArrayList<>();
    }
    
}

