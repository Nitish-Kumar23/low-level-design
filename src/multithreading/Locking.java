package multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class Locking {

    public static void main(String[] args) {

        // synchronized will not work here as we are two different instances of Shared Resource class are being used
        // both thread will acquire lock at same time and release as well as it's two different object on shared resource class
        SharedResourceV4 sharedResourceV4 = new SharedResourceV4();
        Thread thread1 = new Thread(() -> sharedResourceV4.task());
        Thread thread2 = new Thread(() ->new SharedResourceV4().task());
        thread1.start();
        thread2.start();

        // when we have two different instances, use reentrant lock
        ReentrantLock lock = new ReentrantLock();
        Thread thread3 = new Thread(() -> new SharedResourceV4().taskV1(lock));
        Thread thread4 = new Thread(() -> new SharedResourceV4().taskV1(lock));
        thread3.start();
        thread4.start();

    }

}

class SharedResourceV4{

    boolean available = false;

    public synchronized void task(){
        try {
            System.out.println("lock aquired for 10 sec " + Thread.currentThread().getName());
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle exception here
        }

        available = true;
        System.out.println("lock released " + Thread.currentThread().getName());
    }

    public void taskV1(ReentrantLock lock){
        try {
            lock.lock();
            System.out.println("task1 : lock aquired for 10 sec " + Thread.currentThread().getName());
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle exception here
        }
        finally {
            available = true;
            lock.unlock();
            System.out.println("task1 : lock released " + Thread.currentThread().getName());
        }
    }

}
