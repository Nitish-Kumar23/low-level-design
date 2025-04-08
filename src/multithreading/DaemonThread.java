package multithreading;

public class DaemonThread {

    public static void main(String[] args) {
        System.out.println("main method started");
        SharedResourceV3 sharedResourceV3 = new SharedResourceV3();

        Thread thread1 = new Thread(() -> sharedResourceV3.task1());
        Thread thread2 = new Thread(() -> sharedResourceV3.task2());

        thread1.start();

        // daemon thread is only alive till any one of the user thread is alive. Once all user thread complete their work the daemon thread also kills itself
        thread2.setDaemon(true);
        thread2.start();
        System.out.println("main method completed");
    }

}

class SharedResourceV3{


    public synchronized void task1(){
        try {
            Thread.sleep(10000);
        }catch (Exception exception){
            // handle some exception here
        }

        System.out.println("task1 completed " + Thread.currentThread().getName());
    }

    public void task2(){
        while (true){
            try {
                Thread.sleep(1000);
            }catch (Exception exception){
                // handle exception here
            }
            System.out.println("daemon thread working");
        }
    }

}
