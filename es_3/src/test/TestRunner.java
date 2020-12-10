package test;
import code.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner{
    public static void main(String[] args){

        System.out.println("\n\n**********UnionFindSet**********\n");

        Result result_direct = JUnitCore.runClasses(TestUnionFindSet.class);
        int failed = 0;

        for (Failure fail : result_direct.getFailures()){
            System.out.println(fail.toString());
            failed ++;
        }

        System.out.println("\tTest result: " + result_direct.wasSuccessful() + "\n\t" + failed + " failed tests out of " + result_direct.getRunCount());
        System.out.println("\n********************************");
    }
}