package main.view_model;

import main.models.Map;

/**
 * A class for managing Map and Character generator. Use Singleton design pattern.
 */
public class DependencyFactory {

    private static MapDriver mapDriver;
    private static CharacterDriver characterDriver;

    /**
     * Static method to create Map generator.
     */
    public static MapDriver getMapDriver() {
        if (mapDriver == null) {
            mapDriver = new MapDriver(new Map());
        }
        return mapDriver;
    }

    /**
     * Static method to create Character generator.
     */
    public static CharacterDriver getCharacterDriver() {
        if (characterDriver == null) {
            characterDriver = new CharacterDriver();
        }
        return characterDriver;
    }
}
