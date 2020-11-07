package main.view_model;

import main.models.Map;

public class MapDriver {
    Map map;

    public MapDriver(Map map) {
        this.map = map;
    }

    public String[][] getMapPath(int width, int height) {
        map.setPath(width, height);
        return map.getPath();
    }

}
