package multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreLocking {

    public static void main(String[] args) {
        // allows multiple threads = permits value to access critical section
        // useful for collection pools, collections
        Semaphore semaphore = new Semaphore(2);

        Thread thread1 = new Thread(() -> new SemaphoreSharedResource(semaphore).task());
        Thread thread2 = new Thread(() -> new SemaphoreSharedResource(semaphore).task());
        Thread thread3 = new Thread(() -> new SemaphoreSharedResource(semaphore).task());
        Thread thread4 = new Thread(() -> new SemaphoreSharedResource(semaphore).task());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

}

class SemaphoreSharedResource{

    private final Semaphore semaphore;

    public SemaphoreSharedResource(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void task(){
        try {
            semaphore.acquire();
            System.out.println("acquired semaphore lock " + Thread.currentThread().getName());
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle exception
        }finally {
            System.out.println("released semaphore lock " + Thread.currentThread().getName());
            semaphore.release();
        }
    }
}
