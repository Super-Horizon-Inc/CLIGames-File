package main.view;

import main.models.Player;
import main.models.Monster;
import main.view_model.DependencyFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of View layer for printing out user interface.
 */
public class MapDesign {
    private final int width;
    private final int height;
    private String[][] path;
    private Player player;
    private List<Monster> monsters;
    char space = 0x2005;        //unicode space goes after player and monsters icons

    /**
     * Constructor. Initialize important fields of map.
     * @param width the width of the map.
     * @param height the height of the map.
     * @param player the Player object.
     * @param monsters the list of monsters.
     */
    public MapDesign(int width, int height, Player player, List<Monster> monsters) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.monsters = monsters;
    }

    /**
     * Print the map and status table.
     * @param monster the Monster object if encountered. Otherwise null.
     */
    public void createUI(Monster monster) {
        int visibleMonsters = 0;
        int invisibleMonsters = 0;

        for (Monster m : monsters) {
            if (m.getIsVisible()) visibleMonsters++;
            else invisibleMonsters++;
        }

        String tableFormat = "|%-50s|%-50s|%n";

        this.path = this.initPath();    //assign values to path matrix before printing out
        List<String> firstColumn = initFirstColumn(monster);
        List<String> secondColumn = initSecondColumn(visibleMonsters, invisibleMonsters);

        //generate ceiling
        printCeilingAndFloor();

        for (int i = 0; i < height; i++) {
            printPath(i);   //print row ith of path
            System.out.print("     ");  //print gap between path and table
            System.out.format(tableFormat, firstColumn.get(i), secondColumn.get(i));  //print table
        }

        //generate floor
        printCeilingAndFloor();
    }

    /**
     * Assign the content (values) to the path 2D array. What will be printed on the map depends on the content of this 2D array.
     * @return assigned path.
     */
    public String[][] initPath() {
        String[][] path = DependencyFactory.getMapDriver().getMapPath(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == player.getPosition().getX() && i == player.getPosition().getY()) {
                    path[j][i] = "player";              //player position
                }
                else if (checkVisibleMonster(j, i)) {
                    path[j][i] = "visibleMonster";      //visible monster positions and alive
                }
                else
                    path[j][i] = ". ";                  //invisible monster positions and path
            }
        }
        return path;
    }

    /**
     * Check if the monster at a given position is visible and alive to assign corresponding content to that map location.
     * @param j the X coordinate (horizontal).
     * @param i the Y coordinate (vertical).
     * @return true if the monster is visible and alive. Otherwise false.
     */
    public boolean checkVisibleMonster(int j, int i) {
        for (Monster monster : monsters) {
            if (j == monster.getPosition().getX() && i == monster.getPosition().getY() && monster.getHealth() > 0 && monster.getIsVisible()) return true;
        }
        return false;
    }

    /**
     * Initialize content (value) of the left column in the status table.
     * @param monster the Monster object if encountered. Otherwise null.
     * @return the list of left column content.
     */
    public List<String> initFirstColumn(Monster monster) {
        List<String> firstColumn = new ArrayList<>();
        firstColumn.add(" Player:");
        firstColumn.add("     Name: " + player.getName());
        firstColumn.add("     Health: " + player.getHealth());
        firstColumn.add("     Level: " + player.getCharacterLevel());
        firstColumn.add("     Exp: " + player.getExp());
        firstColumn.add("     Health Potion: " + player.getHealthPotion());
        firstColumn.add("     Gold: " + player.getGold());
//        firstColumn.add("     Weapons: ");
//        for (int i = 0; i < player.getWeapons().size(); i++) {
//            firstColumn.add("         " + player.getWeapons().get(i).getName());
//        }
        firstColumn.add("");

        if (monster != null) {
            firstColumn.add(" Monster Status:");
            firstColumn.add("     Health: " + monster.getHealth());
            firstColumn.add("     Exp: " + monster.getExp());
        }

        int addingRow = height - firstColumn.size(); //set a fixed value for for-loop condition since size will be changed each iteration
        for (int i = 0; i < addingRow; i++) {
            firstColumn.add("");
        }
        return firstColumn;
    }

    /**
     * Initialize content (value) of the right column in the status table.
     * @param visibleMonsters the number of visible Monster alive in the map.
     * @param invisibleMonsters the number of invisible Monster alive in the map.
     * @return the list of right column content.
     */
    public List<String> initSecondColumn(int visibleMonsters, int invisibleMonsters) {
        List<String> secondColumn = new ArrayList<>();
        secondColumn.add(" Movements & Control:");
        secondColumn.add("     W: Up             C: Save");
        secondColumn.add("     A: Left           V: Load");
        secondColumn.add("     S: Down           B: Shop");
        secondColumn.add("     D: Right          Q: Quit");
        secondColumn.add("");
        secondColumn.add(" Options (in the fight):");
        secondColumn.add("     1: Strike");
        secondColumn.add("     2: Use health potion");
        secondColumn.add("     3: Run");
        secondColumn.add("");
        secondColumn.add(" Map Status:");
        secondColumn.add("     Visible monsters: " + String.format("%02d", visibleMonsters));
        secondColumn.add("     Invisible monsters: " + String.format("%02d", invisibleMonsters));

        int addingRow = height - secondColumn.size();
        for (int i = 0; i < addingRow; i++) {
            secondColumn.add("");
        }
        return secondColumn;
    }

    /**
     * Print the give row of the map.
     * @param index the row to be printed.
     */
    public void printPath(int index) {
        System.out.print("|");
        for (int j = 0; j < width; j++) {
            if (path[j][index].equals("player")) {
                System.out.print("" + player.getIcon() + space);
            }
            else if (path[j][index].equals("visibleMonster")) {
                System.out.print("" + monsters.get(0).getIcon() + space);   //all monsters have identical icons, just use the first monster's here
            }
            else {
                System.out.print(path[j][index]);
            }
        }
        System.out.print("|");
    }

    /**
     * Print the border top and bottom of the map and status table.
     */
    public void printCeilingAndFloor() {
        System.out.print("+");
        for (int w = 0; w < 2*width; w++) System.out.print("-");    //each unit of width has 2 characters ". "
        System.out.print("+     +");
        for (int w = 0; w < 50; w++) System.out.print("-");         //first column
        System.out.print("+");
        for (int w = 0; w < 50; w++) System.out.print("-");         //second column
        System.out.println("+");
    }
}