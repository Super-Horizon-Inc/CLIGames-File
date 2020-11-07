package main.models;

import java.util.*;
//import javax.validation.constraints.*;

/**
 * An abstract class for holding common fields, and actions of MainCharacter and Monster classes.
 */
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorFormula("case when gold is null then 'MainCharacter' else 'Monster' end")
public abstract class Character {

    //private int id;
    private Short health;
    private Short characterLevel;
    private String name;
    private Position position;
    private Set<Weapon> weapons;
    private String image;

    // Hibernate One-to-Many Association on Join Table Annotations Example
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "Character_Weapons",
//            joinColumns = @JoinColumn(name = "Character_Id", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "Weapon_Id", referencedColumnName = "Id")
//    )

    public Character() {
        //set default values
        this.health = (short)100;
    }

//    public int getId() { return this.id; }
//
//    public void setId(int id) {
//        if (id <= 0) throw new IllegalArgumentException("Only positive number.");
//        this.id = id;
//    }

    public Short getHealth() { return this.health; }

    public void setHealth(short health) {
        if (health < 0) throw new IllegalArgumentException("Only positive number or zero.");
        this.health = health;
    }

    public Short getCharacterLevel() { return this.characterLevel; }

    public void setCharacterLevel(short characterLevel) {
        if (characterLevel <= 0) throw new IllegalArgumentException("Only positive number.");
        this.characterLevel = characterLevel;
    }

    public String getName() { return this.name; }

    public void setName(String name) {
        if (name.length() < 1 || name.length() > 50) throw new IllegalArgumentException("Only between 1 and 50 characters.");
        this.name = name;
    }

    public Position getPosition() { return this.position; }

    public void setPosition(Position position) {
        if (position == null) throw new IllegalArgumentException("Position can't be null.");
        this.position = position;
    }

    public Set<Weapon> getWeapons() { return this.weapons; }

    public void setWeapons(Weapon weapon) {
        if (this.weapons == null) {
            this.weapons = new HashSet<Weapon>();
        }
        this.weapons.add(weapon);
    }

    public String getImage() { return this.image; }

    public void setImage(String image) {
        if ("".equals(image)) throw new IllegalArgumentException("Image path can't be null");
        this.image = image;
    }
}