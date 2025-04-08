package multithreading;

public class JoinThread {

    public static void main(String[] args) {
        JoinThread joinThread = new JoinThread();

        System.out.println("starting main method, priority " + Thread.currentThread().getPriority());
        SharedResourceV2 sharedResourceV2 = new SharedResourceV2();

        // creating thread
        Thread thread = new Thread(() -> sharedResourceV2.task());
        thread.start();

        // we can assign priority to thread on scale of 1-10 (low -> high)
        thread.setPriority(Thread.MAX_PRIORITY);

        //
        thread.setDaemon(true);
        // join thread
        joinThread.joinThread(thread);
        System.out.println("main method complete");

    }

    private void joinThread(Thread thread){
        try {

            // waiting for current thread work to complete only then main thread will resume
            System.out.println("joinThread : waiting for thread1 task to complete");
            thread.join();
        }catch (Exception exception){

        }
    }

}

class SharedResourceV2{

    public synchronized void task(){
        System.out.println("inside task1");
        try {
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle some exception here
        }
        System.out.println("task finished " + Thread.currentThread().getName());
    }

}
