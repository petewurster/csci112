/*
ArrayController.java
2020-07-15 pWurster

This class encapsulates an array of (CellPhone) objects and provides functionality to access those objects
 */
package pw;

public class ArrayController {
    private CellPhone[] array = new CellPhone[0];


    //create empty ArrayController
    ArrayController(){}

    //create an ArrayController from any number of CellPhones
    ArrayController(CellPhone... items) {
        this.addCellPhone(items);
    }



    //getter used by outside classes to access the array
    public CellPhone[] array(){
        return this.array;
    }





    //add any number of CellPhones to the array
    public void addCellPhone(CellPhone... newCells) {
        //set new array to length of old array + number of new elements
        CellPhone[] newArray = new CellPhone[newCells.length + this.array.length];

        //loop to fill newArray with old data
        for (int i = 0; i < this.array.length; i++) newArray[i] = this.array[i];

        //loop to add new data to newArray
        for (int i = 0; i < newCells.length; i ++) newArray[this.array.length + i] = newCells[i];

        //member property now points to newArray
        this.array = newArray;
    }




    //called without args to remove last element
    public void removeCellPhone() {
        removeCellPhone(this.array.length - 1);
    }



    //overloaded method removes target element
    public void removeCellPhone(int index) {
        //fail-fast
        if (0 > index || index >= this.array.length) return;

        //create newArray with length smaller than old array
        CellPhone[] newArray = new CellPhone[this.array.length -1];

        int n = 0;      //marks index within new array

        //loop thru old array; fill newArray with objects, ignoring target index
        for (int i = 0; i < this.array.length; i++) {
            if (i != index) {
                 newArray[n] = this.array[i];
                 n++;       //update newArray index location
            }
        }
        //member property now points to newArray
        this.array = newArray;
    }





    //search for target element based on model; returns index of 1st
    //object with a matching model
    public int searchByModel(String target) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i].getModel().equals(target)) return i;
        }
        return -1;
    }





}


