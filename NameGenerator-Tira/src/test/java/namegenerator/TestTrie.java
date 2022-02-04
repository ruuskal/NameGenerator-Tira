package namegenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestTrie {
    private Trie trie;
    private Generator generator;
    
    public TestTrie() {
    }
    
    @Before
    public void setUp() {
        this.trie = new Trie(256);
        this.trie.insert("ac");
        this.trie.insert("bbc");
        this.generator = new Generator(trie);
    }
    
    @Test
    public void insertsWholeWordAndItsSubstrings() {
        assertTrue(trie.search("ac"));
        assertTrue(trie.search("bbc"));
        assertTrue(trie.search("bc"));
       
    }
    
    @Test
    public void doesNotInsertMiddleOfWordAsWord() {
        assertFalse(trie.search("bb"));
    }
}
