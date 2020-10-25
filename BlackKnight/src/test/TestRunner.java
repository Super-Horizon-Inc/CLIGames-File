package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.dao.FileDaoTest;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FileDaoTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}