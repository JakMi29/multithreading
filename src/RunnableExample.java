import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RunnableExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Future<String>> futuresList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
             Future<String> future=service.submit(new Worker(i));
             futuresList.add(future);
        }

        for (Future<String> f: futuresList){
            try {
                System.out.println(f.get());
            }catch (InterruptedException| ExecutionException e){
                e.printStackTrace();
            }
        }
    }

    public static class Worker implements Callable<String>{
        private int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "Id: " + id + " thread" + Thread.currentThread().getName()+ " finished job";
        }
    }
}
