package main.view;

import main.models.Character;
import main.models.MainCharacter;
import main.models.Position;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MapDesign mapDesign = new MapDesign();
        mapDesign.initPath(20,10);

        //TODO need to manipulate path to get character position.

        Character character = new MainCharacter();
        Position position = new Position(2,5);
        character.setPosition(position);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter something: ");
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("w")) {
//            Position charPosition = character.getPosition();
            if (position.getY() > 0) {
                position.setY(position.getY()-1);
                character.setPosition(position);
            }


        }
    }

}