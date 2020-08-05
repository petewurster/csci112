/**
 * RemoteControl.java
 * 2020-08-05 pWurster
 *
 * This program modifies its predecessor to provide event listening capability
 * to the RemoteControl GUI.  Event information is displayed in the console.
 */



package pw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RemoteControl extends JFrame implements ActionListener, ItemListener {

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

    private boolean menuToggle = false;



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




    //build panels and apply appropriate listeners
    private void buildPanels() {

        //populate top panel by looping thru top button list
        ButtonGroup sources = new ButtonGroup();
        for (String source: TOP_BUTTONS) {

            //add button
            if (source.equals(TOP_BUTTONS[0])) {
                JButton item = new JButton(TOP_BUTTONS[0]);
                this.topPanel.add(item);
                item.addActionListener(this);

            //or radio button
            } else {
                JRadioButton radio = new JRadioButton(source);
                sources.add(radio);
                this.topPanel.add(radio);
                radio.addItemListener(this);
            }
        }



        //populate main panel by looping thru main panel button list
        this.mainPanel.setLayout(new GridLayout(6, 3));
        for (JButton button: populateButtons()) {

            //add checkbox
            if (button.getText().equals(MAIN_BUTTONS[9])) {
                JCheckBox check = new JCheckBox(MAIN_BUTTONS[9]);
                this.mainPanel.add(check);
                check.addItemListener(this);

            //or button
            } else {
                this.mainPanel.add(button);
            }
        }



        //populate bottom panel by looping thru bottom panel button list
        for (String button: BOTTOM_BUTTONS) {
            JButton item = new JButton(button);
            this.bottomPanel.add(item);
            item.addActionListener(this);
        }



    }


    //helper function used by the main panel
    private JButton[] populateButtons() {
        //create an array of button objects
        JButton[] buttons = new JButton[MAIN_BUTTONS.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(MAIN_BUTTONS[i]);
            buttons[i].addActionListener(this);
        }
        return buttons;
    }






    @Override
    //for buttons
    public void actionPerformed(ActionEvent e) {

        //isolate which items have been activated based on label text
        String active = e.getActionCommand();

        //test if dual-function button action is required
        if (active.equals(MAIN_BUTTONS[1]) || active.equals(MAIN_BUTTONS[3]) || active.equals(MAIN_BUTTONS[4]) ||
                active.equals(MAIN_BUTTONS[5]) || active.equals(MAIN_BUTTONS[7])) {
            System.out.println("you have pressed a button: " + (this.menuToggle ? active.split(" ")[1] : active.split(" ")[0]));


        //exit program if PWR button is pressed; immune from menuToggle deactivation
        } else if (active.equals(TOP_BUTTONS[0])) {
            System.out.println("powering off...");
            System.exit(88);


        //volume controls are also immune from menuToggle deactivation
        } else if (active.equals(BOTTOM_BUTTONS[0]) || active.equals(BOTTOM_BUTTONS[1])) {
            System.out.println("volume control: " + (active.equals(BOTTOM_BUTTONS[0]) ? "DOWN" : "UP"));



        //handle all other single-function button features
        } else {
            System.out.println(this.menuToggle ? "the " + active + " button is currently disabled"
                    : "you have pressed a button: " + active);
        }
    }







    @Override
    //for checks/radios
    public void itemStateChanged(ItemEvent e) {

        //isolate which items have been activated based on label text
        String active = e.getSource().toString().split("text=")[1];
        active = active.substring(0, active.length() - 1);


        //update status of menuToggle property
        if (active.equals(MAIN_BUTTONS[9])) {
            this.menuToggle = !this.menuToggle;
                System.out.println("menu toggled: " + (e.getStateChange() == ItemEvent.SELECTED ? "ON" : "OFF"));


        //handle radio button features
        }else {
            if (e.getStateChange() == ItemEvent.SELECTED) System.out.println("input source changed to: " + active);
        }
    }




}
