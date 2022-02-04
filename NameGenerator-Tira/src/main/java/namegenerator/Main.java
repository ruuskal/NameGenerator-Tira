package namegenerator;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie(256);
  
        trie.insert("mia");
        trie.insert("mira");
        trie.insert("mirkku");
        trie.insert("mirka");
        trie.insert("kari");
        trie.insert("markkula");
        trie.insert("lauri");
        trie.insert("laura");
        trie.insert("lauren");
        trie.insert("urkki");
        trie.insert("ac");
        trie.insert("bbc");
        trie.insert("bc");
  
        System.out.println("*****");
        Generator g = new Generator(trie);
        System.out.println(g.firstDegreeMarkov());
        System.out.println(g.secondDegreeMarkov());
    }
    
}
