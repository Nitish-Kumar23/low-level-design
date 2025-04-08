package multithreading;

import java.util.concurrent.*;

public class CompletableFutureAction {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        // if we don't pass threadpoolexecutor it will by default use fork join pool thread pool
        CompletableFuture<String> completableFutureDefaultExecutor = CompletableFuture.supplyAsync(() -> {
            System.out.println("executing by " + Thread.currentThread().getName());
            return "task complete by default executor";
        });

        CompletableFuture<String> completableFutureCustomExecutor = CompletableFuture.supplyAsync(() -> {
            System.out.println("executing by " + Thread.currentThread().getName());
            return "task complete by custom executor";
        },threadPoolExecutor);

        try {
            System.out.println(completableFutureDefaultExecutor.get());
            System.out.println(completableFutureCustomExecutor.get());
        }catch (Exception exception){
            // handle exception here
        }

        CompletableFuture<String> supplyAsyncCompletableFuture = CompletableFuture.supplyAsync(() ->{
            try {
                System.out.println("supply async completed by " + Thread.currentThread().getName());
//                Thread.sleep(5000);
            }catch (Exception exception){
                // handle exception here
            }
            return "hello from supply async";
        }, threadPoolExecutor);

        // sequentially use the same thread which was used in supplyAsync call
        CompletableFuture<String> thenApplyFuture = supplyAsyncCompletableFuture.thenApply((String val) -> {
            try {
                System.out.println("thenApply completed by " + Thread.currentThread().getName());
                Thread.sleep(10000);
            }catch (Exception exception){
                // handle exception here
            }
            return "hi from thenApply";
        });

        // uses a different thread than supply async one
        CompletableFuture<String> thenApplyAsyncFuture = supplyAsyncCompletableFuture.thenApplyAsync((String val) -> {
            System.out.println("thenApply async completed " + Thread.currentThread().getName());
            return "hi from thenApply async";
        });

        try {
            System.out.println(thenApplyFuture.get());
            System.out.println(thenApplyAsyncFuture.get());
        }catch (Exception exception){
            // handle exception here
        }
    }

}
