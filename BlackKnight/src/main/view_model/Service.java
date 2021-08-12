package main.view_model;

import main.dao.FileDao;
import main.models.Map;
import main.models.Monster;
import main.models.Player;
import main.models.Position;
import main.view.MapDesign;
import java.util.*;

/**
 * A class contains Business logic of the game.
 * A class for managing user inputs and the flow of the game. Serve as a bridge between model layer and view layer.
 */
public class Service {
    //map size
    private final int width;
    private final int height;

    //statistics
    private final int playerBaseDamage = 40;
    private final int monsterBaseDamage = 30;
    private final int potionHealAmount = 50;
    private final int healthPotionDropChance = 75;    //percentage
    private final int baseGoldDropAmount = 10;
    private final int baseExp = 50;         //base exp to level up
    private final double exponent = 1.5;    //difficulty of leveling up to next level

    //map model objects
    private Player player;
    private List<Monster> monsters;
    private MapDesign mapDesign;
    private FileDao fileDao;
    char[] playerIcons = {0x26F9, 0x26C4, 0x263A, 0x263B};  //unicode character

    /**
     * Constructor. Initialize important fields of the map.
     * @param width the width of the map.
     * @param height the height of the map.
     */
    public Service(int width, int height) {
        this.width = width;
        this.height = height;
        player = DependencyFactory.getCharacterDriver().player;
        monsters = DependencyFactory.getCharacterDriver().monsters;
        mapDesign = new MapDesign(width, height, player, monsters);
        fileDao = new FileDao();
    }

    /**
     * The most important method of the game, where the game starts.
     * Control the flow of the game. Receive user inputs and giving corresponding responses.
     * Do business computations like: calculate damage dealt, damage dealt, exp receive, exp to level up, health potion, ...
     */
    public void startService() {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("\nWelcome to Black Knight!");
        System.out.print("Do you want to load the saved game? (y/n) ");
        String loadGame = input.next();
        while (true) {
            if (loadGame.equalsIgnoreCase("y")) {
                try {
                    loadMap();
                    break;
                }
                catch (Exception e) {
                    System.out.println("No saved game available. Starting a new game ...");
                    loadGame = "n";
                }
            }
            else if (loadGame.equalsIgnoreCase("n")) {
                System.out.print("Please enter player name: ");
                String playerName = input.next();
                player.setName(playerName);
                System.out.print("Choose your icon (1." + playerIcons[0] + "  2." + playerIcons[1] + "  3." + playerIcons[2] + "  4." + playerIcons[3] + "): ");
                if (input.hasNextInt()) {
                    int iconOption = input.nextInt();
                    if (iconOption > 0 && iconOption <= playerIcons.length) {
                        player.setIcon(playerIcons[iconOption-1]);
                    }
                    else {
                        System.out.println("Invalid option. The default icon is chosen.");
                    }
                }
                else {
                    input.next();
                    System.out.println("Invalid option. The default icon is chosen.");
                }
                System.out.println();
                break;
            }
            else {
                System.out.println("Invalid input. Please try again.");
                System.out.print("Do you want to load previous game? (y/n) ");
                loadGame = input.next();
            }
        }

        mapDesign.createUI(null);
        System.out.print("Your move: ");
        String move = input.next();
        System.out.println();
        while (!move.equalsIgnoreCase("q")) {
            int x = player.getPosition().getX();
            int y = player.getPosition().getY();

            if (move.equalsIgnoreCase("w")) {
                if (y > 0) player.setPosition(new Position(x,y-1));
                else System.out.println("You can't move up.");
            }
            else if (move.equalsIgnoreCase("a")) {
                if (x > 0) player.setPosition(new Position(x-1,y));
                else System.out.println("You can't move left.");
            }
            else if (move.equalsIgnoreCase("s")) {
                if (y < height-1) player.setPosition(new Position(x,y+1));
                else System.out.println("You can't move down.");
            }
            else if (move.equalsIgnoreCase("d")) {
                if (x < width-1) player.setPosition(new Position(x+1,y));
                else System.out.println("You can't move right.");
            }
            else if (move.equalsIgnoreCase("c")) {
                saveMap();
            }
            else if (move.equalsIgnoreCase("v")) {
                try {
                    loadMap();
                } catch (Exception e) {
                    System.out.println("No saved game available.\n");
                }
            }
            else if (move.equalsIgnoreCase("b")) {
                //add more to shop in the future
                int hpPrice = 10;
                System.out.println("Health potion: " + hpPrice + " gold/each.");
                System.out.print("How many health potions do you want to purchase? ");
                if (input.hasNextInt()) {
                    int hpPurchaseAmount = input.nextInt();
                    if (hpPurchaseAmount > 0) {
                        int goldAmount = hpPurchaseAmount * hpPrice;
                        if (player.getGold() >= goldAmount) {
                            player.setGold(player.getGold() - goldAmount);
                            player.setHealthPotion(player.getHealthPotion() + hpPurchaseAmount);
                            System.out.println("You've purchased " + hpPurchaseAmount + " health potions.\n");
                        }
                        else {
                            System.out.println("You don't have enough gold.\n");
                        }
                    }
                    else {
                        System.out.println("Purchase unsuccessful. The amount to purchase should be greater than 0.\n");
                    }
                }
                else {
                    input.next();
                    System.out.println("Purchase unsuccessful. The amount to purchase should be an integer.\n");
                }
            }
            else {
                System.out.println("Invalid move.");
            }

            Iterator<Monster> iterator = monsters.iterator();   //avoid removing element in enhanced for loop

            CHECK_MONSTERS:
            while (iterator.hasNext()) {
                Monster monster = iterator.next();
                if (player.getPosition().getX() == monster.getPosition().getX() && player.getPosition().getY() == monster.getPosition().getY()) {
                    mapDesign.createUI(monster);
                    System.out.println("You've encountered " + monster.getName() + ".");

                    while (monster.getHealth() > 0) {
                        System.out.print("Your choice: ");
                        String choice = input.next();
                        System.out.println();

                        if (choice.equalsIgnoreCase("1")) {
                            //formula for damages
                            int damageDealt = (int) (rand.nextInt(playerBaseDamage) * Math.pow(1.1, player.getCharacterLevel()));   //scale up damage
                            int damageTaken = (int) (rand.nextInt(monsterBaseDamage) * Math.pow(1.01, monster.getExp()));  //scale up damage

                            int playerHealth = Math.max((player.getHealth() - damageTaken), 0);
                            int monsterHealth = Math.max((monster.getHealth() - damageDealt), 0);

                            player.setHealth(playerHealth);
                            monster.setHealth(monsterHealth);

                            mapDesign.createUI(monster);

                            System.out.println("You've dealt " + damageDealt + " to " + monster.getName() + ".");
                            System.out.println("Monster's dealt " + damageTaken + " to you.");

                            if (player.getHealth() < 1) {
                                System.out.println("Your health is too low to continue.");
                                break;
                            }
                        }
                        else if (choice.equalsIgnoreCase("2")) {
                            if (player.getHealthPotion() > 0) {
                                player.setHealth(player.getHealth() + potionHealAmount);
                                player.setHealthPotion(player.getHealthPotion()-1);
                                mapDesign.createUI(monster);
                                System.out.println("You've used 1 health potion.");
                            }
                            else {
                                mapDesign.createUI(monster);
                                System.out.println("You have no health potion left.");
                            }
                        }
                        else if (choice.equalsIgnoreCase("3")) {
                            System.out.println("You've escaped successfully.\n");
                            continue CHECK_MONSTERS;    //stop checking player and monsters positions, ready to move
                        }
                        else {
                            System.out.println("Invalid option.");
                        }
                    }

                    if (player.getHealth() < 1) {
                        System.out.println("\nYou were defeated.");
                        System.out.println("Game over!");
                        return;
                    }

                    //monster's dead
                    iterator.remove();
                    System.out.println("\n" + monster.getName() + " was defeated.");

                    //health
                    boolean isHealthPotionDrop = rand.nextInt(100) < healthPotionDropChance;    //chance of dropping health potion
                    if (isHealthPotionDrop) {
                        player.setHealthPotion(player.getHealthPotion()+1);
                        System.out.println("You've got a health potion.");
                    }

                    //gold
                    int goldDrop = rand.nextInt(baseGoldDropAmount);
                    player.setGold(player.getGold() + goldDrop);
                    System.out.println("You've received " + goldDrop + " gold.");

                    //exp & level
                    player.setExp(player.getExp() + monster.getExp());
                    System.out.println("You've gained " + monster.getExp() + " exp.\n");
                    int nextLevelExp = (int) Math.floor(baseExp * (Math.pow(player.getCharacterLevel(), exponent))); //formula for leveling
                    if (player.getExp() >= nextLevelExp) {
                        System.out.println("Level up!\n");
                        player.setCharacterLevel(player.getCharacterLevel()+1);
                    }
                }
            }

            mapDesign.createUI(null);
            System.out.print("Your move: ");
            move = input.next();
            System.out.println();
        }

        System.out.println("Black Knight is closing ...");
    }

    /**
     * Load the map and assign to corresponding objects.
     */
    public void loadMap() {
        Map loadedMap = fileDao.loadMap();
        player = loadedMap.getPlayer();
        monsters = loadedMap.getMonsters();
        mapDesign = new MapDesign(width, height, player, monsters);
        System.out.println("Successfully loaded.\n");
    }

    /**
     * Save important fields of the map.
     */
    public void saveMap() {
        Map savedMap = new Map();
        savedMap.setPlayer(player);
        for (Monster monster : monsters) savedMap.addMonster(monster);
        fileDao.saveOrUpdateMap(savedMap);
        System.out.println("Successfully saved.\n");
    }
}