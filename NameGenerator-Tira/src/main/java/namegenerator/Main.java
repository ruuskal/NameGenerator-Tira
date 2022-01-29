package namegenerator;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie(256);
  
        trie.insert("mia");
        trie.insert("mira");
        trie.insert("mirkku");
        trie.insert("mirka");
        trie.insert("amy");
        System.out.println("*****");
        
        System.out.println(trie.firstDegreeMarkov());
    }
    
}
