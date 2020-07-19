/*
ArrayControllerMenu.java
2020-07-18 pWurster
 */
package pw;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArrayControllerMenu {
    public static final String FILE = "phoneData.csv";
    
    private ArrayController phoneList = new ArrayController();
    private String[] message = new String[10];
    private Scanner rawInput = new Scanner(System.in);
    
    private final String[] menu = new String[]{
            "\n-------------------------",
            "Menu for Cell Phone array",
            "-------------------------",
            "[1] Show all items",
            "[2] Append data from file",
            "[3] Add an item",
            "[4] Search by model OR index",
            "[5] Delete an item by model OR index",
            "[6] Save data to file",
            "[7] Quit"};

    ArrayControllerMenu() {
        //use default values
    }

    public void loop() {
        //menu loop
        while (true) {
            try {
                message = getMenu();
                int choice = Integer.parseInt(grabInput(message));

                //handle selections
                switch (choice) {
                    case 1:
                        listAllItems();
                        break;
                    case 2:
                        loadArrayControllerFromFile();
                        break;
                    case 3:
                        addInstanceToArray();
                        break;
                    case 4:
                        searchArrayForInstance();
                        break;
                    case 5:
                        removeInstanceFromArray();
                        break;
                    case 6:
                        saveArrayDataToFile();
                        break;
                    default: exit();
                }
            } catch (Exception e) {
                showMessage(new String[]{"*** Invalid entry ***"});
            }
        }
    }






    //show menu
    public String[] getMenu() {
        return this.menu;
    }





    //grab user input for menu after prompting with message
    private String grabInput(String[] message) {
        showMessage(message);
        return rawInput.nextLine();
    }





    //show message line by line
    private void showMessage(String[] message) {
        for (String line: message) System.out.println(line);
    }




    //load object array from file
    private void loadArrayControllerFromFile() {
        try (Scanner in = new Scanner(new File(FILE))) {
            //iterate through file
            while (in.hasNextLine()) {

                //split each line into an array
                String[] data = in.nextLine().split(", ");

                //properties used to create new cellPhone and added to controller
                phoneList.addCellPhone(new CellPhone(data[0], data[2], Double.parseDouble(data[1]),
                        data[3], Double.parseDouble(data[5]), data[4]));
            }
        } catch (Exception e) {
            showMessage(new String[]{"*** Error reading from file \"phoneData.csv\" ***"});
        }
    }




    //save array data to file
    private void saveArrayDataToFile() {
        try (PrintWriter out = new PrintWriter(new File(FILE))) {

            //loop through array and print each line to csv file
            for (CellPhone item: phoneList.array()) out.println(item.toString()
                    .replaceAll(" :{1,3} ", ", ")   //regex fixes delimiters from
                    .replaceAll("(cm)|\\$", ""));   //toString format to csv format
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }



    //add instance to array
    private void addInstanceToArray() {
        message = new String[] {"\nAdd another instance by entering data separated by commas:",
                "[ productNumber, purchasePrice, productName, MAC, model, size ]",
                "ex: P123-223-7, 899.99, pricey phone, AC:10:9F:00:00:BB, luxury77, 10.2"};

        //split each line into an array
        String[] data = grabInput(message).split(", ");

        try {
            //properties used to create new cellPhone and added to controller
            phoneList.addCellPhone(new CellPhone(data[0], data[2], Double.parseDouble(data[1]),
                    data[3], Double.parseDouble(data[5]), data[4]));

        } catch (Exception e) {
            showMessage(new String[]{"*** Invalid format ***"});
        }


    }





    private int searchArrayForInstance() {
        message = new String[] {"\nEnter the phone's model OR array index"};

        String model = grabInput(message);
        int choice;

        //allow for multiple search types
        try {
            //initiate index search
            choice = Integer.parseInt(model);

            //out of bounds equates to not found
            if (choice < 0 || choice > this.phoneList.array().length -1) choice = -1;

        }catch (Exception e) {
            //initiate model search
            choice = phoneList.searchByModel(model);
        }

        //ternary message based on existence selected CellPhone
        System.out.println((choice == -1) ? "*** No such instance exists ***" : "There is an instance at index " + choice);
        return choice;
    }





    private void removeInstanceFromArray() {
        int found = searchArrayForInstance();

        //fail-fast, item not in array
        if (found == -1) return;

        message = new String[] {"\nConfirm the index of the item you'd like to remove"};

        try {
            int choice = Integer.parseInt(grabInput(message));

            //ternary message based on existence selected CellPhone
            message = new String[]{choice != found ? "*** Invalid entry ***" : "That instance removed from the array"};

            //fail-fast, deletion confirmation failed
            showMessage(message);
            if (choice != found) return;

            //remove cell phone from array
            phoneList.removeCellPhone(choice);


        } catch (Exception e) {
            showMessage(new String[]{"*** Invalid entry ***"});
        }

    }





    //list all items
    private void listAllItems() {
        String[] message = new String[phoneList.array().length];

        //fill array with string data about each listing
        for (int i = 0; i < phoneList.array().length; i++) message[i] = "<" + i + "> " + phoneList.array()[i].toString();

        if (phoneList.array().length == 0) {
            message = new String[]{"*** No items loaded into manager ***"};
        }
        showMessage(message);
    }





    //exit code 88 is to signify intentional kill of the program
    private void exit() {
        rawInput.close();
        System.exit(88);
    }







}
