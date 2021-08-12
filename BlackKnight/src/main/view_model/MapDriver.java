package main.view_model;

import main.models.Map;

/**
 * A class for generating Map object.
 */
public class MapDriver {
    Map map;

    /**
     * Constructor. Generate map of the game.
     * @param map the Map object.
     */
    public MapDriver(Map map) {
        this.map = map;
    }

    /**
     * Initialize the map with given size.
     * @param width the width of the map.
     * @param height the height of the map.
     * @return the 2D array path of the map.
     */
    public String[][] getMapPath(int width, int height) {
        map.setPath(width, height);
        return map.getPath();
    }
}