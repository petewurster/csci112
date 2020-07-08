/*
MergeSort.java
2020-07-07 pWurster

this Interface provides methods perform a merge sort
on arrays of ints (currently only intended for use within
my RandomInts Class)
 */

public interface MergeSort {

    /**
     *Merges two pre-sorted arrays
     * @param array1 a smaller pre-sorted array
     * @param array2 another smaller pre-sorted array
     * @return final merged array of ints
     */
    static int[] merge(int[] array1, int[] array2) {
        //larger array to hold merged arrays
        int[] merged = new int[array1.length + array2.length];
        //incrementors for smaller arrays
        int i1 = 0;
        int i2 = 0;

        //iterate across larger array and fill it appropriately
        for (int i = 0; i < merged.length; i++) {
            //operate on both in-bounds arrays
            if (i1 < array1.length && i2 < array2.length) {
                if (array1[i1] < array2[i2]) {
                    merged[i] = array1[i1];
                    i1++;
                }else{
                    merged[i] = array2[i2];
                    i2++;
                }
            //operate only on remaining array
            }else{
                if (i1 < array1.length) {
                    merged[i] = array1[i1];
                    i1++;
                }else if (i2 < array2.length) {
                    merged[i] = array2[i2];
                    i2++;
                }
            }
        }
        return merged;
    }

    /**
     * Sort an array of ints with bubble sort algorithm
     * @param array to be sorted
     */
    static void bubbleSort(int[] array) {
        //iterate over entire array, index is not used.  this loop is needed because a worst case
        //scenario will have to perform the comparison loop this many times to ensure proper sorting
        for (int unused: array) {
            //comparison loop to swap values
            for (int next = 1; next < array.length; next++) {
                int prev = next - 1;
                if (array[prev] > array[next]) {
                    int temp = array[prev];
                    array[prev] = array[next];
                    array[next] = temp;
                }
            }
        }
    }



}
