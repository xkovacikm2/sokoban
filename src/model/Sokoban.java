/*
 * Copyright (C) 2015, eProvement s.r.o. All rights reserved.
 */

package model;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class Sokoban extends MovingObject{

    public Sokoban(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public Sokoban moveLeft() {
        return new Sokoban(new Coordinates(coordinates.x-1, coordinates.y));
    }

    @Override
    public Sokoban moveRight() {
        return new Sokoban(new Coordinates(coordinates.x+1, coordinates.y));
    }

    @Override
    public Sokoban moveDown() {
        return new Sokoban(new Coordinates(coordinates.x, coordinates.y+1));
    }

    @Override
    public Sokoban moveUp() {
        return new Sokoban(new Coordinates(coordinates.x, coordinates.y-1));
    }
    
    public boolean canPushLeft(Box box){
        return box.coordinates.match(new Coordinates(coordinates.x-1, coordinates.y));
    }
    
    public boolean canPushRight(Box box){
        return box.coordinates.match(new Coordinates(coordinates.x+1, coordinates.y));
    }
    
    public boolean canPushUp(Box box){
        return box.coordinates.match(new Coordinates(coordinates.x, coordinates.y-1));
    }
    
    public boolean canPushDown(Box box){
        return box.coordinates.match(new Coordinates(coordinates.x, coordinates.y+1));
    }
    
    @Override
    public boolean isSokoban(){
        return true;
    }
}
