package model;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
abstract public class MovingObject {

    protected Coordinates coordinates;
    
    public MovingObject(Coordinates coordinates){
        this.coordinates=coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates.clone();
    }

    abstract public MovingObject moveLeft();

    abstract public MovingObject moveRight();

    abstract public MovingObject moveDown();

    abstract public MovingObject moveUp();
    
    public boolean isSokoban(){
        return false;
    }
}
