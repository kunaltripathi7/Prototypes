class Main {
    public static void main(String[] args) {
        // System.out.println("Hello");
        // Thread thread =  new Thread(() -> {
        //     for (int i = 0; i<10; i++) {
        //         System.out.println("Running the thread");
        //     }
        // });
        // thread.start();
        // Collections.sort(list, (a, b) -> Integer.compare(a,b));


        // Multithreading 

        // locks
        // Non blocking attempt
        TryLockExample example = new TryLockExample();
        Thread t1 = new Thread(example::performTask);
        Thread t2 = new Thread(example::performTask);
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        t2.start();
        
        

    }
}