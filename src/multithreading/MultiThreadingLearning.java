package multithreading;

public class MultiThreadingLearning {

    /**
     *
     * Javac multithreading.MultiThreadingLearning.java - (coverts code into bytecode)
     * java multithreading.MultiThreadingLearning - bytecode -> machine code
     * process is instance of a java program, when it's created it starts with one thread and that's known as main thread,
     * we can use that to create new threads to perform task concurrently
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Current thread name : " + Thread.currentThread().getName());
    }


    // java -Xms 256m -Xmx 2g  MultiThreadingLearning (this is how JVM instance is allocated heap memory)
    // -Xms 256m : minimum allocated at this time 256mb
    // -Xmx 2g : maximum allocated at this time 2gb

}

