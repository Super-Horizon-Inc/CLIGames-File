package main.models;

import java.io.Serializable;
import java.util.*;

/**
 * A class for Maps of the game.
 */
public class Map implements Serializable {

//    private int id;
//    private boolean isActive;
    private String name;
    private Set<Character> characters;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable (
//            name = "Map_Characters",
//            joinColumns = @JoinColumn(name = "Map_Id", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "Character_Id", referencedColumnName = "Id")
//    )

//    public int getId() { return this.id; }
//
//    public void setId(int id) {
//        if (id <= 0) throw new IllegalArgumentException("Only positive number.");
//        this.id = id;
//    }

//    public Boolean getIsActive() {
//        return this.isActive;
//    }
//
//    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public String getName() { return this.name; }

    public void setName(String name) {
        if (name.length() < 1 || name.length() > 50) throw new IllegalArgumentException("Only between 1 and 50 characters.");
        this.name = name;
    }

    public Set<Character> getCharacter() { return this.characters; }

    public void setCharacter(Character character) {
        if (this.characters == null) {
            this.characters = new HashSet<Character>();
        }
        this.characters.add(character);
    }
}