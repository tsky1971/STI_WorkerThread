cmake_minimum_required(VERSION 3.16)
project(WorkerThread LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# Threads (Pthreads auf POSIX; auf Windows via pthreads-w32)
find_package(Threads REQUIRED)
if(MSVC)
find_package(PThreads4W REQUIRED)
endif()

add_executable(WorkerThread main.cpp)

if(MSVC)
  # Nur kosmetisch: typische MSVC-Einstellungen
  target_compile_definitions(WorkerThread PRIVATE _CRT_SECURE_NO_WARNINGS)
  target_link_libraries(WorkerThread PRIVATE PThreads4W::PThreads4W)
else()
  target_link_libraries(WorkerThread PRIVATE Threads::Threads)
endif()
