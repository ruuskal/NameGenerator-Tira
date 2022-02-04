package namegenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestTrie {
    private Trie trie;
    
    @Before
    public void setUp() {
        this.trie = new Trie(256);
        this.trie.insert("ac");
        this.trie.insert("bbc");
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
