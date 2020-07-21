/**
Main.java
2020-07-20 pWurster

This program demonstrates use of recursion via a palindrome test, first the program runs a bunch of
samples, then the user can test for themselves
 */

package pw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static final String TESTDATA = "wordlist.txt";

    public static void main(String[] args) {
        autoTest();

        //even if automated tests fail, user can still run tests
        System.out.println("\nPalindrome Test\n---------------");
        System.out.println("Type a word or phrase to test if it's a palindrome");

        String sample = "";
        Scanner raw = new Scanner(System.in);

        while (!sample.equals("exit")) {
                sample = raw.nextLine();
                runTest(sample);
        }
    }




    /**
     *prep phrase for palindrome testing
     */
    private static String prepPhrase(String phrase) {
        return phrase.replaceAll("[.,?! :'\\/]", "").toLowerCase();
    }




    /**
     * recursive test for palindromes
     */
    private static boolean isPalindrome(String sample) {
        //base case: single letter words are automatically palindromes
        if (sample.length() < 2) return true;

        //if outside ends don't match, its not a palindrome
        if (sample.charAt(0) != sample.charAt(sample.length() - 1)) return false;

        //self-invoke with substring that excludes the ends
        return isPalindrome(sample.substring(1, sample.length() - 1));
    }




    /**
     *display test results
     */
    private static void runTest(String sample) {
        System.out.println(sample + " : " + (isPalindrome(prepPhrase(sample))? "OK!" : "nope"));
    }



    /**
    * automatic testing
    */
    private static void autoTest() {
        System.out.println("Palindrome Test\n---------------");

        try (Scanner data = new Scanner(new File(TESTDATA))) {

            //since file length is unknown, iterate it and collect data into
            //a long delimited string
            String collector = "";
            while (data.hasNextLine()) collector += data.nextLine() + ":::";

            //split the string
            String[] tests = collector.split(":::");

            //run the tests
            for (String sample: tests) runTest(sample);

        } catch (FileNotFoundException e) {
            System.out.println("Error reading from \"" + TESTDATA + "\"\n" +
                    "Ensure that each sample is on its own line");
        }
    }



}
