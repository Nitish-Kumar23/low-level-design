package multithreading;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLocking {

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        SharedResourceReadWrite sharedResourceReadWrite = new SharedResourceReadWrite(lock);
        Thread thread1 = new Thread(() -> new SharedResourceReadWrite(lock).consume());
//        Thread thread2 = new Thread(() -> new SharedResourceReadWrite(lock).consume());
//        thread1.start();
//        thread2.start();

        Thread thread3 = new Thread(() -> new SharedResourceReadWrite(lock).produce());
//        Thread thread4 = new Thread(() -> new SharedResourceReadWrite(lock).produce());
        thread3.start();
//        thread4.start();
        thread1.start();
    }

}

class SharedResourceReadWrite{

    private final ReadWriteLock readWriteLock;

    private boolean itemAvailable = false;

    public SharedResourceReadWrite(ReadWriteLock readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    public void consume(){
        try {
            readWriteLock.readLock().lock();
            System.out.println("consume lock acquired by " + Thread.currentThread().getName());
            System.out.println("consume lock acquired by " + Thread.currentThread().getName() + " item state by " + itemAvailable);
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle some exception
        }finally {
            readWriteLock.readLock().unlock();
            System.out.println("consume lock released by " + Thread.currentThread().getName());
        }
    }

    public void produce(){
        try {
            Thread.sleep(1000);
            readWriteLock.writeLock().lock();
            System.out.println("produce lock acquired by " + Thread.currentThread().getName());
            itemAvailable = true;
            System.out.println("produce item state by " + Thread.currentThread().getName() + " item state by " + itemAvailable);
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle some exception
        }finally {
            readWriteLock.writeLock().unlock();
            System.out.println("produce lock released by " + Thread.currentThread().getName() + " " +itemAvailable);
        }
    }
}
