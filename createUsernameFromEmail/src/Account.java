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
    public static final String OUTFILE = "hashes.txt";


    private String userName;
    private String email;
    private String passHash;
    private Date createdOn;
    private boolean locked = false;

    Account() {
        this.email = acquireEmail();
        this.userName = stripNameFromEmail();
        prompt();
        this.createdOn = new Date();
        this.passHash = hashPassword(acquirePassword());
        printHashToFile();

    }

    private String grabInput(String message) {
        String raw = JOptionPane.showInputDialog(null,message);
        if (raw == null) System.exit(88);
        return raw;
    }

    private String stripNameFromEmail() {
        return this.getEmail().replaceAll("@.+[.]edu", "");
    }

    private boolean validEmail(String email) {
        String pattern = "[a-zA-Z0-9]+@[a-zA-Z0-9]+[.]edu";
        return email.matches(pattern);
    }

    private String acquireEmail() {
        String message;
        String email;
        do {
            message = "Please enter a valid \".edu\" account.\nOr select \"Cancel\" to quit.";
            email = grabInput(message);
        } while (!validEmail(email));
        return email;
    }

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

    public String toString() {
        return "user:" + this.getUserName() +
                "\nemail:" + this.getEmail() +
                "\ncreated:" + this.getCreatedOn().toString() +
                "\nlocked:" + this.isLocked()+
                "\nhash:" + this.getPassHash();
    }


    private void toggleLock() {
        this.locked = !this.locked;
    }

    public void updateIdentity(String newInfo) {
        this.email = validEmail(newInfo) ? newInfo : this.email;
        this.userName = stripNameFromEmail();
        prompt();
    }

    public void updatePassword(String newInfo) {
        this.passHash = hashPassword(newInfo);
        printHashToFile();

    }

    private void printHashToFile() {
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File(OUTFILE), true));
            outputStream.println(this.getUserName() + "  :  " + this.getPassHash());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }




    }



    private void prompt() {
        String message = "Welcome " + this.getUserName() + "!";
        if (this.getCreatedOn() != null) message = "Your information has been changed!";
        JOptionPane.showMessageDialog(null, message);
    }


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


}
