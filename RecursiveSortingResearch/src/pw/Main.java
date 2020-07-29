package pw;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner rawInput = new Scanner(System.in);
    private static String[] message = new String[10];
    private static final int SAMPLE_NUM = 5;

    public static void main(String[] args) {
        //initiate test routines
        menuLoop();
    }




    private static int[] createArrayOfInts(int length) {
        Random rnd = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = rnd.nextInt();
        return array;
    }



    private static void runTests(int choice, int sample, int sampleSize) {
//        for (int s = 1; s < Sorts.SIZES.length; s++) {

            showMessage(new String[]{"", "Running 5 sets of " + Sorts.TESTS[choice], "on " + Sorts.SIZES[sampleSize] + " elements"});

            for (int i = 1; i < sample + 1; i++) {
                System.out.print(i + " : ");
                int[] testArray = createArrayOfInts(Sorts.SIZES[sampleSize]);

                //capture timestamp
                double time = System.nanoTime();

                switch (choice) {
                    case 1:
                        Sorts.bubbleSort(testArray);
                        break;
                    case 2:
                        Sorts.selectionSort(testArray);
                        break;
                    case 3:
                        Sorts.insertionSort(testArray);
                        break;
                    case 4:
                        Sorts.mergeSort(testArray);
                        break;
                    default:
                        Sorts.quickSort(testArray);

                }

                //calculate completion time
                time = (System.nanoTime() - time) / 1000000000.0;
                System.out.println(time);

                if (time > 300.0) {
                    showMessage(new String[]{"", "*** Tests aborted! ***", "*** Exceeded 5 min threshold ***", ""});
                    break;
                }

            }





//        }





    }



    //menu loop
    private static void menuLoop() {
        while (true) {
            try {
                message = getMenu();
                int choice = Integer.parseInt(grabInput(message));

                //quit if selection out of bounds
                if (choice < 1 || choice > 5) exit();

                message = getSampleSize();
                int sampleSize = Integer.parseInt(grabInput(message));

                //quit if selection out of bounds
                if (sampleSize < 1 || sampleSize > 8) exit();

                //run selected test SAMPLE_NUM times on sampleSize elements
                runTests(choice, SAMPLE_NUM, sampleSize);

            } catch (Exception e) {
                showMessage(new String[]{"*** Invalid entry ***"});
            }
        }
    }



    private static String[] getMenu() {
        return new String[]{
                "\n-------------------------",
                "  Test which algorithm?",
                "-------------------------",
                "[1] bubble sort",
                "[2] selection sort",
                "[3] insertion sort",
                "[4] merge sort",
                "[5] quick sort",
                "[6] Quit"
        };
    }

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
