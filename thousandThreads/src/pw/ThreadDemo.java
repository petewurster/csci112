/**
 * ThreadDemo.java
 *
 * 2020-08-14 pWurster
 *
 * Defines a class for to perform a task (incrementing an integer)
 * via asynchronous Threading
 *
 */

package pw;

public class ThreadDemo extends Thread{
    //int wrapped in array for referencing by different threads
    int[] value;
    Thread thr;

    ThreadDemo(int[] sum) {
        this.value = sum;
    }

    public void start() {
        thr = new Thread(this);
        thr.start();
    }

    //this class runs threads
    public void run() {
        this.value[0]++;
    }

}
