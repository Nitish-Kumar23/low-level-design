package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCondition {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ThreadConditionResource threadConditionResource = new ThreadConditionResource(lock);
        ProduceTaskCondition produceRunnable = new ProduceTaskCondition(threadConditionResource);
        Thread produceThread = new Thread(produceRunnable);

        Thread consumeThread = new Thread(() -> threadConditionResource.consume());

        produceThread.start();
        consumeThread.start();


    }

}

class ThreadConditionResource{

    private boolean item = false;

    private final ReentrantLock lock;

    private final Condition condition;

    public ThreadConditionResource(ReentrantLock reentrantLock) {
        this.lock = reentrantLock;
        this.condition = reentrantLock.newCondition();
    }

    public void produce(){
        try {
            lock.lock();
            System.out.println("lock acquired on producer " + Thread.currentThread().getName());
            item = true;

            System.out.println("notifying all waiting threads from producer " + Thread.currentThread().getName());

            // notify all locks waiting to wake up again and work
            condition.signalAll();
            System.out.println("notified all waiting threads from producer " + Thread.currentThread().getName());
        }catch (Exception exception){
            // handle exception
        }finally {
            lock.unlock();
            System.out.println("lock released on producer " + Thread.currentThread().getName());
        }
    }

    public void consume(){
        while (!item) {
            try {
                lock.lock();
                System.out.println("lock acquired on consumer " + Thread.currentThread().getName());
                System.out.println("consumer waiting for item " + Thread.currentThread().getName());

                // await will put the thread in block state and release lock for time being and wake up on signalAll event
                condition.await();
                System.out.println("received notification and item is now available");
            } catch (Exception exception) {
                // handle exception
            } finally {
                lock.unlock();
                System.out.println("lock released on consumer " + Thread.currentThread().getName());
            }
        }


        item = false;
        System.out.println("existing from consumer " + Thread.currentThread().getName());

    }

}

class ProduceTaskCondition implements Runnable{

    private ThreadConditionResource threadConditionResource;

    public ProduceTaskCondition(ThreadConditionResource threadConditionResource) {
        this.threadConditionResource = threadConditionResource;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle exception
        }

        threadConditionResource.produce();

    }
}