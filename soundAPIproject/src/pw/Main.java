/**
 * Main.java
 * 2020-07-23 pWurster
 *
 * This program demonstrates incorporation of external Voice/Sound APIs
 * into Java programs.  The .jar file for the JFugue API is included in
 * the "lib" folder of this project for ease of demonstration.
 */

package pw;

import org.jfugue.player.Player;

public class Main {

    public static void main(String[] args) {

        //instantiate a Player
        Player player = new Player();

        //play notes corresponding to the string (Mary had a little lamb)
        player.play("E D C D E E E R D D D R E E E R E D C D E E E E D D E D C");

    }
}
