#!/bin/bash

#Per usare su windows il file .sh, bisogna scaricarsi git bash e compilare dal suo terminale

#Crea la cartella 'out' se non esiste, li' verranno salvati tutti i file .class
mkdir -p out
#Debug: su GitHub non e' salvata la cartella out

#Percorso del file JAR della libreria esterna JSON
LIB_PATH="src/libs/json-20250107.jar"

#Classpath per Windows (Git Bash usa ';' come separatore su JVM Windows)
CP="src;$LIB_PATH"

#Compila tutti i file .java trovati in src + main.java, usando il classpath
javac -cp "$CP" -d out main.java $(find src -name "*.java")

#Se la compilazione ha successo, esegui il programma, altrimenti dai errore
if [ $? -eq 0 ]; then
  echo "Compilazione riuscita. Avvio..."
  java -cp "out;$LIB_PATH" Main
else
  echo "Errore nella compilazione."
fi


read -p "Premi INVIO per chiudere..."
