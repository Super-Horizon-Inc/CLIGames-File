package main.view_model;

import main.models.MainCharacter;
import main.models.Map;
import main.models.Position;
import main.models.Weapon;

public class CharacterDriver {
    public MainCharacter mainCharacter = new MainCharacter();

    public CharacterDriver() {
        setCharacter();
    }

    private void setCharacter() {
        mainCharacter.setHealth(0);
        mainCharacter.setCharacterLevel(1);
        mainCharacter.setName("Bahram_is_stupid!!!");
        mainCharacter.setPosition(new Position(1,1));
        mainCharacter.setWeapons(new Weapon("gun"));
        mainCharacter.setWeapons(new Weapon("sword"));
        mainCharacter.setWeapons(new Weapon("tank"));
        mainCharacter.setWeapons(new Weapon("arrow"));
        mainCharacter.setWeapons(new Weapon("axe"));
        mainCharacter.setImage("image");
        mainCharacter.setGold(0);
        mainCharacter.setSkills(null);
    }
}
