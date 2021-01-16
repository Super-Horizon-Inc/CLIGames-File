package main.view;

import main.view_model.DependencyFactory;

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
        String[] firstColumns = {"Name:", "Health:", "Level:", "Weapons:", "Image:"};
        String[] secondColumns = {"Press 'W' to Go Up", "Press 'A' to Go Left", "Press 'S' to Go Down", "Press 'D' to Go Right"};

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
                if (i-2 < firstColumns.length && i-2 < secondColumns.length)
                    generateRow(firstColumns[i-2], secondColumns[i-2]);
                else if (i-2 < firstColumns.length)
                    generateRow(firstColumns[i-2], "");
                else if (i-2 < secondColumns.length)
                    generateRow("", secondColumns[i-2]);
                else generateRow("", "");
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

    public void generateRow(String text1, String text2) {
        System.out.print("     " + text1);
        for (int w = 0; w < 46 - text1.length(); w++) System.out.print(' ');
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