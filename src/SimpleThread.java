class SimpleThread {
    public static void main(String[] args) {
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();
        Thread thread1 = new Thread(runner1);
        Thread thread2 = new Thread(runner2);
        thread1.setDaemon(true);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Both threads have finished execution.");
    }

    static class Runner1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runner1: " + i);
            }
        }
    }

    static class Runner2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runner2: " + i);
            }
        }
    }

}
