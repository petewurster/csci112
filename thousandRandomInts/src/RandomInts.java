import java.util.Random;


public class RandomInts implements MergeSort{
    /*
    class constants are used to set defaults
     */
    public static final int LENGTH = 1000;
    public static final int MIN = 1;
    public static final int MAX = 10;

    //
    private int[] array;
    private int[] tally;
    private int length;
    private double average;
    private int min;
    private int max;

    //default constructor
    RandomInts() {
        Random rnd = new Random();
        this.array = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            this.array[i] = rnd.nextInt(MAX) + 1;
        }
        this.min = MIN;
        this.max = MAX;
        this.length = LENGTH;
        this.tally = tallyFrequency();
        this.average = findAverage();
    }



    //verbose constructor
    RandomInts(int length, int min, int max) {
        Random rnd = new Random();
        this.array = new int[length];
        for (int i = 0; i < length; i++) {
            this.array[i] = rnd.nextInt(max) + 1;
        }
        this.min = min;
        this.max = max;
        this.length = length;
        this.average = findAverage();
        this.tally = tallyFrequency();

    }

    //calculate average of the values in the collection
    private double findAverage() {
        int acc = 0;
        for (int value: this.array) {
            acc += value;
        }
        return (double) acc / (double) this.length;
    }



    //override toString method
    public String toString() {
        return "array: len=" + this.getLength() + ", min=" + this.getMin() + ", max=" + this.getMax() + ", avg=" +this.getAverage();
    }



    //return a new sorted copy of the array; object's collection remains unaltered
    public int[] sorted() {
        //split the collection into two smaller arrays
        int[] array1 = new int[this.length / 2];
        int[] array2 = new int[this.length - this.length / 2];
        for (int i = 0; i < this.length; i++) {
            if (i < array1.length) {
                array1[i] = this.array[i];
            }else{
                array2[i - array1.length] = this.array[i];
            }
        }

        //sort the smaller arrays
        MergeSort.bubbleSort(array1);
        MergeSort.bubbleSort(array2);

        //mergeSort them back together
        return MergeSort.merge(array1, array2);
    }


    //calculate frequency of each value in the collection
    private int[] tallyFrequency() {
        int[] tally = new int[this.getMax()];
        for (int value: this.getArray()) {
            tally[value - 1]++;
        }
        return tally;
    }


    //getters are self-explanatory
    public int getLength() {
        return this.length;
    }
    public int getMax() {
        return this.max;
    }
    public int getMin() {
        return this.min;
    }
    public double getAverage() {
        return this.average;
    }
    public int[] getArray() {
        return this.array;
    }
    public int[] getTally() {
        return this.tally;
    }


    //no setters included as the intent of this Class is to fully encapsulate the integer collection



}//end class
