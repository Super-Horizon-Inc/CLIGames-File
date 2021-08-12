package main.view;

import main.view_model.Service;

/**
 * The entry point to run test.
 */
public class Main {
    public static void main (String[] args) {
        Service service = new Service(30, 15);
        service.startService();
    }
}