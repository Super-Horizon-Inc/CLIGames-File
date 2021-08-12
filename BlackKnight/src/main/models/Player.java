package main.models;

import java.io.Serializable;

/**
 * A class for the Player of the game.
 */
public class Player extends Character implements Serializable{

    private Integer gold;
    private int healthPotion;

    public Integer getGold() { return this.gold; }

    public void setGold(int gold) {
        if (gold < 0) throw new IllegalArgumentException("Only positive number or zero.");
        this.gold = gold;
    }

    public int getHealthPotion() { return this.healthPotion; }

    public void setHealthPotion(int healthPotion) {
        if (healthPotion < 0) throw new IllegalArgumentException("Only positive number or zero.");
        this.healthPotion = healthPotion;
    }
}