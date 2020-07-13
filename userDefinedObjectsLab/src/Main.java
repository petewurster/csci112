public class Main {
    public static void main(String[] args) {

        //instantiate some cellphones
        CellPhone phone1 = new CellPhone("P123-223-7", "pricey phone", 899.99, "AC:10:9F:00:00:BB", 10.2, "luxury77");

        //same phone in a different retailer's inventory system
        CellPhone phone2 = new CellPhone("CP004", "pricey phone sold cheap", 700.00, "AC:10:9F:66:66:66", 10.2, "luxury77");

        //create a blank cellphone object
        CellPhone phone3 = new CellPhone();

        //display states of phones
        System.out.println("phone1");
        System.out.println(phone1);
        System.out.println("phone2");
        System.out.println(phone2);
        System.out.println("phone3");
        System.out.println(phone3);
        System.out.println();

        //build out phone3
        phone3.setModel("luxury78");
        phone3.setMAC("AC:10:9F:12:34:56");
        phone3.setScreenSize(12.0);
        phone3.setProductName("next version of the device");
        phone3.setPurchasePrice(1000.00);
        phone3.setProductNumber("CP005");

        //display changes
        System.out.println("phone3");
        System.out.println(phone3);
        System.out.println();


        String message = "";
        //demonstrate utility functions/////////////////////////////////////////////

        //checks for equality
        message = "test using equals()";
        System.out.println(message);

        if (phone1.equals(phone2)) {
            message = "Phone1 and Phone2 are the same phone (model number \'" + phone2.getModel() + "\' even though they have \ndifferent properties in different retailer's inventory systems)";
        } else {
            message = "these are not the same";
        }
        System.out.println(message);
        System.out.println();


        message = "test using compareTo()";
        System.out.println(message);
        if (phone1.compareTo(phone2) < 0) {
            message = "phone1's model \'" + phone1.getModel() + "\' comes lexographically BEFORE phone2's model \'" + phone2.getModel() + "\'";
        } else if (phone1.compareTo(phone2) > 0) {
            message = "phone1's model \'" + phone1.getModel() + "\' comes lexographically AFTER phone2's model \'" + phone2.getModel() + "\'";
        }else {
            message = "these phones are the same";
        }

        System.out.println(message);
        System.out.println();




        //demonstrate comparison being made correctly
        message = "a different test using compareTo()";
        System.out.println(message);
        if (phone3.compareTo(phone2) < 0) {
            message = "phone3's model \'" + phone3.getModel() + "\' comes lexographically BEFORE phone2's model \'" + phone2.getModel() + "\'";
        } else if (phone3.compareTo(phone2) > 0) {
            message = "phone3's model \'" + phone3.getModel() + "\' comes lexographically AFTER phone2's model \'" + phone2.getModel() + "\'";
        }else {
            message = "these phones are the same";
        }

        System.out.println(message);







    }


}
