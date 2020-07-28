/**
 * TutorialSorts.java
 * 2020-07-28 pWurster
 *
 * This class uses methods from my Sorts class that were re-written
 * to make use of the Main.TutorialWebsite object's compareTo() method
 *
 */
package pw;

public abstract class TutorialSorts {

    //selectionSort
    public static void selectionSort(Main.TutorialWebsite[] array, boolean reverse) {
        int minPos;

        //iterate once for every element
        for (int index = 0; index < array.length; index++) {
            //set position of minimum value to current iteration
            //this protects previously sorted values
            minPos = index;

            //check for next lowest element
            for (int nextPos = index + 1; nextPos < array.length; nextPos++) {
                //swap current pointer with pointer to next lowest element
                if (array[nextPos].compareTo(array[minPos]) < 0 && reverse == false ||
                        array[nextPos].compareTo(array[minPos]) > 0 && reverse == true) minPos = nextPos;
            }

            //perform element swap
            if (minPos > index) {
                Main.TutorialWebsite temp = array[index];
                array[index] = array[minPos];
                array[minPos] = temp;
            }
        }

    }
    //overload function to allow default param
    public static void selectionSort(Main.TutorialWebsite[] array) {
        selectionSort(array, false);
    }









    //insertionSort
    public static void insertionSort(Main.TutorialWebsite[] array, boolean reverse) {
        int index;

        //iterate from slot 1
        for (int nextPos = 1; nextPos < array.length; nextPos++) {
            //set inspected value
            Main.TutorialWebsite value = array[nextPos];

            //set location for backwards checking
            index = nextPos - 1;

            //decrease location index until array is exhausted OR a larger value is found
            while (index >= 0 && array[index].compareTo(value) > 0 && reverse == false ||
                    index >= 0 && array[index].compareTo(value) < 0 && reverse == true) {
                //shift elements forward
                array[index + 1] = array[index];
                index--;
            }

            //place value into slot
            array[index + 1] = value;
        }

    }
    //overload function to allow default param
    public static void insertionSort(Main.TutorialWebsite[] array) {
        insertionSort(array, false);
    }








}
