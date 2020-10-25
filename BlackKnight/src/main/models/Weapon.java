package main.models;
<<<<<<< HEAD:BlackKnight/src/main/models/Weapon.java
=======

import java.io.Serializable;
>>>>>>> b0bbeca1f4371aca4d0d9acfe6171f28125ea007:BlackKnight/src/models/Weapon.java

/**
 * A class for Weapons of Characters of the game.
 */
public class Weapon {

//    private int id;
    private String name;

    public Weapon() {}

    public Weapon(String name) { this.name = name; }

//    public int getId() { return this.id; }
//
//    public void setId(int id) {
//        if (id <= 0) throw new IllegalArgumentException("Only positive number.");
//        this.id = id;
//    }

    public String getName() { return this.name; }

    public void setName(String name) {
        if (name.length() < 1 || name.length() > 50) throw new IllegalArgumentException("Only between 1 and 50 characters.");
        this.name = name;
    }
}