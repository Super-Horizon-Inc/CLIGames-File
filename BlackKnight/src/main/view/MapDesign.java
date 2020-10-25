package main.view;

import main.view_model.MapDriver;

public class MapDesign {
    MapDriver mapDriver = new MapDriver();

    public void initPath(int width, int height) {
        String[][] path = mapDriver.createPath(width, height);
        for (int w = 0; w < 2*width+2; w++) System.out.print('-');
        System.out.println("");
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                System.out.print(path[j][i]);
            }
            System.out.println("|");
        }
        for (int w = 0; w < 2*width+2; w++) System.out.print('-');
        System.out.println("");
    }

//    public void updatePath() {
//
//    }
}
