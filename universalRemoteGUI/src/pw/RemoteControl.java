package pw;

import javax.swing.*;
import java.awt.*;

public class RemoteControl extends JFrame {

    private static final String[] TOP_BUTTONS = new String[]{
            "PWR",      "a", "b", "c"
    };
    private static final String[] MAIN_BUTTONS = new String[]{
            "1",        "2 (U)",    "3",
            "4 (L)",    "5",        "6 (R)",
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
        buildPanels();
        this.container.setLayout(new BorderLayout());

        this.container.add(topPanel, BorderLayout.NORTH);
        this.container.add(mainPanel,BorderLayout.CENTER);
        this.container.add(bottomPanel, BorderLayout.SOUTH);

        this.add(container);


        this.setVisible(true);



    }

    private void buildPanels() {
        //populate top panel
        ButtonGroup sources = new ButtonGroup();
        for (String source: TOP_BUTTONS) {
            if (source.equals("PWR")) {
                this.topPanel.add(new JButton("PWR"));
            } else {
                JRadioButton radio = new JRadioButton(source);
                sources.add(radio);
                this.topPanel.add(radio);
            }
        }



        //populate main panel
        this.mainPanel.setLayout(new GridLayout(6, 3));
        for (JButton button: populateButtons()) {
            if (button.getText().equals("#/menu")) {
                this.mainPanel.add(new JCheckBox("#/menu"));
            } else {
                this.mainPanel.add(button);
            }
        }



        //populate bottom panel
        for (String button: BOTTOM_BUTTONS) this.bottomPanel.add(new JButton(button));



    }

    private JButton[] populateButtons() {
        JButton[] buttons = new JButton[MAIN_BUTTONS.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(MAIN_BUTTONS[i]);
        }
        return buttons;
    }





}
