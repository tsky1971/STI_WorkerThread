

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <vector>
#include <cstdint>
#include <inttypes.h>

#ifdef _WIN32
#  include <windows.h>
#  define sleep(x) Sleep((x)*1000)
#else
#  include <unistd.h>
#endif

#define MAX_THREADS 50

void *printMessage(void *arg) {   
   intptr_t tid = reinterpret_cast<intptr_t>(arg);
   int waitTime = rand() % 5 + 1;
   sleep(waitTime);
   printf("Thread #%ld sagt Hallo!\n", tid);
   return nullptr; // statt pthread_exit(NULL)
}

int main(int argc, char *argv[]) {
   srand(time(0));
   
   int numOfThreads = rand() % MAX_THREADS + 1;
   printf("Number of threads: %d\n", numOfThreads);
   
#ifndef _WIN32
   pthread_t threads[numOfThreads];
#else
   std::vector<pthread_t> threads(numOfThreads);
#endif
   
   int rc;
   for(long t = 0; t < numOfThreads; t++){
       printf("Creating thread #%ld\n", t);
       rc = pthread_create(&threads[t], NULL, printMessage, (void *)t);
       if (rc){
           printf("Fehler beim Erstellen von Thread #%ld\n", t);
           exit(-1);
       }
   }
   pthread_exit(NULL);
}
