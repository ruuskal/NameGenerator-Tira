package namegenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Loader {
    
    public Trie loadNames(String file) {
        Trie trie = new Trie(256);
        
        try { 
            
        BufferedReader b = new BufferedReader(new InputStreamReader(
                  this.getClass().getResourceAsStream(file)));

           b.lines().forEach(n -> trie.insert(n.toLowerCase()));
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return trie;
    }
}
