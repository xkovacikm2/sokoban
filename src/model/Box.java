package model;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class Box extends MovingObject{

    public Box(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Box moveLeft() {
        return new Box(new Coordinates(coordinates.x-1, coordinates.y));
    }

    @Override
    public Box moveRight() {
        return new Box(new Coordinates(coordinates.x+1, coordinates.y));
    }

    @Override
    public Box moveDown() {
        return new Box(new Coordinates(coordinates.x, coordinates.y+1));
    }

    @Override
    public Box moveUp() {
        return new Box(new Coordinates(coordinates.x, coordinates.y-1));
    }

}
