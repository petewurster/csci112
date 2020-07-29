/**
 * Sorts.java
 * 2020-07-29 pWurster
 *
 *
 * This program holds my implementations of several in-place sorting methods:
 * bubbleSort, insertionSort, selectionSort, mergeSort, quickSort
 *
 * These implementations only work for integers, but serve as a skeleton for
 * custom object sorting implementations.  Reverse order is done by flipping
 * internal logic of the methods rather than re-copying the array backwards
 *
 */
package pw;


public abstract class Sorts {

    public static final String[] TESTS = new String[]{"", "bubble sort", "selection sort", "insertion sort", "merge sort", "quick sort" };
    public static final int[] SIZES = new int[]{0, 10000, 20000, 100000, 200000, 1000000, 2000000, 10000000, 20000000};



    /**
     * bubbleSort -- with early escape
     *
     * @param array
     * @param reverse [optional]
     */
     public static void bubbleSort(int[] array, boolean reverse) {
        //boolean flag allows shortcut termination if sort is completed before full iteration
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            //iterate once for every element
            for (int i = 0; i < array.length - 1; i++) {
                //if the current element is larger than the next element...
                if (array[i] > array[i + 1] && reverse == false || array[i] < array[i + 1] && reverse == true) {
                    //swap elements
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    //activate flag to quit early
                    swapped = true;
                }
            }
        }//end while

    }
    //overload function to allow default param
    public static void bubbleSort(int[] array) {
        bubbleSort(array, false);
    }





    /**
     * selectionSort
     *
     * @param array
     * @param reverse [optional]
     */
    public static void selectionSort(int[] array, boolean reverse) {
        int minPos;

        //iterate once for every element
        for (int index = 0; index < array.length; index++) {
            //set position of minimum value to current iteration
            //this protects previously sorted values
            minPos = index;

            //check for next lowest element
            for (int nextPos = index + 1; nextPos < array.length; nextPos++) {
                //swap current pointer with pointer to next lowest element
                if (array[nextPos] < array[minPos] && reverse == false ||
                        array[nextPos] > array[minPos] && reverse == true) minPos = nextPos;
            }

            //perform element swap
            if (minPos > index) {
                int temp = array[index];
                array[index] = array[minPos];
                array[minPos] = temp;
            }
        }

    }
    //overload function to allow default param
    public static void selectionSort(int[] array) {
        selectionSort(array, false);
    }







    /**
     * insertionSort
     *
     * @param array
     * @param reverse [optional]
     */
    public static void insertionSort(int[] array, boolean reverse) {
        int index;

        //iterate from slot 1
        for (int nextPos = 1; nextPos < array.length; nextPos++) {
            //set inspected value
            int value = array[nextPos];

            //set location for backwards checking
            index = nextPos - 1;

            //decrease location index until array is exhausted OR a larger value is found
            while (index >= 0 && array[index] > value && reverse == false ||
                    index >= 0 && array[index] < value && reverse == true) {
                //shift elements forward
                array[index + 1] = array[index];
                index--;
            }

            //place value into slot
            array[index + 1] = value;
        }

    }
    //overload function to allow default param
    public static void insertionSort(int[] array) {
        insertionSort(array, false);
    }







    /**
     * mergeSort
     *
     * @param array
     * @param reverse [optional]
     */
    public static void mergeSort(int[] array, boolean reverse) {
        //determine center
        int pivot = array.length / 2;

        //base case: single element / 2 == 0
        if (pivot == 0) return;

        //create sub-arrays
        int[] left = new int[pivot];
        int[] right = new int[array.length - pivot];

        //populate sub-arrays
        for (int i = 0; i < pivot; i++) left[i] = array[i];
        for (int i = pivot; i < array.length; i++) right[i - pivot] = array[i];

        //self-invoke for both sub-arrays
        mergeSort(left, reverse);
        mergeSort(right, reverse);

        //merge results
        merge(array, left, right, reverse);

    }
    //overload function to allow default param
    public static void mergeSort(int[] array) {
        mergeSort(array, false);
    }



    //merge -- helper function for mergeSort
    private static void merge(int[] merged, int[] left, int[] right, boolean reverse) {
        //incrementors for smaller arrays
        int l = 0;
        int r = 0;

        //iterate across larger array and fill it appropriately
        for (int i = 0; i < merged.length; i++) {
            //operate on both in-bounds arrays
            if (l < left.length && r < right.length) {
                if (left[l] < right[r] && reverse == false || left[l] > right[r] && reverse == true) {
                    merged[i] = left[l];
                    l++;
                }else{
                    merged[i] = right[r];
                    r++;
                }

            //operate only on remaining array
            }else{
                if (l < left.length) {
                    merged[i] = left[l];
                    l++;
                }else if (r < right.length) {
                    merged[i] = right[r];
                    r++;
                }
            }
        }//end loop

    }





    /**
     * quickSort -- implemented from pseudocode of Tony Hoare's methodology
     * interesting side note: Tony Hoare invented the null pointer
     *
     * @param array
     * @param reverse [optional]
     */
    public static void quickSort(int[] array, int lo, int hi, boolean reverse) {
        if (lo < hi) {
            //set new pivot point
            int pivot = partition(array, lo, hi, reverse);

            //self-invoke for low side and high side
            quickSort(array, lo, pivot, reverse);
            quickSort(array, pivot + 1, hi, reverse);
        }
    }
    //overload functions to allow default params
    public static void quickSort(int[] array, boolean reverse) {
        quickSort(array, 0, array.length - 1, reverse);
    }
    //overload functions to allow default params
    public static void quickSort(int[] array) {
        quickSort(array, false);
    }



    //partition -- helper function for quickSort
    private static int partition(int[]array, int lo, int hi, boolean reverse) {
        //capture pivot value for comparison
        int pivot = array[(hi + lo) / 2];

        //adjust index incrementors
        lo--;
        hi++;

        //loop until low and high indexes cross
        while (true) {

            //iterate through low side until value is found less than pivot value
            do {
                lo++;
            } while (array[lo] < pivot && reverse == false || array[lo] > pivot && reverse == true);

            //iterate through high side until value is greater than pivot value
            do {
                hi--;
            }while (array[hi] > pivot && reverse == false || array[hi] < pivot && reverse == true);

            //done swapping this in this partition
            if (lo >= hi) return hi;

            //swap sides of identified elements
            int temp = array[lo];
            array[lo] = array[hi];
            array[hi] = temp;

        }//end while
    }






}
