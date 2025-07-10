import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueExample {

    public static class FirstWorker implements Runnable {

        private PriorityBlockingQueue<Person> queue;

        public FirstWorker(PriorityBlockingQueue<Person> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                queue.put(new Person(30));
                queue.put(new Person(15));
                Thread.sleep(2000);
                queue.put(new Person(16));
                Thread.sleep(3000);
                queue.put(new Person(45));
                Thread.sleep(2000);
                queue.put(new Person(11));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class SecondWorker implements Runnable {

        private PriorityBlockingQueue<Person> queue;

        public SecondWorker(PriorityBlockingQueue<Person> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(queue.take());
                System.out.println(queue.take());
                Thread.sleep(1000);
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class Person implements Comparable<Person>{
        private int age;

        public Person(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.age, o.age);
        }

        @Override
        public String toString() {
            return "Person{age=" + age + '}';
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}
