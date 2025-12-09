// Hier ist ein einfaches Java-Programm, das Variable viele Threads startet, um eine Ausgabe jede 1-5 Sekunden zu erzeugen:

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	static int threadCount = 20;
    static long iterations = 5_000_000_000L;
		
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Anzahl der Threads definieren
        // not ThreadSafe int numOfThreads = random.nextInt(50) + 1; 
		// int numOfThreads = ThreadLocalRandom.current().nextInt(1, 51);
		int numOfThreads = threadCount;
        System.out.println("Number of threads: " + numOfThreads);
        
        // Thread Pool erstellen 
        ExecutorService service = Executors.newFixedThreadPool(numOfThreads);
		
		// Messung der Gesamtzeit
        long overallStart = System.nanoTime();

        for (int i = 0; i < numOfThreads; i++) {
            service.execute(new Worker(i, iterations));
        }
        service.shutdown();
		
		// Auf Abschluss aller Aufgaben warten (wie pthread_join)
		if (!service.awaitTermination(1, TimeUnit.MINUTES)) {
			System.err.println("Timeout beim Warten auf Threads – beende hart.");
			service.shutdownNow();
		}
		
		long overallEnd = System.nanoTime();
        double overallDurationSec = (overallEnd - overallStart) / 1_000_000_000.0;
        System.out.printf("Gesamtlaufzeit aller Threads: %.3f Sekunden%n", overallDurationSec);
    }
}

