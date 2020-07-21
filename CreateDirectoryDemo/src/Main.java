/* CreateDirectoriesDemo.java
 * original:
 * 2/23/2014 by C. Herbert
 *
 * The software in this example creates a set of folders (directories).
 * It is intended as an example for CSCI 112.
 *
 * The directory which will contain the new set of directories is identified in
 * line 23 of this code as  "c:\", the root directory on most Microsoft Windows
 * systems.  This can be changed to place the new set of directories elsewhere.
 *---------------------------------------------------------------------------------*
 * last revised:
 * 2020-07-21 pWurster
 *
 * Uses a DEFAULT_ROOT for a linux-system rather than Windows
 * Removed hard-coded folder values; added features permitting user interaction via JOptionPanes
 */
import javax.swing.*;
import java.io.*;

public class Main {
    //Establish the location of the parent for the new set of directories.
    public static final String DEFAULT_ROOT = "/home/pw2/Desktop/";

    public static void main(String[] args) {
        String message = "Where do you want to create your new directories?";
        //uses JOptionPane
        String location = grabInput(message, DEFAULT_ROOT);

        //build out a string because number of directories is unknown
        String data = "";
        while (true) {
            //ensure valid length before testing for "finished"
            if (data.length() > 8) {
                if (data.substring(data.length() - 9).equals("finished\n")) break;
            }
            //message includes data to show what has already been entered
            message = "Create your directories, parent directories must\n" +
                    "be created before any child directories - ex:\naaa\naaa/bbb/\naaa/bbb/ccc\n\n" +
                    "Type \"finished\" when you're done\n\n" + data;
            data += grabInput(message, "") + "\n";
        }

        //split string into array
        String[] folderPaths = data.split("\n");

        //create a File array for directories to be created (length - 1 removes "finished" token)
        File[] newFolders = new File[folderPaths.length - 1];

        // create new directories based root location and the file names in the array
        for (int i = 0 ; i < newFolders.length; i++) {
            // create a File object for this new directory
            newFolders[i]  = new File( location + folderPaths[i] ) ;

            // make the new directory
            newFolders[i].mkdir();
        }
    } // end main()




    //exit code 88 is to signify intentional kill of the program
    private static String grabInput(String message, String placeholder) {
        String raw = JOptionPane.showInputDialog(null, message, placeholder);
        if (raw == null) System.exit(88);
        return raw;
    }







}
