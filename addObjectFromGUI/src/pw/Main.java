/**
Main.java
2020-08-06 pWurster

 This program uses the CellPhone and Product classes from earlier in the semester.
 Main provides a GUI and a driver to append new CellPhone objects to a .csv file

 */
package pw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Main {

    //globals
    private static JTextField[] inputs = new JTextField[6];
    private static JFrame window = new JFrame("CellPhone Creation GUI");
    private static final String OUTFILE = "phoneData.csv";


    public static void main(String[] args) {
        //prep window
        window.setSize(300, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
        window.add(panel);


        //prep panel labels
        String[] labels = new String[]{
                " product number",
                " product name",
                " purchase price",
                " MAC address",
                " screen size",
                " model"
        };


        //build & insert panel elements
        for (int i = 0; i < labels.length; i++) {
            inputs[i] = new JTextField(20);
            panel.add(inputs[i]);
            panel.add(new JLabel(labels[i]));
        }

        //internal class handles encapsulates its own event
        class AddButton extends JButton implements ActionListener {
            AddButton() {
                super("ADD");
                this.addActionListener(this);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //adds data
                submitForm();
            }
        }


        //internal class handles encapsulates its own event
        class ClearButton extends JButton implements ActionListener {
            ClearButton() {
                super("CLEAR");
                this.addActionListener(this);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //clears data
                clearForm();
            }
        }


        //insert event buttons
        panel.add(new ClearButton());
        panel.add(new AddButton());

        //show window
        window.setVisible(true);
    }


    //clears form data
    private static void clearForm() {
        for (JTextField field: inputs) {
            field.setText("");
        }
    }

    //appends data to .csv file
    private static void submitForm() {
        try {
            //create new CellPhone
            CellPhone newPhone = new CellPhone(inputs[0].getText(),
                    inputs[1].getText(),
                    Double.parseDouble(inputs[2].getText()),
                    inputs[3].getText(),
                    Double.parseDouble(inputs[4].getText()),
                    inputs[5].getText());

            //append new CellPhone to .csv
            addPhoneToCSV(newPhone);

            //clear form for next entry
            clearForm();

        } catch (Exception e) {
            //ignore bad data
            JOptionPane.showMessageDialog(window,"All fields are mandatory!\n" +
                            "\n  \"purchase price\" and" +
                            "\n  \"screen size\" must be" +
                            "\n  numeric!  Try again.");
        }
    }


    //append data to file
    private static void addPhoneToCSV(CellPhone phone) {
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File(OUTFILE), true));
            outputStream.println(phone.toString()
                    .replaceAll(" :{1,3} ", ", ")   //regex fixes delimiters from
                    .replaceAll("(cm)|\\$", ""));   //toString format to csv format);
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
