package main.models;

import java.io.Serializable;
import java.util.*;

/**
 * A class for Maps of the game.
 */
public class Map implements Serializable {

    private static final long serialVersionUID = 1L;
//    private int id;
//    private boolean isActive;
    private String name;
    private List<Monster> monsters;
    private Player player;
    private String[][] path;

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

    public List<Monster> getMonsters() { return this.monsters; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }

    /**
     * Add Monster object to the list. If the list isn't existed, create new empty lit.
     * @param monster the Monster object to be added.
     */
    public void addMonster(Monster monster) {
        if (this.monsters == null) {
            this.monsters = new ArrayList<>();
        }
        this.monsters.add(monster);
    }

    public Player getPlayer() { return this.player; }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String[][] getPath() { return this.path; }

    /**
     * Initialize the path with given width and height.
     * @param width the width of the path.
     * @param height the height of the path.
     */
    public void setPath(int width, int height) {
        this.path = new String[width][height];
    }
}