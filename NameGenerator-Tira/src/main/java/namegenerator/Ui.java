package namegenerator;

import java.util.Scanner;


public class Ui {
    
    public void start(Scanner s) {
        Loader l = new Loader();
            
        Trie lasts = l.loadNames("/lastnames.txt");       
        Trie females = l.loadNames("/femaleNames.txt");
        Trie males = l.loadNames("/maleNames.txt");
        
        Generator mg = new Generator(males);
        Generator fg = new Generator(females);
        Generator lg = new Generator(lasts);
        System.out.println("WELCOME TO NAMEGENERATOR!");
        
        while (true) {
            System.out.println("(To quit, enter -1.)");
            System.out.println("Which degree of Markov chain you want to use? (Must be positive integer. Smaller degrees generate more random "
                    + " names. Try 3 for a start.)\n");
            
            try {
                int k = s.nextInt();
                if (k == -1) {
                    break;
                }
        
                System.out.println("Maximum lenght for name? (1-25)\n");
                int n = s.nextInt();
                
                System.out.println("First letter for name? (a-ö)\n");
                String letter = s.next().toLowerCase();
                
                if (letter.charAt(0) < 97 || letter.charAt(0) > 256) {
                    System.out.println("That's not a valid letter, we'll use 'q'.\n");
                    letter = "q";
                } else {
                    System.out.println("OK\n");
                }
                
                System.out.println("*****FEMALE*****");
                System.out.println("Without first letter: " + fg.generateName(k, n,  ".", false));
                System.out.println("With first letter: " + fg.generateName(k, n, letter, false));
                System.out.println("With good ending: " + fg.generateName(k, n, letter, true));
                System.out.println("\n");
                System.out.println("*****MALE*****");
                System.out.println("Without first letter: " + mg.generateName(k, n,  ".", false));
                System.out.println("With first letter: " + mg.generateName(k, n, letter, false));
                System.out.println("With good ending: " + mg.generateName(k, n, letter, true));
                System.out.println("\n");
                System.out.println("*****LASTNAME*****");
                System.out.println("Without first letter: " + lg.generateName(k, n, ".", false));
                System.out.println("With first letter: " + lg.generateName(k, n, letter, false));
                System.out.println("With good ending: " + lg.generateName(k, n, letter, true));
                System.out.println("\n*****AGAIN?*****");
            
            } catch (Exception e) {
                System.out.println("Error " + e);
                break;
            }
        }
    }
}
