
package pw;

public abstract class Sorter {


    public static final int[] options = new int[]{16, 10000, 20000, 100000, 200000, 1000000, 2000000, 10000000, 20000000};


    public static void bubbleSort(int[] array) {
        //boolean flag allows shortcut termination if sort is completed before full iteration
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            //iterate once for every element
            for (int i = 0; i < array.length - 1; i++) {
                //if the current element is larger than the next element...
                if (array[i] > array[i + 1]) {
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







    public static void selectionSort(int[] array) {
        int minPos;

        //iterate once for every element
        for (int index = 0; index < array.length; index++) {
            //set position of minimum value to current iteration
            //this protects previously sorted values
            minPos = index;

            //check for next lowest element
            for (int nextPos = index + 1; nextPos < array.length; nextPos++) {
                //swap current pointer with pointer to next lowest element
                if (array[nextPos] < array[minPos]) minPos = nextPos;
            }

            //perform element swap
            if (minPos > index) {
                int temp = array[index];
                array[index] = array[minPos];
                array[minPos] = temp;
            }
        }

    }







    public static void insertionSort(int[] array) {
        int index;

        //iterate from slot 1
        for (int nextPos = 1; nextPos < array.length; nextPos++) {
            //set inspected value
            int value = array[nextPos];

            //set location for backwards checking
            index = nextPos - 1;

            //decrease location index until array is exhausted OR a larger value is found
            while (index >= 0 && array[index] > value) {
                //shift elements forward
                array[index + 1] = array[index];
                index--;
            }

            //place value into slot
            array[index + 1] = value;
        }

    }




    public static void mergeSort(int[] array){
        //determine center
        int pivot = array.length / 2;

        //base case: single element / 2 == 0
        if (pivot == 0) return;

        //create sub-arrays
        int[] left = new int[pivot];
        int[] right = new int[array.length - pivot];

        //populate sub-arrays
        for (int i = 0; i < pivot; i++) left[i] = array[i];
        for (int i = pivot; i < array.length; i++) right[i -pivot] = array[i];

        //self-invoke for both sub-arrays
        mergeSort(left);
        mergeSort(right);

        //merge results
        merge(array, left, right);

    }



    private static void merge(int[] merged, int[] left, int[] right) {
        //incrementors for smaller arrays
        int l = 0;
        int r = 0;

        //iterate across larger array and fill it appropriately
        for (int i = 0; i < merged.length; i++) {
            //operate on both in-bounds arrays
            if (l < left.length && r < right.length) {
                if (left[l] < right[r]) {
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




    public static void quickSort(int[] array) {
        







    }








}
