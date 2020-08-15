/**
 * SyncThreadDemo.java
 *
 * 2020-08-14 pWurster
 *
 * Defines a class for to perform a task (incrementing an integer)
 * via synchronized Threading
 *
 */

package pw;

public class SyncThreadDemo extends Thread {
    //int wrapped in array for referencing by different threads
    int[] value;
    Thread thr;

    SyncThreadDemo(int[] sum) {
        this.value = sum;
    }

    public void start() {
        thr = new Thread(this);
        thr.start();
    }

    //this class runs threads sequentially
    public synchronized void run() {
        this.value[0]++;
    }

}