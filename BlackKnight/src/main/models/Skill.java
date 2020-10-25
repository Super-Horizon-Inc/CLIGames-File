package main.models;
<<<<<<< HEAD:BlackKnight/src/main/models/Skill.java
=======

import java.io.Serializable;
>>>>>>> b0bbeca1f4371aca4d0d9acfe6171f28125ea007:BlackKnight/src/models/Skill.java

/**
 * A class for Skills of Main Character of the game.
 */
public class Skill {

//    private int id;
    private String name;
    public Skill() {}

    public Skill(String name) { this.name = name; }

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