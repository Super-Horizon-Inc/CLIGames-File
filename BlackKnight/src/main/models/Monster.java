package main.models;
<<<<<<< HEAD:BlackKnight/src/main/models/Monster.java
=======

import java.io.Serializable;
>>>>>>> b0bbeca1f4371aca4d0d9acfe6171f28125ea007:BlackKnight/src/models/Monster.java

/**
 * A class for Monsters of the game.
 */
public class Monster extends Character {

    private Boolean isVisible;

    public Boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) { this.isVisible = isVisible; }
}