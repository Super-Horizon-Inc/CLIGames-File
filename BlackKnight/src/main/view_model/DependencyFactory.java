package main.view_model;

import main.models.Map;

public class DependencyFactory {

    public static MapDriver getMapDriver() {
        return new MapDriver(new Map());
    }
}
