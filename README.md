# STI_WorkerThread

### Windows Vorbereitungen:

git clone https://github.com/microsoft/vcpkg
.\vcpkg\bootstrap-vcpkg.bat
.\vcpkg\vcpkg install pthreads:x64-windows

cmake -S . -B build -G "Visual Studio 17 2022" -A x64 -DCMAKE_TOOLCHAIN_FILE=C:\Pfad\zu\vcpkg\scripts\buildsystems\vcpkg.cmake

cmake --build build --config Release


### Linux Vorbereitungen:

sudo apt update
sudo apt install -y build-essential cmake

cmake -S . -B build
cmake --build build -j

#### C++ Kompilieren

g++ -o mainCpp main.cpp -std=c++11



### JAVA Kompilieren:
javac Main.java Worker.java
java Main

#### oder wenn man das Kompilat wiederverwenden will:

jar cvf STI_workterthread.jar *.class

'm': Fügt Manifest-Informationen hinzu
jar cvfm STI_workterthread.jar Manifest.txt *.class

#### Führt das Programm über das erstellte JAR-Archiv aus
java -jar STI_workterthread.jar
