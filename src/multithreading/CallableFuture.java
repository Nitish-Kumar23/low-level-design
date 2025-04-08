package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableFuture {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        // default uses runnable doesnt return anything in response
        // future is used to know the status of task
        Future<?> future = threadPoolExecutor.submit(() -> {
            System.out.println("hello from executor");
        });

        try {
            Object object = future.get();
            if(object==null){
                System.out.println("nothing returned from future object");
            }

        }catch (Exception exception){
            // handle exception here
        }


        List<Integer> list = new ArrayList<>();
        // callable future returns any object
        Future<List<Integer>> listFuture = threadPoolExecutor.submit(() -> {
            System.out.println("hello from callable task");
            list.add(300);
            return list;
        });

        try {
            List<Integer> futureListResponse = listFuture.get();
            System.out.println(futureListResponse);
        }catch (Exception exception){
            // handle exception here
        }

        // our runnable is updating the list which we passed in constructor but if we need to access the list after task is completed
        // but dont have reference then we cannot access it so we can use runnable along with it pass expected response in parameter in threadpool submit action
        // both can work but callable is cleaner
        List<Integer> runnableTaskList = new ArrayList<>();
        Future<?> runnableFuture = threadPoolExecutor.submit(new RunnableTaskV1(runnableTaskList));

        try {
            Object object = runnableFuture.get();
            if(object==null){
                System.out.println("runnable future is null");
            }
            System.out.println(runnableTaskList);
        }catch (Exception exception){

        }


        // we can use runnable along with it pass expected response in parameter in threadpool submit action
        // both can work but callable is cleaner
        List<Integer> runnableWithTaskList = new ArrayList<>();
        Future<List<Integer>> runnableWithFutureTask = threadPoolExecutor.submit(new RunnableTaskV1(runnableWithTaskList), runnableWithTaskList);
        try {
            Object object = runnableWithFutureTask.get();
            if(object==null){
                System.out.println("runnable with future is null");
            }
            List<Integer> futureWithRunableTaskList = runnableWithFutureTask.get();
            System.out.println(futureWithRunableTaskList);
        }catch (Exception exception){
            // handle exception here
        }

    }

}

class RunnableTaskV1 implements Runnable{

    private List<Integer> list;

    public RunnableTaskV1(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("hello from runnable task v1");
        list.add(500);
    }
}
