//Import
import java.util.*;
import java.io.*;
//Import nostri pacchetti
import dictionary.*;
import api.*;
import generator.*;
import template.*;

public class Main {
    public static void main(String[] args) {
        //Variabili

        //sentence immessa dall'utente
        String s = ""; //es. The quick brown fox jumps over the lazy dog, while he went home
        //sentence generata dal nostro programma
        String result = "";
        Generator g = new Generator();
        //oggetto Scanner per leggere l'input da terminale
        Scanner scanner = new Scanner(System.in);

        //Variabili di controllo per cosa inserisce l'utente
        boolean check = false;
        int mode = 0;
        //numero di frasi da comporre
        int sentenceNumber = 0;
        //
        int sentenceCount = 0;
        int past = 0, present = 0, future = 0;
        int temp = 0;
        
        System.out.println("[System]: Welcome to NoSenseGenerator! :D");

        //STEP 1: L'utente decide se inserire una frase di input o meno
        while(!check){
            System.out.println("[System]: Do you want to: \n (1).Insert a sentence that we'll analyze and use tokens \n (2).Generate a totally random sentence \n Please insert 1 or 2");
            mode = scanner.nextInt();
            scanner.nextLine(); //Consuma il carattere \n

            if(mode == 1){
                System.out.println("[System]: Insert the sentence");
                s = scanner.nextLine();
                check = true;
            } else if (mode == 2){
                check = true;
            }else {
                System.out.println("[System]: What is this number!? You can insert only 1 or 2");
            }
        }

        //Resetto le variabili di controllo
        check = false;

        //STEP 2: L'utente decide quante frasi creare
        while(!check){
            System.out.println("[System]: How many sentences do you want to create?");
            sentenceNumber = scanner.nextInt();
            scanner.nextLine(); //Consuma il carattere \n

            if(sentenceNumber > 5){
                System.out.println("[System]: Dear, we're not at NASA, do you think my computer can handle that many sentences? Enter a smaller number please");
            } else if (sentenceNumber < 1){
                System.out.println("[System]: What's the point of the program if you don't even want to create a single sentence? >:(");
            } else {
                check = true;
            }
        }

        //Resetto le variabili di controllo
        check = false;

        //STEP 3: L'utente decide quante frasi al passato, presente, futuro creare


        //check PAST
        while(!check){
            System.out.printf("[System]: How many PAST tense sentences do you want to create? [0-%d]\n", sentenceNumber);
            past = scanner.nextInt();
            //past=Integer.parseInt(scanner.next());
            
            scanner.nextLine(); //Consuma il carattere \n

            if(past > sentenceNumber){
                System.out.println("[System]: Uh oh, you've run out of sentences, enter a smaller number");
            } else if (past < 0){
                System.out.println("[System]: Seriously you entered a NEGATIVE number?? -_-");
            } else {
                sentenceCount = past;
                check = true;
            }
        }

        //Resetto le variabili di controllo
        if(sentenceCount<sentenceNumber){
            check = false;
        } else {
            System.out.println("[System]: You will create 0 presente sentences");
        }

        //check presente

        while(!check){
            System.out.printf("[System]: How many PRESENT tense sentences do you want to create? [0-%d]\n", sentenceNumber-sentenceCount);
            present = scanner.nextInt();
            scanner.nextLine(); //Consuma il carattere \n

            if(sentenceCount + present > sentenceNumber){
                System.out.println("[System]: Uh oh, you've run out of sentences, enter a smaller number");
            } else if (present < 0){
                System.out.println("[System]: Seriously you entered a NEGATIVE number?? -_-");
            } else {
                sentenceCount += present;
                check = true;
            }
        }

        future=sentenceNumber - past - present;
        System.out.printf("[System]You will create %d future sentences", future);

        scanner.close();
       

        try {
            // Codice che potrebbe lanciare un'eccezione

            System.out.println();

            if(past > 0){
                System.out.println("[System]: Here you PAST sentences");
            }

            for (int i = 0; i < past; i++){
                result = g.genSentence(s, 0);
                System.out.println(i +1 + ": " + result); 
            }

            if(present > 0){
                System.out.println("[System]: Here you PRESENT sentences");
            }

            for (int i =0; i < present; i++){
                result = g.genSentence(s, 1);
                System.out.println(i +1 + ": " + result); 
            }

            if(future > 0){
                System.out.println("[System]: Here you FUTURE sentences");
            }

            for (int i =0; i < future; i++){
                result = g.genSentence(s, 1);
                System.out.println(i +1 + ": " + result); 
            }

            System.out.println("[System]: Thank you for using NoSenseGenerator, we hope to see you soon ^_^");
            


            // System.out.println("Frase iniziale: " + s);
            // result = g.genSentence(s,tense);
            // System.out.println(result); 







            
        } catch (Exception e) {
            //Gestione dell'eccezione
            System.out.println("[Error]: " + e.getMessage());
        }











    }
}
