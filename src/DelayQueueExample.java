import java.util.concurrent.*;

public class DelayQueueExample {

    public static void main(String[] args) {
        BlockingQueue<DelayWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayWorker("This is the first message", 7000));
            queue.put(new DelayWorker("This is the second message", 1000));
            queue.put(new DelayWorker("This is the third message", 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while(!queue.isEmpty())
        {
            try {
                DelayWorker worker = queue.take();
                System.out.println("Processing: " + worker);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class DelayWorker implements Delayed {
        private long duration;
        private String message;

        public DelayWorker(String message, long duration) {
            this.message = message;
            this.duration = System.currentTimeMillis() + duration;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (duration < ((DelayWorker) o).getDuration())
                return -1;
            else if (duration > ((DelayWorker) o).getDuration())
                return 1;
            else
                return 0;
        }

        public long getDuration() {
            return duration;
        }

        @Override
        public String toString() {
            return message;
        }

    }
}
