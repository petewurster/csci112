/*
Product.java
2020-07-12 pWurster
 */
package pw;

public class Product {
    protected String productNumber;
    protected String productName;
    protected double purchasePrice;

    //default constructor
    Product() {
    }

    //verbose constructor
    Product(String number, String name, double price) {
        this.productNumber = number;
        this.productName = name;
        this.purchasePrice = price;
    }

    //getters/////////////////////////////////////
    public String getProductNumber() {
        return this.productNumber;
    }
    public String getProductName() {
        return this.productName;
    }
    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    //override
    public String toString() {
        return this.getProductNumber() + " : $" + this.getPurchasePrice()  + " : " + this.getProductName();
    }

    //setters////////////////////////////////////////
    public void setProductNumber(String number) {
        this.productNumber = number;
    }
    public void setProductName(String name) {
        this.productName = name;
    }
    public void setPurchasePrice(double price) {
        this.purchasePrice = price;
    }
}
