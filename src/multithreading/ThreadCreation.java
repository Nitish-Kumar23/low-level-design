package multithreading;

public class ThreadCreation {

    public static void main(String[] args) {

        System.out.println("going inside main method");

        threadViaRunnable();

        threadViaThreadExtension();

        System.out.println("finished main method " + Thread.currentThread().getName());

    }

    private static void threadViaThreadExtension() {
        MultiThreadingViaThreadExtension multiThreadingViaThreadExtension = new MultiThreadingViaThreadExtension();
        multiThreadingViaThreadExtension.start();
    }

    private static void threadViaRunnable() {
        // creating thread using class which implements runnable class
        MultiThreadingViaRunnable multiThreadingViaRunnable = new MultiThreadingViaRunnable();

        // create thread at this step
        Thread thread = new Thread(multiThreadingViaRunnable);

        // till this point, thread is created but it's run method is not invoked
        // this will invoke run method of thread class
        thread.start();
        thread.interrupt();

        Thread thread1 = new Thread(multiThreadingViaRunnable);
        thread1.start();
    }
}

class MultiThreadingViaRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("in MultiThreadingLec1 current thread " + Thread.currentThread().getName());
    }
}


class MultiThreadingViaThreadExtension extends Thread{

    @Override
    public void run(){
        System.out.println("MultiThreadingViaThreadExtension : current thread name " + Thread.currentThread().getName());
    }
}
