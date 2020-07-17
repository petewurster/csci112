/*
Main.java
2020-07-17 pWurster
 */
package pw;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    //globals use in Main
    public static final String FILE = "phoneData.csv";
    public static ArrayController phoneList = new ArrayController();
    public static String[] message = new String[10];
    public static Scanner rawInput = new Scanner(System.in);


    public static void main(String[] args) {
        //menu loop
        while (true) {
            try {
                message = showMenu();
                int menuChoice = Integer.parseInt(grabInput(message));

                switch (menuChoice) {
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
    public static String[] showMenu() {
        return new String[]{"\n-------------------------", "Menu for Cell Phone array", "-------------------------",
                "[1] Show all items", "[2] Load data from file", "[3] Add an item", "[4] Search by model",
                "[5] Delete an item", "[6] Save data to file", "[7] Quit"};
    }





    //grab user input for menu
    public static String grabInput(String[] message) {
        showMessage(message);
        return rawInput.nextLine();
    }





    //show message line by line
    public static void showMessage(String[] message) {
        for (String line: message) System.out.println(line);
    }




    //load object array from file
    public static void loadArrayControllerFromFile() {
        try (Scanner in = new Scanner(new File(FILE))) {
            while (in.hasNextLine()) {
                //split each line
                String[] data = in.nextLine().split(", ");

                //properties used to create new cellPhone and added to controller
                phoneList.addCellPhone(new CellPhone(data[0], data[2], Double.parseDouble(data[1]),
                        data[3], Double.parseDouble(data[5]), data[4]));
            }
        } catch (Exception e) {
            showMessage(new String[]{"*** Error reading from file \"phoneData.csv\" ***"});
        }
    }





    public static void saveArrayDataToFile() {
        try (PrintWriter out = new PrintWriter(new File(FILE))) {
            for (CellPhone item: phoneList.array) {
                //use regex to fix delimiters and print as csv
                out.println(item.toString()
                        .replaceAll(" :{1,3} ", ", ")
                        .replaceAll("(cm)|\\$", ""));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static void addInstanceToArray() {
        message = new String[] {"\nAdd another instance by entering data separated by commas:",
                "[ productNumber, purchasePrice, productName, MAC, model, size ]",
                "ex: P123-223-7, 899.99, pricey phone, AC:10:9F:00:00:BB, luxury77, 10.2"};

        String[] data = grabInput(message).split(", ");

        try {
            //properties used to create new cellPhone and added to controller
            phoneList.addCellPhone(new CellPhone(data[0], data[2], Double.parseDouble(data[1]),
                    data[3], Double.parseDouble(data[5]), data[4]));

        } catch (Exception e) {
            showMessage(new String[]{"*** Invalid format ***"});
        }


    }





    public static int searchArrayForInstance() {
        message = new String[] {"\nEnter the phone's model"};
        int choice = phoneList.searchByModel(grabInput(message));
            System.out.println(choice == -1 ? "*** No such instance exists ***" : "The next instance of that model is located at index " + choice);
        return choice;
    }





    public static void removeInstanceFromArray() {
        int found = searchArrayForInstance();

        //fail-fast
        if (found == -1) return;

        message = new String[] {"\nConfirm the index of the item you'd like to remove"};

        try {
            int choice = Integer.parseInt(grabInput(message));
            if (choice == found) {
                phoneList.removeCellPhone(choice);
                showMessage(new String[]{"That instance removed from the array"});
                return;
            }
            showMessage(new String[]{"*** Invalid entry ***"});
        } catch (Exception e) {
            showMessage(new String[]{"*** Invalid entry ***"});
        }

    }





    //list all items
    public static void listAllItems() {
        String[] message = new String[phoneList.array.length];
        for (int i = 0; i < phoneList.array.length; i++) message[i] = phoneList.array[i].toString();

        if (phoneList.array.length == 0) {
            message = new String[]{"*** No items loaded into manager ***"};
        }
        showMessage(message);
    }





    //exit code 88 is to signify intentional kill of the program
    public static void exit() {
        rawInput.close();
        System.exit(88);
    }







}
