/**
 * Main.java
 * 2020-08-01 pWurster
 *
 *
 * This is the driver program that will be used to conduct research on the time
 * complexity of various sorting algorithms
 */

package pw;

import java.util.Random;
import java.util.Scanner;

public class Main {
    //init globals
    private static Scanner rawInput = new Scanner(System.in);
    private static String[] message = new String[10];


    //this makes running the final project samples require a little less typing
    private static final int SAMPLE_NUM = 5;

    public static void main(String[] args) {
        //start the menu
        menuLoop();
    }





    //creates an array of ints
    private static int[] createArrayOfInts(int length) {
        Random rnd = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = rnd.nextInt();
        return array;
    }







    //runs the chosen test with sampleSize length arrays
    private static void runTests(int choice, int sampleSize) {

        //capture input regarding number of samples
        int sample;
        try {
            message = new String[]{"How many times do you wanna run this test?"};
            sample = Integer.parseInt(grabInput(message));
        } catch (Exception e) {
            showMessage(new String[]{"*** Using default ***"});
            sample = SAMPLE_NUM;
        }

        //prompt user
        showMessage(new String[]{"", "Running " + sample + " sets of " +
                Sorts.TESTS[Math.abs(choice)], "on " + Sorts.SIZES[sampleSize] +
                " elements" + (choice < 0 ? " (reversed)" : "")});

        //perform the test once for each sample
        for (int i = 1; i < sample + 1; i++) {
            //this makes the results easier to read, but interferes with copy/paste efforts
//            System.out.printf("%3d : ", i);

            //instantiate a new array of random ints
            int[] testArray = createArrayOfInts(Sorts.SIZES[sampleSize]);

            //capture timestamp
            double time = System.nanoTime();

            //initiate chosen test routine
            switch (choice) {
                case 1:
                    Sorts.bubbleSort(testArray);
                    break;
                case -1:
                    Sorts.bubbleSort(testArray, true);
                    break;
                case 2:
                    Sorts.selectionSort(testArray);
                    break;
                case -2:
                    Sorts.selectionSort(testArray, true);
                    break;
                case 3:
                    Sorts.insertionSort(testArray);
                    break;
                case -3:
                    Sorts.insertionSort(testArray, true);
                    break;
                case 4:
                    Sorts.mergeSort(testArray);
                    break;
                case -4:
                    Sorts.mergeSort(testArray, true);
                    break;
                case 5:
                    Sorts.quickSort(testArray);
                    break;
                default: //case -5
                    Sorts.quickSort(testArray, true);

            }

            //calculate completion time in seconds
            time = (System.nanoTime() - time) / 1000000000.0;
            System.out.println(time);

            //stop further testing after time limit is exceeded for the first time
            if (time > Sorts.TIME_LIMIT) {
                showMessage(new String[]{"\n***     Tests aborted!     ***",
                        "*** Exceeded " + Sorts.TIME_LIMIT + " seconds ***\n"});
                break;
            }

        }//end for loop


    }





    //menu loop
    private static void menuLoop() {
        while (true) {
            try {
                message = getMenu();
                int choice = Integer.parseInt(grabInput(message));

                //quit if selection out of bounds
                if (choice < -5 || choice > 5 || choice == 0) exit();

                message = getSampleSize();
                int sampleSize = Integer.parseInt(grabInput(message));

                //quit if selection out of bounds
                if (sampleSize < 1 || sampleSize > 8) exit();

                //run selected test SAMPLE_NUM times on sampleSize elements
                runTests(choice, sampleSize);

            } catch (Exception e) {
                showMessage(new String[]{"*** Invalid entry ***"});
            }
        }
    }






    //main menu
    private static String[] getMenu() {
        return new String[]{
                "\n-------------------------",
                "  Test which algorithm?",
                "-------------------------",
                "[1] bubble sort     [-1]",
                "[2] selection sort  [-2]",
                "[3] insertion sort  [-3]",
                "[4] merge sort      [-4]",
                "[5] quick sort      [-5]",
                "[6] Quit",
                "\n( negative option reverses the sort )"
        };
    }






    //secondary menu
    private static String[] getSampleSize() {
        return new String[]{
                "\n-------------------------",
                "How many random integers?",
                "-------------------------",
                "[1] 10000",
                "[2] 20000",
                "[3] 100000",
                "[4] 200000",
                "[5] 1000000",
                "[6] 2000000",
                "[7] 10000000",
                "[8] 20000000",
                "[9] Quit"
        };
    }





    //grab user input for menu after prompting with message
    private static String grabInput(String[] message) {
        showMessage(message);
        return rawInput.nextLine();
    }





    //show message line by line
    private static void showMessage(String[] message) {
        for (String line: message) System.out.println(line);
    }




    //exit code 88 is to signify intentional kill of the program
    private static void exit() {
        rawInput.close();
        System.exit(88);
    }






}
