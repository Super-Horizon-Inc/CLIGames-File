package main.view_model;

import main.models.*;
import java.util.*;

/**
 * A class for generating Player and Monster objects.
 */
public class CharacterDriver {
    public Player player = new Player();
    public List<Monster> monsters = new ArrayList<>();

    private final int numberOfMonster = 15; //total number of monsters at the beginning

    /**
     * Constructor. Generate player and monsters.
     */
    public CharacterDriver() {
        setPlayer();
        setMonsters();
    }

    /**
     * Initialize player fields.
     */
    private void setPlayer() {
        player.setName("Player");   //default name
        player.setIcon((char) 0x263B);   //default icon for player
        player.setHealthPotion(30);
        player.setHealth(100);
        player.setCharacterLevel(1);
        player.setExp(0);
        player.setPosition(new Position(0,0));
        player.setGold(0);
//        player.addWeapons(new Weapon("Gun"));
//        player.addWeapons(new Weapon("Sword"));
//        player.addWeapons(new Weapon("Axe"));   //max 3 weapons
    }

    /**
     * Initialize monster and add to monsters list.
     */
    private void setMonsters() {
        for (int i = 0; i < numberOfMonster; i++) {
            Random random = new Random();

            Monster monster = new Monster();
            monster.setHealth(100);
            monster.setCharacterLevel(1);
            monster.setExp(random.nextInt(50)+1);
            monster.setName("Monster");
            if (i < numberOfMonster/3) monster.setIsVisible(true);  //at least a few visible monsters
            else monster.setIsVisible(random.nextBoolean());

            int x = random.nextInt(30);
            int y = random.nextInt(15) + 1;
            while (x == player.getPosition().getX() && y == player.getPosition().getY()) {
                x = random.nextInt(30);
                y = random.nextInt(15) + 1;
            }
            monster.setPosition(new Position(x,y));
            monster.setIcon((char) 0x2620); //default icon for monsters (only visible ones appear)

            monsters.add(monster);
        }
    }
}
