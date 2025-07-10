import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        Thread firstThread = new Thread(new FirstThread(exchanger));
        Thread secondThread = new Thread(new SecondThread(exchanger));

        firstThread.start();
        secondThread.start();
    }

    public static class FirstThread implements Runnable {
        private int counter;
        private Exchanger<Integer> exchanger;

        public FirstThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter++;
                System.out.println("First thread increment the counter: " + counter);

                try {
                    counter = exchanger.exchange(counter);
                    System.out.println("First thread received counter: " + counter);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static class SecondThread implements Runnable {
        private int counter;
        private Exchanger<Integer> exchanger;

        public SecondThread(Exchanger<Integer> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                counter--;
                System.out.println("Second thread decrement the counter: " + counter);

                try {
                    counter = exchanger.exchange(counter);
                    System.out.println("Second thread received counter: " + counter);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
