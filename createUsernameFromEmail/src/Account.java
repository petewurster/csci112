/**
Account.java
2020-07-08 pWurster

this is a class designed to (eventually) be a self contained account creation module
password hashes are currently written to file, but the intent is to eventually store these
values in a relational database along with the rest of the account info (email is intended to be the PK).
future members will include-> lastActivity, lastPWReset, Account(w/params), and a few other helper
functions for DB connectivity
*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class Account {

    //output file path
    public static final String OUTFILE = "hashes.txt";

    //basic members
    private String userName;
    private String email;
    private String passHash;
    private Date createdOn;
    private boolean locked = false;

    //default constructor
    Account() {
        this.email = acquireEmail();
        this.userName = stripNameFromEmail();
        prompt();
        this.createdOn = new Date();
        this.passHash = hashPassword(acquirePassword());
        printHashToFile();
    }



    //exit code 88 is to signify intentional kill of the program
    private String grabInput(String message) {
        String raw = JOptionPane.showInputDialog(null,message);
        if (raw == null) System.exit(88);
        return raw;
    }



    //strips user name from email address
    private String stripNameFromEmail() {
        return this.getEmail().replaceAll("@.+[.]edu", "");
    }



    //valid email test
    private boolean validEmail(String email) {
        String pattern = "[a-zA-Z0-9]+@[a-zA-Z0-9]+[.]edu";
        return email.matches(pattern);
    }



    //prompts user for email address
    private String acquireEmail() {
        String message;
        String email;
        do {
            message = "Please enter a valid \".edu\" account.\nOr select \"Cancel\" to quit.";
            email = grabInput(message);
        } while (!validEmail(email));
        return email;
    }



    //prompts user for password & confirmation
    private String acquirePassword() {
        String password;
        String confirm;
        String message;
        do {
            message = "Please choose a password.";
            password = grabInput(message);
            message = "Please confirm your password.";
            confirm = grabInput(message);
        } while (!password.matches(confirm));
        return password;
    }


    /**
     *applies a 128-bit SecureRandom salt, peppers the password with the toString() value of
     * the createdOn Date, and then hashes with SHA-512
     * @param password String
     * @return _inlineConvertedValue_ this value is a String of the Hex representation of the hash's resulting byte array
     */
    private String hashPassword(String password) {
        try{
            //generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            //put salt into my hash
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);

            //pepper by including additional string into password
            byte[] hash = md.digest((password + getCreatedOn().toString()).getBytes(StandardCharsets.UTF_8));

            prompt();
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    //override
    public String toString() {
        return "user:" + this.getUserName() +
                "\nemail:" + this.getEmail() +
                "\ncreated:" + this.getCreatedOn().toString() +
                "\nlocked:" + this.isLocked()+
                "\nhash:" + this.getPassHash();
    }



    //toggle locked status of Account
    public void toggleLock() {
        this.locked = !this.locked;
    }




    //update email & (subsequently) userName; ignores updates that are not valid
    public void updateIdentity(String newInfo) {
        this.email = validEmail(newInfo) ? newInfo : this.email;
        this.userName = stripNameFromEmail();
        prompt();
    }



    //updates hashed password
    public void updatePassword(String newInfo) {
        this.passHash = hashPassword(newInfo);
        printHashToFile();
    }




    //appends userName & hashed password to file as key:value pair
    private void printHashToFile() {
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File(OUTFILE), true));
            outputStream.println(this.getUserName() + "  :  " + this.getPassHash());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //helper func to inform user of successful changes
    private void prompt() {
        String message = "Welcome " + this.getUserName() + "!";
        if (this.getCreatedOn() != null) message = "Your information has been changed!";
        JOptionPane.showMessageDialog(null, message);
    }




    //mandatory getters due to all member properties being private
    public String getUserName() {
        return this.userName;
    }
    public String getEmail() {
        return this.email;
    }
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public boolean isLocked() {
        return this.locked;
    }
    public String getPassHash() {
        return this.passHash;
    }


}//end of class
