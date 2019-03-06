package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class Map {

    private char[][] mapArray;
    private List<Coordinates> walls = null;
    private Coordinates finalPos = null;

    public Map(List<String> mapArray) {
        this.mapArray = new char[mapArray.size()][];
        int i = 0;
        for (String line : mapArray) {
            this.mapArray[i] = line.toCharArray();
            i++;
        }
    }

    private Coordinates getPositionByChar(char c) {
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                if (mapArray[i][j] == c) {
                    return new Coordinates(j, i);
                }
            }
        }
        return null;
    }

    public Coordinates getSokobanInitialPosition() {
        return getPositionByChar('S');
    }

    public Coordinates getBoxInitialPosition() {
        return getPositionByChar('B');
    }

    public List<Coordinates> getWallsPositions() {
        if (walls == null) {
            walls = new ArrayList<>();
            for (int i = 0; i < mapArray.length; i++) {
                for (int j = 0; j < mapArray[i].length; j++) {
                    if (mapArray[i][j] == '1') {
                        walls.add(new Coordinates(j, i));
                    }
                }
            }
        }
        return walls;
    }

    public Coordinates getFinalPosition() {
        if (finalPos == null) {
            finalPos = getPositionByChar('C');
        }
        return finalPos;
    }

    public char[][] getMapArray() {
        return mapArray;
    }
}
