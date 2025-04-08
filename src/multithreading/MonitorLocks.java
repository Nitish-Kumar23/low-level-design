package multithreading;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 *
 * Monitor locks ensure that only one thread is able to access one particular segment of the code
 * using synchronized block
 *
 */
public class MonitorLocks {

    public static void main(String[] args) {
        System.out.println("started main method");

        MonitorLockExample monitorLockExample = new MonitorLockExample();

        // monitor lock is applied on the object if that particular object is acquired in a lock then the other thread performing task using same object will be blocked
        MonitorLockViaRunnable runnableObj = new MonitorLockViaRunnable(monitorLockExample);
        Thread thread1 = new Thread(runnableObj);

        Thread thread2 = new Thread(() -> {monitorLockExample.task2();});
        Thread thread3 = new Thread(() -> {monitorLockExample.task3();});

        MonitorLockExample lockExample = new MonitorLockExample();
        Thread thread4 = new Thread(() -> lockExample.task1());
        thread1.start();
        thread2.start();
        thread3.start();
//        thread4.start();
        System.out.println("exited main method");
    }


}

class MonitorLockExample{

    // synchronized keyword - thread will get the lock on the object as soon as method is invoked
    // if object is already locked then thread will wait for lock to be released
    public synchronized void task1(){
        try {
            long startTime = System.nanoTime();  // Get the start time in nanoseconds
            System.out.println("inside task 1 " + ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.of("UTC")));
            Thread.sleep(10000);

            long endTime = System.nanoTime();  // Get the end time in nanoseconds
            long elapsedTime = (endTime - startTime) / 1_000_000;  // Convert nanoseconds to milliseconds
            System.out.println("existing task 1, " + elapsedTime + "ms elapsed " + ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.of("UTC")));
        }catch (Exception exception){
            System.out.println("exception occurred in task1");
        }
    }

    // since synchronized is not used on method then thread can enter into method without checking lock
    public void task2(){
        System.out.println("before going inside task2 : " + ZonedDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.of("UTC")));

        // this particular block of code is synchronized in this case, lock will be checked and acquired on the object before entering the code block
        synchronized (this){
            long currentTimeinMillis = System.currentTimeMillis();
            System.out.println("inside synchronized block task 2 : " + ZonedDateTime.ofInstant(Instant.ofEpochMilli(currentTimeinMillis), ZoneId.of("UTC")));
        }
    }

    // no lock here, it can be executed without checking any lockx
    public void task3(){
        System.out.println("inside task 3");
    }

}

class MonitorLockViaRunnable implements Runnable{

    private final MonitorLockExample monitorLockExample;

    public MonitorLockViaRunnable(MonitorLockExample monitorLockExample) {
        this.monitorLockExample = monitorLockExample;
    }

    @Override
    public void run() {
        monitorLockExample.task1();
    }
}
