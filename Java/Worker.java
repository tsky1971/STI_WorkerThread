
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

class Worker implements Runnable {
	private final int id;
	
	Worker(int id) {
		this.id = id;
	}

    @Override
    public void run() {
		int waitTime = ThreadLocalRandom.current().nextInt(1, 6); // 1..5 Sekunden
		try {
			Thread.sleep(waitTime * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return; // vorzeitig beendet
		}
		System.out.println("Thread #" + id + " sagt Hallo!");
		// danach endet der Thread    
    }
}
