package models;

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