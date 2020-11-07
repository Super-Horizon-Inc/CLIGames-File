package main.view;

import main.view_model.DependencyFactory;

public class MapDesign {

    private int width;
    private int height;

    public MapDesign(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void createUI() {
        String[][] path = this.initPath();
        for (int w = 0; w < 2*width+2; w++) System.out.print('-');
        System.out.print("     ");
        for (int w = 0; w < 102; w++) System.out.print('-');
        System.out.println("");
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                System.out.print(path[j][i]);
            }

            System.out.print("|     |");
            if (i == 0) {
                System.out.print(" Player Status:");
                for (int w = 0; w < 35; w++) System.out.print(' ');
                System.out.print("| Hot Keys:");
                for (int w = 0; w < 39; w++) System.out.print(' ');
            }
            else {
                for (int w = 0; w < 100; w++) {
                    if (w == 50) System.out.print("|");
                    else System.out.print(' ');
                }
            }
            System.out.println("|");
        }
        for (int w = 0; w < 2*width+2; w++) System.out.print('-');
        System.out.print("     ");
        for (int w = 0; w < 102; w++) System.out.print('-');
        System.out.println("");
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
}