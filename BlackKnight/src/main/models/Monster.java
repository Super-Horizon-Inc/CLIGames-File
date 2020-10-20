package main.models;

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