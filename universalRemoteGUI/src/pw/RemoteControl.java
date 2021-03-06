/**
 * RemoteControl.java
 * 2020-08-04 pWurster
 *
 * This program builds out a GUI for a RemoteControl interface (like that used
 * with a television set)
 */

package pw;

import javax.swing.*;
import java.awt.*;

public class RemoteControl extends JFrame {

    private static final String[] TOP_BUTTONS = new String[]{
            "PWR",      "a", "b", "c"
    };
    private static final String[] MAIN_BUTTONS = new String[]{
            "1",        "2 (U)",    "3",
            "4 (L)",    "5 (OK)",   "6 (R)",
            "7",        "8 (D)",    "9",
            "#/menu",   "0",        "OK",
            "<<<",      "PLAY",     ">>>",
            "REC",      "STOP",     "PAUSE"
    };
    private static final String[] BOTTOM_BUTTONS = new String[]{
            "-",       "+"
    };

    private JPanel container = new JPanel();

    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();




    RemoteControl(){
        super("Remote Control Demo");
        this.setSize(247, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //populate panels/frame
        buildPanels();
        this.container.setLayout(new BorderLayout());
        this.container.add(topPanel, BorderLayout.NORTH);
        this.container.add(mainPanel,BorderLayout.CENTER);
        this.container.add(bottomPanel, BorderLayout.SOUTH);
        this.add(container);

        this.setVisible(true);
    }




    //build panels
    private void buildPanels() {

        //populate top panel by looping thru top button list
        ButtonGroup sources = new ButtonGroup();
        for (String source: TOP_BUTTONS) {

            //add button
            if (source.equals(TOP_BUTTONS[0])) {
                JButton item = new JButton(TOP_BUTTONS[0]);
                this.topPanel.add(item);

            //or radio button
            } else {
                JRadioButton radio = new JRadioButton(source);
                sources.add(radio);
                this.topPanel.add(radio);
            }
        }



        //populate main panel by looping thru main panel button list
        this.mainPanel.setLayout(new GridLayout(6, 3));
        for (JButton button: populateButtons()) {

            //add checkbox
            if (button.getText().equals(MAIN_BUTTONS[9])) {
                JCheckBox check = new JCheckBox(MAIN_BUTTONS[9]);
                this.mainPanel.add(check);

            //or button
            } else {
                this.mainPanel.add(button);
            }
        }



        //populate bottom panel by looping thru bottom panel button list
        for (String button: BOTTOM_BUTTONS) {
            JButton item = new JButton(button);
            this.bottomPanel.add(item);
        }



    }


    //helper function used by the main panel
    private JButton[] populateButtons() {
        //create an array of button objects
        JButton[] buttons = new JButton[MAIN_BUTTONS.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(MAIN_BUTTONS[i]);
        }
        return buttons;
    }




}
