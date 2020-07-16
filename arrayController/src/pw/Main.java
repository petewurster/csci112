/*
Main.java
2020-07-15 pWurster
 */
package pw;

public class Main {

    public static void main(String[] args) {
	    ArrayController ac = new ArrayController(
	            new CellPhone("P123-223-7", "pricey phone", 899.99, "AC:10:9F:00:00:BB", 10.2, "luxury77"),
                new CellPhone("CP004", "pricey phone sold cheap", 700.00, "AC:10:9F:66:66:66", 10.2, "luxury77"),
                new CellPhone("CP005", "next version of the device", 1000.00, "AC:10:9F:12:34:56", 12.0, "luxury78"));


	    ac.addCellPhone(new CellPhone("rrrrrrr7", "pricey phone", 899.99, "AC:10:9F:00:00:BB", 10.2, "luxury77"),
               new CellPhone("popppppp7", "pricey phone", 899.99, "AC:10:9F:00:00:BB", 10.2, "luxury77"));

	    ac.removeCellPhone(3);
	    ac.removeCellPhone();

        CellPhone[] cps = ac.array;


        for (CellPhone c: cps) {
            System.out.println(c);

        }





    }



}
