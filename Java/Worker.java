
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

class Worker implements Runnable {
	private final int id;
	protected long iterations;
	
	Worker(int _id, long _iterations) {
		this.id = _id;
		this.iterations = _iterations;
	}

    @Override
    public void run() {
		// Messung pro Worker
        long start = System.nanoTime();
		
		double tmp = 1.0;
		for (long j = 0; j < iterations; j++) {
			tmp *= 1.0000001;  // einfache, aber nicht triviale Rechenoperation
		}
		// Damit tmp nicht optimiert wird
		if (tmp == 0.0) System.out.println("never");
		  
		long end = System.nanoTime();
        double durationSec = (end - start) / 1_000_000_000.0;

        System.out.printf("Thread #%d dauerte: %.3f Sekunden%n", id, durationSec);
		System.out.println("Thread #" + id + " sagt Hallo!");
		// danach endet der Thread    
    }
}
