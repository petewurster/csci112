/**
 * ThreadTests.java
 *
 * 2020-08-14 pWurster
 *
 * This is a driver program to demonstrate differences between
 * synchronized vs Asynchronous threading
 *
 */


package pw;


public class ThreadTests {

    public static final int NUM_OF_OPS = 1000;

    public static void main(String[] args) {
    ////////////////////begin synchronized demo////////////////////////
        //use array to wrap ints for manipulation by separate threads
        int[] sum = new int[]{0};

        //grab timeStamp for start
        double time = System.nanoTime();

        System.out.println("running " + NUM_OF_OPS + " synchronized threads...");

        //fire up a bunch of threads
        for (int i = 0; i < NUM_OF_OPS; i++) {
            SyncThreadDemo syn =  new SyncThreadDemo(sum);
            syn.start();
        }

        //capture & display completion time
        time = (System.nanoTime() - time) / 1000000000.0;
        System.out.println("\tcompleted in " + time + " seconds");


    ////////////////////begin asynchronous demo////////////////////////
        //reset array for async test
        sum = new int[]{0};

        //grab timeStamp for start
        time = System.nanoTime();

        System.out.println("running " + NUM_OF_OPS + " Asynchronous threads...");

        //fire up a bunch of threads
        for (int i = 0; i < NUM_OF_OPS; i++) {
            ThreadDemo thr = new ThreadDemo(sum);
            thr.start();
        }

        //capture & display completion time
        time = (System.nanoTime() - time) / 1000000000.0;
        System.out.println("\tcompleted in " + time + " seconds");


    }

}
