package namegenerator;

import namegenerator.Trie;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestTrie {
    private Trie trie;
    
    public TestTrie() {
    }
    
    @Before
    public void setUp() {
        this.trie = new Trie(256);
    }
    
    @Test
    public void insertsWholeWordAndItsSubstrings() {
        this.trie.insert("hello");
        assertTrue(this.trie.search("hello"));
        assertTrue(this.trie.search("ello"));
        assertTrue(this.trie.search("llo"));
        assertTrue(this.trie.search("lo"));
       
    }
    
    @Test
    public void doesNotInsertMiddleOfWordAsWord() {
        this.trie.insert("hello");
        assertFalse(trie.search("ll"));
    }

}
