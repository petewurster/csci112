/**
 * Main.Java
 * 2020-07-28 pWurster
 *
 * This is a driver program to demonstrate implementation of iterative sorting
 * techniques applied to custom classes.  An internal class TutorialWebsite is
 * included to aid in the demonstration
 *
 */

package pw;


import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static final String SOURCE = "Tutorials.txt";
    public static final String OUT_1 = "ascendingSelection.csv";
    public static final String OUT_2 = "descendingInsertion.csv";

    public static void main(String[] args) {


        try {
            //populate unsorted list & display
            TutorialWebsite[] tutorials = createCustomObjectsFromTripletFile();
            printListToConsole(tutorials);

            //sort ascending via selection & display
            TutorialSorts.selectionSort(tutorials);
            printListToConsole(tutorials);
            printArrayDataToFile(tutorials, OUT_1);

            //sort descending via insertion & display
            TutorialSorts.insertionSort(tutorials, true);
            printListToConsole(tutorials);
            printArrayDataToFile(tutorials, OUT_2);

        } catch (Exception e) {
            System.out.println("*** Error handling file ***");
        }


    }




    private static void printListToConsole(TutorialWebsite[] tutorials) {
        for (TutorialWebsite t: tutorials) System.out.println(t);
        System.out.println();
    }




    private static void printArrayDataToFile(TutorialWebsite[] tutorials, String output) throws Exception {
        PrintWriter out = new PrintWriter(new File (output));

        //every line is a single entity
        for (TutorialWebsite t: tutorials) out.println(t.toString());

        out.close();
    }




    private static TutorialWebsite[] createCustomObjectsFromTripletFile() throws Exception {

        TutorialWebsite[] tutorials = new TutorialWebsite[0];

        //determine size of the array
        Scanner in = new Scanner(new File(SOURCE));
        int count = 0;
        while (in.hasNextLine()) {
            count++;
            in.nextLine();
        }

        //reset scanner
        in = new Scanner(new File(SOURCE));

        tutorials = new TutorialWebsite[count / 3];

        //iterate to fill empty array
        for (int i = 0; i < tutorials.length; i++) {

            //every 3 lines is one object
            String[] objProps = new String[3];
            for (int j = 0; j < 3; j++) objProps[j] = in.nextLine();

            tutorials[i] = new TutorialWebsite(objProps[0], objProps[1], objProps[2]);

        }
        return tutorials;
    }







 //////////////////////////////////////////////////////////////////////////////

    //inner class for use with only this assignment
    public static class TutorialWebsite implements Comparable<TutorialWebsite>{
        public String lang;
        public String desc;
        public String url;


        TutorialWebsite() {}

        TutorialWebsite(String lang, String desc, String url) {
            //trim is used to compensate for irregularities in the source file
            this.lang = lang.trim();
            this.desc = desc.trim();
            this.url = url.trim();
        }

        @Override
        public String toString() {
            return this.lang + ", " + this.url + ", " + this.desc;
        }

        @Override
        public int compareTo(TutorialWebsite that) {
            return this.lang.compareTo(that.lang);
        }
    }








}
