package namegenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGenerator {
    private Trie trie;
    private Generator generator;
    
    @Before
    public void setUp() {
        this.trie = new Trie(256);
        this.trie.insert("ac");
        this.trie.insert("bbc");
        this.generator = new Generator(trie);
    }
    
    @Test
    public void noNullPointerWhenLastLetterHasNoChildren() {
        assertEquals("bc", generator.generateName(1, 10));
        assertEquals("bc", generator.generateName(2, 10));
    }
    
    @Test
    public void generateNameReturnsAllEndingChars() {
        assertEquals("bc", generator.generateName(2, 10));
        trie.insert("bcfz");
        assertEquals("bcfz", generator.generateName(2, 10));
    }
    
    @Test
    public void generatingFromEmptyTrieDosentBreak() {
        Trie t = new Trie(256);
        Generator g = new Generator(t);
        assertEquals("", g.generateName(1, 10));
    }
    
    @Test
    public void trieWithOneCharDosentBreak() {
        Trie t = new Trie(256);
        t.insert("a");
        Generator g = new Generator(t); 
        assertEquals("", g.generateName(1, 10));
        assertEquals("", g.generateName(5, 10));
    } 
    
    @Test
    public void firstDegreeOnlyAffectedByOnePreviousNode() {
        Trie t = new Trie(256);
        t.insert("llama");
        Generator g = new Generator(t);
        assertEquals("llllllllll", g.generateName(1, 10));
    }
    
    @Test
    public void xDegreesOnlyAffectebByXPreviousNodes() {
        Trie t = new Trie(256);
        t.insert("llama");
        t.insert("lll");
        Generator g = new Generator(t);
        assertEquals("llllllllll", g.generateName(2, 10));
        assertEquals("lll", g.generateName(3, 10));
        assertEquals("lll", g.generateName(30, 10));
        t.insert("llla");
        assertEquals("lllama", g.generateName(3, 10));
        assertEquals("llla", g.generateName(4, 10));
        assertEquals("llla", g.generateName(30, 10));
        
    }
    
    @Test
    public void negativeParametersDenied() {
        assertEquals("Bad parameters", generator.generateName(-1, 1));
        assertEquals("Bad parameters", generator.generateName(1, 30));
        assertEquals("Bad parameters", generator.generateName(1, 0));
    }
    
    @Test
    public void constructNameWorks() {
        int[] t = new int[26];
        t[0] = 97;
        t[1] = 98;
        t[3] = 0;
        t[4] = 99;
        assertEquals("ab", generator.constructName(t));
    }
  
}
