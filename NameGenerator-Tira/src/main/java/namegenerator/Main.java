package namegenerator;

public class Main {

    public static void main(String[] args) {
        
        Loader l = new Loader();
        
        Trie males = l.loadNames("/maleNames.txt");
        Trie females = l.loadNames("/femaleNames.txt");
        Trie lasts = l.loadNames("/lastnames.txt");
        
        Generator mg = new Generator(males);
        Generator fg = new Generator(females);
        Generator lg = new Generator(lasts);
   
        System.out.println("***");
        System.out.println(lg.generateName(100000, 10));
        System.out.println(lg.generateName(3, 18));
        System.out.println(lg.generateName(1, 10));

    }
    
}
