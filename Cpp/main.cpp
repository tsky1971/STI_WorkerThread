#include <chrono>
#include <cstdint>
#include <inttypes.h>
#include <iostream>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <vector>

#ifdef _WIN32
#include <windows.h>
#define sleep(x) Sleep((x) * 1000)
#else
#include <unistd.h>
#endif

#define MAX_THREADS 20
#define ITERATIONS 5000000000

void* printMessage(void* arg)
{
    intptr_t tid = reinterpret_cast<intptr_t>(arg);
    double i = 1;
	
    auto start = std::chrono::high_resolution_clock::now();
    for (unsigned long long it = 0; it < ITERATIONS; ++it) {
        i *= 1.0000001;
    }
	
    auto end = std::chrono::high_resolution_clock::now();
    double exec_time_ns = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start).count();
    printf("Thread #%ld sagt Hallo mit einer ", tid);
    
    float seconds = exec_time_ns / 1000000000;
	printf("Dauer fuer Thread#%ld: %f sec \n", tid, seconds);
	
    if (i == 0) {
        std::cout << "poop";
    }
    return nullptr; // statt pthread_exit(NULL)
}

int main(int argc, char* argv[])
{
    srand(time(0));

    int numOfThreads = MAX_THREADS;
    printf("Number of threads: %d\n", numOfThreads);

#ifndef _WIN32
    pthread_t threads[numOfThreads];
#else
    std::vector<pthread_t> threads(numOfThreads);
#endif

	auto start = std::chrono::high_resolution_clock::now();

    int rc;
    for (long t = 0; t < numOfThreads; t++) {
        printf("Creating thread #%ld\n", t);
        rc = pthread_create(&threads[t], NULL, printMessage, (void*)t);
        if (rc) {
            printf("Fehler beim Erstellen von Thread #%ld\n", t);
            exit(-1);
        }
    }
    //pthread_exit(NULL);
	for (int t = 0; t < numOfThreads; t++) {
        rc = pthread_join(threads[t], NULL);
        if (rc) {
            printf("Fehler beim Warten auf Thread #%d\n", t);
        }
    }
	
	auto end = std::chrono::high_resolution_clock::now();
    double exec_time_ns = std::chrono::duration_cast<std::chrono::nanoseconds>(end - start).count();    
    float seconds = exec_time_ns / 1000000000;
	printf("Dauer fuer alles %f sec \n", seconds);
}
