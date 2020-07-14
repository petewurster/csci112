/*
Main.java
2020-07-14 by pWurster
this program demonstrates exception handling techniques by using multiple
catch blocks with a single try statement, also demonstrating the exception
hierarchy by throwing exceptions back up the stack trace for handling

 */
package pw;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String message = "please enter an integer to reference our 100 integer array";
        System.out.println(message);

        //loop until no more exceptions arise from user input
        while (true) {
            try {
                message = "no exceptions detected";


                /* assignment requirements 3(1, 2):
                 1- call methods to create an array,
                 2- prompt user for index
                 3- print results
                 */
                printUserSelectedElement(createArrayOfInts(), promptUserForIndex());


                //no exceptions, break loop
                System.out.println(message);
                break;



            //assignment req 4: handle exceptions inside the main method
            } catch (InputMismatchException e) {
                message = "failed due to input mismatch, try again or type 'exit' to quit";


            } catch (ArrayIndexOutOfBoundsException e) {
                message = "enter a value between 0 and 99, try again or type 'exit' to quit";
            }


            //inform user of exception status
            System.out.println(message);
        }//end loop




    }



    //creates array of 100 random integers
    public static int[] createArrayOfInts() {
        Random rnd = new Random();
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) array[i] = rnd.nextInt();
        return array;
    }



    //gets user input
    public static int promptUserForIndex() throws InputMismatchException {
        Scanner raw = new Scanner(System.in);
        //fail-fast and quit program if user typed "exit"
        if (raw.hasNext("exit")) System.exit(88);
        return raw.nextInt();
    }



    //prints value of user selected array element
    public static void printUserSelectedElement(int[] array, int index) throws ArrayIndexOutOfBoundsException {
        System.out.println("the value at index "+ index + " is " + array[index]);
    }
}
