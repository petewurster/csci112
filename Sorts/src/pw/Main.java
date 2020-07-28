package pw;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] testArray = createArrayOfInts(Sorter.options[0]);
//        testArray = new int[]{2,6,4,9,7,0,3,5,1,10,8};
//        Sorter.bubbleSort(testArray);
//        Sorter.insertionSort(testArray);
//        Sorter.selectionSort(testArray);

        Sorter.mergeSort(testArray);
//        Sorter.quickSort(testArray);

        for (int x: testArray) System.out.println(x);



    }



    public static int[] createArrayOfInts(int length) {
        Random rnd = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = rnd.nextInt();
        return array;
    }



}
