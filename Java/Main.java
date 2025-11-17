// Hier ist ein einfaches Java-Programm, das Variable viele Threads startet, um eine Ausgabe jede 1-5 Sekunden zu erzeugen:

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Anzahl der Threads definieren
        // not ThreadSafe int numOfThreads = random.nextInt(50) + 1; 
		int numOfThreads = ThreadLocalRandom.current().nextInt(1, 51);
        System.out.println("Number of threads: " + numOfThreads);
        
        // Thread Pool erstellen 
        ExecutorService service = Executors.newFixedThreadPool(numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            service.execute(new Worker(i));
        }
        service.shutdown();
		// Auf Abschluss aller Aufgaben warten (wie pthread_join)
		if (!service.awaitTermination(1, TimeUnit.MINUTES)) {
			System.err.println("Timeout beim Warten auf Threads – beende hart.");
			service.shutdownNow();
		}
    }
}

// Dieses Programm erstellt zwischen 1 und 50 Threads, die in zufälligen Intervallen eine Nachricht auf der Konsole ausgeben.
