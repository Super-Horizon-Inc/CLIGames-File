package models;

import java.util.*;

/**
 * A class for Main Character of the game.
 */
public class MainCharacter extends Character {

    private Integer gold;
    private Set<Skill> skills;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "Character_Skills",
//            joinColumns = @JoinColumn(name = "Character_Id", referencedColumnName = "Id"),
//            inverseJoinColumns = @JoinColumn(name = "Skill_Id", referencedColumnName = "Id")
//    )

    public Integer getGold() { return this.gold; }

    public void setGold(int gold) {
        if (gold < 0) throw new IllegalArgumentException("Only positive number or zero.");
        this.gold = gold;
    }

    public Set<Skill> getSkills() { return this.skills; }

    public void setSkills(Skill skill) {
        if (this.skills == null) {
            this.skills = new HashSet<Skill>();
        }
        this.skills.add(skill);
    }
}