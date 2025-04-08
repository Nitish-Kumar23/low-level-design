package multithreading;

public class SharedResourceDeadlock {

    public static void main(String[] args) {
        System.out.println("starting main method");
        SharedResourceDeadlock sharedResourceDeadlock = new SharedResourceDeadlock();
        SharedResourceV1 sharedResourceV1 =  new SharedResourceV1();

        Thread thread1 = new Thread(() -> sharedResourceV1.task1());
        Thread thread2 = new Thread(() -> sharedResourceV1.task2());

        thread1.start();
        sharedResourceDeadlock.sleep(1000);
        thread2.start();

        System.out.println("main thread complete");
    }

    private void sleep(long sleepTimeMs){
        try {
            Thread.sleep(sleepTimeMs);
        }catch (Exception exception){
            // handle some exception here
        }

        System.out.println("thread sleep done for ms " + sleepTimeMs);
    }

}

class SharedResourceV1{

    public synchronized void task1(){
        System.out.println("lock acquired, inside task1 thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle some exception here
        }

        System.out.println("lock released, task1 complete by thread" + Thread.currentThread().getName());
    }

    public synchronized void task2(){
        System.out.println("lock acquired & released completed task 2");
    }
}
