/*
 * Copyright (C) 2015, eProvement s.r.o. All rights reserved.
 */

package model;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class Coordinates {
    public final int x;
    public final int y;
    
    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public Coordinates clone(){
        return new Coordinates(x, y);
    }
    
    public boolean match(Coordinates c){
        return (c.x==x && c.y==y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Coordinates)
            return match((Coordinates)obj);
        return false;
    }

    @Override
    public int hashCode() {
        return y*100+x;
    }
    
    
}
