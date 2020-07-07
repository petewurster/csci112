/*
Main.java
2020-07-07 pWurster

demonstrates usage of the RandomInts Class:
creates an object that holds 1000 ints and some information about those ints
 */


import java.io.File;
import java.io.PrintWriter;


/**
 *
 */
public class Main {
    //path to output file as a constant
    public static final String OUTFILE = "results.txt";



    public static void main(String[] args) {
        //instantiate a RandomInts object
        RandomInts myInts = new RandomInts(1000, 1, 10);

        //print some data to the console
        System.out.println("   raw " + allValues(myInts.getArray()));
        System.out.println("sorted " + allValues(myInts.sorted()));

        //print the summary to file
        printToFile(myInts);
    }




    public static void printToFile(RandomInts myInts) {
        try{
            //set up file handling
            java.io.File file = new java.io.File(OUTFILE);
            java.io.PrintWriter outputStream = new java.io.PrintWriter(file);

            //print summary
            outputStream.println(myInts.toString());
            outputStream.println();

            //print tallys
            int[] tally = myInts.getTally();
            for (int i = 0; i < tally.length; i++) {
                outputStream.printf("%2d: %d", (i + 1), tally[i]);
                outputStream.println();
            }
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String allValues(int[] array) {
        String str = "--> ";
        for (int value: array) {
            str += value + ", ";
        }
        return str;
    }

}
