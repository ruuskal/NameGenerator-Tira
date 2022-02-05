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
        
        System.out.println(mg.firstDegreeMarkov());
        System.out.println(mg.secondDegreeMarkov());
        System.out.println("***");
        System.out.println(fg.firstDegreeMarkov());
        System.out.println(fg.secondDegreeMarkov());
        System.out.println("***");
        System.out.println(lg.firstDegreeMarkov());
        System.out.println(lg.secondDegreeMarkov());
    }
    
}
