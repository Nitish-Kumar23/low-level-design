package multithreading;

import java.util.concurrent.*;

public class ThreadpoolFuture {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Future<?> future = threadPoolExecutor.submit(() -> {
           try {
               Thread.sleep(10000);
           }catch (Exception exception){
               // handle exception here
           }
        });

        // caller is checking if this task is complete or cancelled or any exception -- done
        System.out.println(" is done " + future.isDone());

        try {
            future.get(2,TimeUnit.SECONDS);
        }catch (Exception exception){
            System.out.println("exception occurred " + exception);
        }

        System.out.println(" is done " + future.isDone());

        threadPoolExecutor.shutdown();
        try {
            future.get();
        }catch (Exception exception){
            // handle exception here
        }

        System.out.println(" is done " + future.isDone());
        System.out.println("is cancelled " + future.isCancelled());
    }

}
