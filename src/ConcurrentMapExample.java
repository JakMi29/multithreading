import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapExample {

    public static void main(String[] args) {
    ConcurrentMap<String, Integer> map = new java.util.concurrent.ConcurrentHashMap<>();

        Thread firstWorkerThread = new Thread(new FirstWorker(map));
        Thread secondWorkerThread = new Thread(new SecondWorker(map));

        firstWorkerThread.start();
        secondWorkerThread.start();

        try {
            firstWorkerThread.join();
            secondWorkerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class FirstWorker implements Runnable {

        private ConcurrentMap<String, Integer> map;

        public FirstWorker(ConcurrentMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                map.put("B", 12);
                Thread.sleep(1000);
                map.put("Z", 5);
                map.put("A", 25);
                Thread.sleep(2000);
                map.put("D", 25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class SecondWorker implements Runnable {

        private ConcurrentMap<String, Integer> map;

        public SecondWorker(ConcurrentMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(map.get("A"));
                Thread.sleep(2000);
                System.out.println(map.get("Z"));
                System.out.println(map.get("B"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
