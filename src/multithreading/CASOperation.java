package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

// compare and swap operation
public class CASOperation {

    public static void main(String[] args) {
        CASSharedResource casSharedResource = new CASSharedResource();

        // incrementing counter sequentially
        for (int i=0;i<400;i++){
            casSharedResource.incrementCounter();
        }
        System.out.println(casSharedResource.getCounter());

        // incrementing counters in multiple thread
        // it will not work properly two threads can read common value and increment
        Thread thread1 = new Thread(() -> {
        for (int i=0;i<200;i++){
            casSharedResource.incrementMultiThreadCounter();
        }});

        Thread thread2 = new Thread(() -> {
            for (int i=0;i<200;i++){
                casSharedResource.incrementMultiThreadCounter();
            }});

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (Exception exception){
            // handle exception here
        }

        System.out.println(casSharedResource.getMultiThreadCounter());


        // incrementing counters in multiple thread using synchronized method
        // will work as only thread can access synchronized method at one time
        Thread thread3 = new Thread(() -> {
            for (int i=0;i<200;i++){
                casSharedResource.incrementSyncCounter();
            }});

        Thread thread4 = new Thread(() -> {
            for (int i=0;i<200;i++){
                casSharedResource.incrementSyncCounter();
            }});

        thread3.start();
        thread4.start();

        try {
            thread3.join();
            thread4.join();
        }catch (Exception exception){
            // handle exception here
        }

        System.out.println(casSharedResource.getSyncCounter());

        // incrementing counters in multiple thread using atomic integer
        // will work as only thread can access synchronized method at one time
        Thread thread5 = new Thread(() -> {
            for (int i=0;i<200;i++){
                casSharedResource.incrementAtomicInteger();
            }});

        Thread thread6 = new Thread(() -> {
            for (int i=0;i<200;i++){
                casSharedResource.incrementAtomicInteger();
            }});

        thread5.start();
        thread6.start();

        try {
            thread5.join();
            thread6.join();
        }catch (Exception exception){
            // handle exception here
        }

        System.out.println(casSharedResource.getAtomicInteger());

    }

}

class CASSharedResource{

    private int counter = 0;

    private int multiThreadCounter = 0;

    private int syncCounter = 0;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void incrementCounter(){
        // this is not a thread safe operation and not atomic, if multiple threads try to perform this operation then both threads can read same value
        // it's a 3 step process (counter = counter + 1)
        // load value, increment value and assign back
        counter++;
    }

    public int getCounter(){
        return counter;
    }

    public void incrementMultiThreadCounter(){
        multiThreadCounter++;
    }

    public int getMultiThreadCounter(){
        return multiThreadCounter;
    }

    public synchronized void incrementSyncCounter(){
        syncCounter++;
    }

    public int getSyncCounter(){
        return syncCounter;
    }

    public void incrementAtomicInteger(){
        // default increment by 1 otherwise use addAndGet
        atomicInteger.incrementAndGet();
    }

    public int getAtomicInteger(){
        return atomicInteger.get();
    }
}
