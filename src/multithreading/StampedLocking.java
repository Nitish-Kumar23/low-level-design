package multithreading;

import java.util.concurrent.locks.StampedLock;

public class StampedLocking {

    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
//        Thread thread1 = new Thread(() -> new StampedLockingSharedResource(stampedLock).consume());
//        Thread thread2 = new Thread(() -> new StampedLockingSharedResource(stampedLock).produce());
//
//        thread1.start();
//        thread2.start();

        Thread thread3 = new Thread(() -> new StampedLockingSharedResource(stampedLock).consumeV1());
        Thread thread4 = new Thread(() -> new StampedLockingSharedResource(stampedLock).produceV1());

        thread3.start();
        thread4.start();
    }

}

class StampedLockingSharedResource{

    private StampedLock stampedLock;

    private boolean itemAvailable = false;

    private int var = 10;

    public StampedLockingSharedResource(StampedLock stampedLock) {
        this.stampedLock = stampedLock;
    }

    public void consume(){
        long stamp = stampedLock.readLock();
        try {
            System.out.println("consume acquired read lock");
            Thread.sleep(10000);
        }catch (Exception exception){
            System.out.println("exception occured" + exception.getLocalizedMessage());
            // handle some exception
        }finally {
            // add some final block of code
            stampedLock.unlockRead(stamp);
            System.out.println("lock released");
        }
    }

    public void produce(){
        long stamp = stampedLock.writeLock();
        try {
            System.out.println("produce acquired write lock");
            Thread.sleep(10000);
            itemAvailable = true;
        }catch (Exception exception){
            System.out.println("produce exception occurred " + exception.getLocalizedMessage());
            // handle some exception
        }finally {
            // add some final block of code
            stampedLock.unlockWrite(stamp);
            System.out.println("produce lock released");
        }
    }

    public void consumeV1(){
        try {
            long stamp = stampedLock.tryOptimisticRead();
            var = 15;
            System.out.println("consume try optimistic read stamp" + stamp + " " + Thread.currentThread().getName());
            Thread.sleep(6000);
            if(stampedLock.validate(stamp)){
                System.out.println("updated value success " + Thread.currentThread().getName());
            }else {
                // rollback if some thread put a write lock already
                System.out.println("write happened, reverting update " + Thread.currentThread().getName());
                var = 10;
            }
        }catch (Exception exception){
            System.out.println("exception occured" + exception.getLocalizedMessage());
            // handle some exception
        }finally {
            // add some final block of code
            System.out.println("optimistic verified " + Thread.currentThread().getName());
        }
    }

    public void produceV1(){
        long stamp = stampedLock.writeLock();
        try {
            System.out.println("produce acquired write lock "+ Thread.currentThread().getName());
            Thread.sleep(10000);
            var = 20;
            itemAvailable = true;
        }catch (Exception exception){
            System.out.println("produce exception occurred " + exception.getLocalizedMessage());
            // handle some exception
        }finally {
            // add some final block of code
            stampedLock.unlockWrite(stamp);
            System.out.println("write lock released by produceV1 " + Thread.currentThread().getName());
        }
    }
}
