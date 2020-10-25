package main.view_model;

import main.models.Map;

public class MapDriver {
    Map map = new Map();
    public String[][] createPath(int width, int height) {
        map.setPath(width, height);
        String[][] path = map.getPath();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                path[j][i] = ". ";
            }
        }
        return path;
    }
}
