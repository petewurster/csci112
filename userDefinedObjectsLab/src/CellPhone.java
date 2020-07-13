/*
CellPhone.java
2020-07-12 pWurster
 */

public class CellPhone extends Product implements Comparable<CellPhone>{
    private String MAC;
    private double screenSize;
    private String model;

    //default constructor
    CellPhone() {
        super();
    }

    //verbose constructor
    CellPhone(String number, String name, double price, String MAC, double size, String model) {
        super(number, name, price);
        this.MAC = MAC;
        this.screenSize = size;
        this.model = model;

    }

    //getters///////////////////////////////
    public String getMAC() {
        return this.MAC;
    }
    public double getScreenSize() {
        return this.screenSize;
    }
    public String getModel() {
        return this.model;
    }


    //override
    public String toString() {
        return super.toString() + " ::: " + this.getMAC() + " : " +this.getModel() + " : " + this.getScreenSize() + "cm";
    }


    //setters/////////////////////////////
    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
    public void setScreenSize(double size) {
        this.screenSize = size;
    }
    public void setModel(String model) {
        this.model = model;
    }

    //utility funcs/////////////////////
    public boolean equals(CellPhone that) {
        return this.getModel().equals(that.getModel());
    }

    //compares phones based on model
    @Override
    public int compareTo(CellPhone that) {
        //since CellPhone.model is of type String, apply the String's compareTo method for CellPhone objects
        //this makes it easier to sort CellPhones alphabetically by model
        return this.getModel().compareTo(that.getModel());
    }





}
