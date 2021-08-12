package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.dao.FileDaoTest;

/**
 * The entry point of the game. Initialize the map size.
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FileDaoTest.class);
        //print out failed cases
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}