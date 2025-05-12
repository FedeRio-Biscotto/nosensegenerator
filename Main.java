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
        String s = ""; //es. The quick brown fox jumps over the lazy dog, while he went home
        String result = "";
        int tense = 2; //0 = passato, 1 = presente, 2 = futuro
        Generator g = new Generator();
        Scanner scanner = new Scanner(System.in); //oggetto Scanner per leggere l'input da terminale

        //Variabili di controllo per cosa inserisce l'utente
        boolean check = false;
        int mode = 0;
        
        System.out.println("[System]: Welcome to NoSenseGenerator! :D");

        // while(!check){
        //     System.out.println("[System]: Do you want to: \n (1).Insert a sentence that we'll analyze and use tokens \n (2).Generate a totally random sentence? \n Please insert 1 or 2");
        //     mode = scanner.nextInt();

        //     if(mode == 1 || mode == 2){
        //         check = true;
        //     }
        // }

        //Resetto le variabili di controllo
        check = false;
        mode = 1;

        //Leggi la frase inserita
        s = scanner.nextLine();

        try {
            // Codice che potrebbe lanciare un'eccezione
            // for (int i =0; i < 20; i++){
            //     result = g.genSentence(s);
            //     System.out.println(i +1 + ": " + result); 
            // }
            System.out.println("Frase iniziale: " + s);
            // result = g.genSentence(s,tense);
            // System.out.println(result); 







            
        } catch (Exception e) {
            //Gestione dell'eccezione
            System.out.println("[Error]: " + e.getMessage());
        }











    }
}
