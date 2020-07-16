/*
ArrayController.java
2020-07-15 pWurster
 */
package pw;

public class ArrayController {
    public CellPhone[] array;


    ArrayController(CellPhone... items) {
        this.array = new CellPhone[0];
        this.addCellPhone(items);
    }




    //create an ArrayController from any number of CellPhones
    public void addCellPhone(CellPhone... newCells) {
        CellPhone[] newArray = new CellPhone[newCells.length + this.array.length];
        for (int i = 0; i < this.array.length; i++) {
            newArray[i] = this.array[i];
        }
        for (int i = 0; i < newCells.length; i ++) {
            newArray[this.array.length + i] = newCells[i];
        }
        this.array = newArray;
    }




    //called without args to remove last element
    public void removeCellPhone() {
        removeCellPhone(this.array.length - 1);
    }



    //removes target element
    public void removeCellPhone(int index) {
        //fail-fast
        if (0 > index || index >= this.array.length) return;

        CellPhone[] newArray = new CellPhone[this.array.length -1];
        int j = 0;
        for (int i = 0; i < this.array.length; i++) {
             if (i != index) {
                 newArray[j] = this.array[i];
                 j++;
            }
        }
        this.array = newArray;
    }









}


