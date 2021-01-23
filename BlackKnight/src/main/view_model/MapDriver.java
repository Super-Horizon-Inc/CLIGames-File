package main.view_model;

import main.models.MainCharacter;
import main.models.Character;
import main.models.Map;
import main.models.Position;

import java.util.List;

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