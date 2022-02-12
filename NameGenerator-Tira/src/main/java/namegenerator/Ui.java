package namegenerator;

import java.util.Scanner;


public class Ui {
    
    public void start(Scanner s) {
    
        Loader l = new Loader();
        
        Trie males = l.loadNames("/maleNames.txt");
        Trie females = l.loadNames("/femaleNames.txt");
        Trie lasts = l.loadNames("/lastnames.txt");
        
        Generator mg = new Generator(males);
        Generator fg = new Generator(females);
        Generator lg = new Generator(lasts);
        System.out.println("WELCOME TO NAMEGENERATOR!\n");
        
        
        while (true) {
            System.out.println("To quit, enter -1.");
            System.out.println("Which degree of Markov chain you want to use? (Must be positive integer.)");
            System.out.println("\n");
            
            try {
                int k = s.nextInt();
                if (k == -1) {
                    break;
                }
        
                System.out.println("Maximum lenght for name? (1-25)");
                int n = s.nextInt();
                
                System.out.println("OK\n");
                System.out.println("***");
                System.out.println("Female name: " + fg.generateName(k, n));
                System.out.println("***");
                System.out.println("Male name: " + mg.generateName(k, n));
                System.out.println("***");
                System.out.println("Lastname: " + lg.generateName(k, n));
                System.out.println("***\n");
            
            } catch (Exception e) {
                System.out.println("Error " + e);
                break;
            }
        }
    }
}
