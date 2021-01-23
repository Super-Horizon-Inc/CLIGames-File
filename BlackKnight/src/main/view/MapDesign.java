package main.view;

import main.models.MainCharacter;
import main.models.Weapon;
import main.view_model.DependencyFactory;

import java.util.ArrayList;
import java.util.List;

public class MapDesign {

    private int width;
    private int height;
    private int hyphen;
    String[][] path;

    public MapDesign(int width, int height, int hyphen) {
        this.width = width;
        this.height = height;
        this.hyphen = hyphen;
        this.path = this.initPath();
    }

    public void createUI() {

        List<String> firstColumns = new ArrayList<>();
        firstColumns.add("Name: ");
        firstColumns.add("Health: ");
        firstColumns.add("Level: ");
        firstColumns.add("Weapons: ");
        firstColumns.add("Image: ");
        String[] secondColumns = {"Press 'W' to Go Up", "Press 'A' to Go Left", "Press 'S' to Go Down", "Press 'D' to Go Right"};

        int index = firstColumns.indexOf("Weapons: ");
        int numberOfWeapons = DependencyFactory.getCharacterDriver().mainCharacter.getWeapons().size();
        for (int i = 1; i < numberOfWeapons+1; i++) {
            firstColumns.add(index + i, " ");
        }


        for (int i = 0; i < height; i++) {

            if (i == 0) {
                //generate ceiling
                printCeilingAndFloor();
            }
            else if (i == 1) {
                printPath(i);

                System.out.print(" Player Status:");
                for (int w = 0; w < 36; w++) System.out.print(' ');
                System.out.print("| Hot Keys:");
                for (int w = 0; w < 38; w++) System.out.print(' ');
                System.out.println("|");
            }

            else {
                printPath(i);
                if (i-2 < firstColumns.size() && i-2 < secondColumns.length)
                    generateRow(firstColumns.get(i-2), secondColumns[i-2], i);
                else if (i-2 < firstColumns.size())
                    generateRow(firstColumns.get(i-2), "", i);
                else if (i-2 < secondColumns.length)
                    generateRow("", secondColumns[i-2], i);
                else generateRow("", "", i);
            }
        }

        //generate floor
        printCeilingAndFloor();

    }

    public String[][] initPath() {
        String[][] path = DependencyFactory.getMapDriver().getMapPath(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                path[j][i] = ". ";
            }
        }
        return path;
    }

    public void generateRow(String text1, String text2, int i) {
        int addingLength = 0;
        switch (text1)
        {
            case "Name: ": {
                System.out.print("     " + text1 + DependencyFactory.getCharacterDriver().mainCharacter.getName());
                addingLength = DependencyFactory.getCharacterDriver().mainCharacter.getName().length();
                break;
            }
            case "Health: ": {
                System.out.print("     " + text1 + DependencyFactory.getCharacterDriver().mainCharacter.getHealth());
                addingLength = String.valueOf(DependencyFactory.getCharacterDriver().mainCharacter.getHealth()).length();
                break;
            }
            case "Level: ": {
                System.out.print("     " + text1 + DependencyFactory.getCharacterDriver().mainCharacter.getCharacterLevel());
                addingLength = String.valueOf(DependencyFactory.getCharacterDriver().mainCharacter.getCharacterLevel()).length();
                break;
            }
            case "Weapons: ": {
                System.out.print("     " + text1);
                break;
            }
            case "Image: ": {
                System.out.print("     " + text1 + DependencyFactory.getCharacterDriver().mainCharacter.getImage());
                addingLength = String.valueOf(DependencyFactory.getCharacterDriver().mainCharacter.getImage()).length();
                break;
            }
            case " ": {
                System.out.print("          " + DependencyFactory.getCharacterDriver().mainCharacter.getWeapons().get(i-6).getName());
                addingLength = 4 + DependencyFactory.getCharacterDriver().mainCharacter.getWeapons().get(i-6).getName().length();
                break;
            }
            default:
                System.out.print("     ");
                break;
        }

        for (int w = 0; w < 46 - text1.length() - addingLength; w++) System.out.print(' ');
        System.out.print("|     " + text2);
        for (int w = 0; w < 43 - text2.length(); w++) System.out.print(' ');
        System.out.println("|");
    }

    public void printPath(int index) {
        System.out.print("|");
        for (int j = 0; j < width; j++) {
            System.out.print(path[j][index]);
        }
        System.out.print("|     |");
    }

    public void printCeilingAndFloor() {
        for (int w = 0; w < hyphen; w++) System.out.print('-');
        System.out.print("     ");
        for (int w = 0; w < 102; w++) System.out.print('-');
        System.out.println("");
    }

}