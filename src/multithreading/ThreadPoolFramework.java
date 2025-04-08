package multithreading;

import java.util.concurrent.*;

public class ThreadPoolFramework {

    public static void main(String[] args) {
        threadpoolFunctionality();
    }

    private static void threadpoolFunctionality() {
        CustomThreadFactory customThreadFactory = new CustomThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                customThreadFactory, new RejectionHandler());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        System.out.println("core pool size " + threadPoolExecutor.getCorePoolSize());
        System.out.println("maximum pool size " + threadPoolExecutor.getMaximumPoolSize());

        // current thread will be zero as there are no tasks submitted to it, threads are only created by threadpoolexecutor when we submit atleast one task
        // Total number of threads created is min(corepoolsize,tasksSubmitted)
        System.out.println("current thread in pool : " + threadPoolExecutor.getPoolSize());
//        ExecutorTasks executorTasks = new ExecutorTasks();
//        RunnableTask runnableTask = new RunnableTask();
//        threadPoolExecutor.submit(runnableTask);
//        threadPoolExecutor.submit(() -> executorTasks.printSomething());
//        threadPoolExecutor.submit(() -> executorTasks.printSomething());
//        threadPoolExecutor.submit(() -> executorTasks.printSomething());
        System.out.println("current thread in pool : " + threadPoolExecutor.getPoolSize() + " active count " + threadPoolExecutor.getActiveCount() + " completed task count " + threadPoolExecutor.getCompletedTaskCount() + " thread " + Thread.currentThread().getName());
        for (int i=0;i<12;i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (Exception exception) {
                    // handle exception here
                }
                System.out.println("task processed, current thread in pool : " + threadPoolExecutor.getPoolSize() + " active count :  " + threadPoolExecutor.getActiveCount() + " is terminating " + threadPoolExecutor.isTerminating()
                        + " completed task count " + threadPoolExecutor.getCompletedTaskCount() + " thread " + Thread.currentThread().getName() + " queue size " + threadPoolExecutor.getQueue().size());
            });
        }

        // graceful shutdown, let other tasks get completed before terminating threadpool. new task will not be accepted
        threadPoolExecutor.shutdown();
    }

}

class ExecutorTasks{

    public void printSomething(){
        try {
            Thread.sleep(1);
        }catch (Exception exception){
            // handle exception here
        }
        System.out.println("hello from thread" + Thread.currentThread().getName());
    }

}

class RunnableTask implements Runnable{

    @Override
    public void run() {
        System.out.println("hello from runnable " + Thread.currentThread().getName());
    }
}

// these properties will be assigned to thread while being created
class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println("creating custom thread");
        Thread thread = new Thread(r);
//        thread.setName("custom_thread");
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}

// custom rejection handler to log tasks or check later
class RejectionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("task rejected " + r.toString());
    }
}
