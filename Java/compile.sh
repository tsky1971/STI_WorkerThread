#!/bin/bash
# Kompiliert die Java-Quellcodedateien
javac Main.java Worker.java

# Erstellt die JAR-Datei
# c: Erstellt eine neue JAR-Datei
# v: Zeigt eine ausführliche Ausgabe an
# f: Gibt den Dateinamen der JAR-Datei an
# m: Fügt das Manifest aus der angegebenen Datei hinzu
jar cvfm STI_workterthread.jar Manifest.txt *.class
