package multithreading;

public class SharedResourceApp {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // producer task, can be done with runnable functional interface also
        ProduceTask producerTask = new ProduceTask(sharedResource);
        Thread producerThread = new Thread(producerTask);

        ConsumeTask consumeTask = new ConsumeTask(sharedResource);
        Thread consumeThread = new Thread(consumeTask);

//        Thread thread = new Thread(() -> sharedResource.addItem());
//        thread.start();

        producerThread.start();
        consumeThread.start();
    }

}

// https://notebook.zohopublic.in/public/notes/74tdo47dcfcf05e644cf192c411a6d78ec1dc

class SharedResource{

    boolean itemAvailable = false;

    // synchronized put the shared resource object into monitoring lock
    public synchronized void addItem(){
        itemAvailable = true;
        System.out.println("addItem : items available, notify all threads");

        // all threads which are waiting on this shared resource object, notify them all so they can get to runnable state from waiting
        notifyAll();
    }

    public synchronized void consumeItem(){
        System.out.println("consume item invoked by " + Thread.currentThread().getName());

//        consumeUsingIfBlock();

        // using while loop to avoid "spurious wake-up", sometimes because of system noise
        while (!itemAvailable){
           try {
               long startTime = System.nanoTime();
               System.out.println("consume item thread waiting now " + Thread.currentThread().getName());

               // explicitly calling wait so thread goes into wait state, and releases all monitoring lock on Shared Resource object
               wait();
               long waitTime = (System.nanoTime() - startTime)/1000000;
               System.out.println("consume item thread wait time in sec " + waitTime/1000);
           }catch (Exception exception){
                // handle exception here
           }
        }

        System.out.println("consume item thread released " + Thread.currentThread().getName());
        itemAvailable = false;
    }

    private void consumeUsingIfBlock(){
        if(!itemAvailable){
            try {
                long startTime = System.nanoTime();
                System.out.println("consume item thread waiting now " + Thread.currentThread().getName());

                // explicitly calling wait so thread goes into wait state, and releases all monitoring lock on Shared Resource object
                wait();
                long waitTime = (System.nanoTime() - startTime)/1000000;
                System.out.println("consume item thread wait time in sec " + waitTime/1000);
            }catch (Exception exception){
                // handle exception here
            }
        }
    }

}

class ProduceTask implements Runnable{

    private final SharedResource sharedResource;

    public ProduceTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("produce task now starting " + Thread.currentThread().getName());
        }catch (Exception exception){
            // handle exception here
        }
        System.out.println("produce task thread resumed " + Thread.currentThread().getName());
        sharedResource.addItem();
    }

}

class ConsumeTask implements Runnable{

    private final SharedResource sharedResource;

    public ConsumeTask(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        sharedResource.consumeItem();
    }
}
