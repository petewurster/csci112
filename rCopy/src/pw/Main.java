/*
Main.java
2020-07-21 pWurster


This demonstrates a simple utility to recursively copy a directory and its contents
 */



package pw;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        //init File vars to be used in try-block
        File source = new File("");
        File copyRoot = new File("");

        //get user input
        try{
            source = new File(grabInput("Copy which directory?", "/home/pw2/Desktop/source"));
            copyRoot = new File(grabInput("Enter destination.", "/home/pw2/Desktop/copies"));
            copyRoot.mkdir();

            //start copying
            rCopy(source, copyRoot);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The target source does not exist!");
            System.exit(99);
        }
    }



    /**
     * recursively copy a directory and its contents
     * @param source    File object
     * @param destination    File object
     */
    private static void rCopy(File source, File destination) {
        //base case - no internal sources
        if (source.listFiles().length == 0) return;

        
        File[] sources = source.listFiles();
        File[] copies = new File[sources.length];

        //iterate through directory contents
        for (int f = 0; f < sources.length; f++) {
            try{
                //create new file object
                copies[f] = new File(destination.getAbsolutePath() + "/" + sources[f].getName());
                
                //process directories
                if (sources[f].isDirectory()) {
                    copies[f].mkdir();

                    //recursive call
                    rCopy(sources[f], copies[f]);

                //process internal sources
                } else {
                    //init buffers
                    BufferedInputStream bufferedSource = new BufferedInputStream(new FileInputStream(sources[f]), 8182);
                    BufferedOutputStream bufferedDestination  = new BufferedOutputStream(new FileOutputStream(copies[f]), 6192);

                    //conduct data transfer
                    int xfer;
                    while ((xfer = bufferedSource.read()) != -1) bufferedDestination.write(xfer);

                    // close file streams
                    bufferedSource.close();
                    bufferedDestination.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//end of directory loop

    }



    //grabs input with a JOptionPane
    //exit code 88 is to signify intentional kill of the program
    private static String grabInput(String message, String placeholder) {
        String raw = JOptionPane.showInputDialog(null, message, placeholder);
        if (raw == null) System.exit(88);
        return raw;
    }

}
