package main.models;
<<<<<<< HEAD:BlackKnight/src/main/models/Position.java
=======

import java.io.Serializable;
>>>>>>> b0bbeca1f4371aca4d0d9acfe6171f28125ea007:BlackKnight/src/models/Position.java

/**
 * A class for position of Character
 */
public class Position {

    private Byte x;
    private Byte y;

    public Position() {}

    public Position(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public Byte getX() { return this.x; }

    public void setX(byte x) { this.x = x; }

    public Byte getY() { return this.y; }

    public void setY(byte y) { this.y = y; }
}