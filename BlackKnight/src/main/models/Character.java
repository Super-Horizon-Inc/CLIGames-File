package main.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
//import javax.validation.constraints.*;

/**
 * An abstract class for holding common fields, and actions of Player and Monster classes.
 */
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorFormula("case when gold is null then 'Player' else 'Monster' end")
public abstract class Character implements Serializable {

    //private int id;
    private int health;
    private int characterLevel;
    private int exp;
    private String name;
    private Position position;
    private List<Weapon> weapons;
    private char icon;

    // Hibernate One-to-Many Association on Join Table Annotations Example
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "Character_Weapons",
//            joinColumns = @JoinColumn(name = "Character_Id", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "Weapon_Id", referencedColumnName = "Id")
//    )

    public Character() {
        //set default values
        this.health = (int)100;
    }

//    public int getId() { return this.id; }
//
//    public void setId(int id) {
//        if (id <= 0) throw new IllegalArgumentException("Only positive number.");
//        this.id = id;
//    }

    public int getHealth() { return this.health; }

    public void setHealth(int health) {
        if (health < 0) throw new IllegalArgumentException("Only positive number or zero.");
        this.health = health;
    }

    public int getCharacterLevel() { return this.characterLevel; }

    public void setCharacterLevel(int characterLevel) {
        if (characterLevel <= 0) throw new IllegalArgumentException("Only positive number.");
        this.characterLevel = characterLevel;
    }

    public int getExp() { return this.exp; }

    public void setExp(int exp) {
        if (exp < 0) throw new IllegalArgumentException("Only positive number or zero.");
        this.exp = exp;
    }

    public String getName() { return this.name; }

    public void setName(String name) {
        if (name.length() < 1 || name.length() > 40) throw new IllegalArgumentException("Only between 1 and 40 characters.");
        this.name = name;
    }

    public Position getPosition() { return this.position; }

    public void setPosition(Position position) {
        if (position == null) throw new IllegalArgumentException("Position can't be null.");
        this.position = position;
    }

    public List<Weapon> getWeapons() { return this.weapons; }

    public void setWeapons(List<Weapon> weapons) { this.weapons = weapons; }

    /**
     * Add Weapon object to the list. If the list isn't existed, create new empty lit. Maximum 3 weapons in the list.
     * @param weapon the Weapon object to be added.
     */
    public void addWeapons(Weapon weapon) {
        if (this.weapons == null) {
            this.weapons = new ArrayList<>();
        }
        //max 3 weapons
        if (weapons.size() >= 3) {
            this.weapons.remove(0);
        }
        this.weapons.add(weapon);
    }

    public char getIcon() { return this.icon; }

    public void setIcon(char icon) {
        if (icon == 0) throw new IllegalArgumentException("Icon can't be null");
        this.icon = icon;
    }
}