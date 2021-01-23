package main.view_model;

import main.models.MainCharacter;
import main.models.Map;

public class DependencyFactory {

    private static MapDriver mapDriver;
    private static CharacterDriver characterDriver;

    public static MapDriver getMapDriver() {
        if (mapDriver == null) {
            return new MapDriver(new Map());
        }
        else return mapDriver;
    }

    public static CharacterDriver getCharacterDriver() {
        if (characterDriver == null) {
            return new CharacterDriver();
        }
        else return characterDriver;
    }
}
