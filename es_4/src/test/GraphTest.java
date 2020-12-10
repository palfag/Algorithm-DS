package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GraphTest{
    public static void main(String[] args){

        System.out.println("\n\n DIRECT GRAPH TESTS:\n");

        Result result_direct = JUnitCore.runClasses(DirectGraphTest.class);
        int failed = 0;

        for (Failure fail : result_direct.getFailures()){
            System.out.println(fail.toString());
            failed ++;
        }

        System.out.println("\tTest result: " + result_direct.wasSuccessful() + "\n\t" + failed + " failed tests out of " + result_direct.getRunCount());
        System.out.println("\n DIRECT GRAPH TEST END");


        System.out.println("\n UNDIRECT GRAPH TESTS:\n");

        Result result_undirect = JUnitCore.runClasses(UndirectGraphTest.class);
        failed = 0;

        for (Failure fail : result_undirect.getFailures()){
            System.out.println(fail.toString());
            failed ++;
        }

        System.out.println("\tTest result: " + result_undirect.wasSuccessful() + "\n\t" + failed + " failed tests out of " + result_undirect.getRunCount());
        System.out.println("\n UNDIRECT GRAPH TEST END\n");
    }
}