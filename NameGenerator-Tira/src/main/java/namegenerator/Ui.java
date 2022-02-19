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
        System.out.println("WELCOME TO NAMEGENERATOR!");
        
        
        while (true) {
            System.out.println("(To quit, enter -1.)");
            System.out.println("Which degree of Markov chain you want to use? (Must be positive integer.)\n");
            
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
                    System.out.println("That's not a valid letter, we'll use 'z'.\n");
                    letter = "z";
                } else {
                    System.out.println("OK\n");
                }
                
                System.out.println("***");
                System.out.println("Female name: " + fg.generateName(k, n));
                System.out.println("With first letter: " + fg.generateNameWithFirstLetter(k, n, letter));
                System.out.println("***");
                System.out.println("Male name: " + mg.generateName(k, n));
                System.out.println("With first letter: " + mg.generateNameWithFirstLetter(k, n, letter));
                System.out.println("***");
                System.out.println("Lastname: " + lg.generateName(k, n));
                System.out.println("With first letter: " + lg.generateNameWithFirstLetter(k, n, letter));
                System.out.println("***");
            
            } catch (Exception e) {
                System.out.println("Error " + e);
                break;
            }
        }
    }
}
