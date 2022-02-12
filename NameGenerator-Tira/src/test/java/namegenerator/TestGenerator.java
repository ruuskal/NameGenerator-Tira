package namegenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGenerator {
    private Trie trie;
    private Generator generator;
    
    public TestGenerator() {
    }
    
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
    public void secondDegreeReturnsAllEndingChars() {
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
}
