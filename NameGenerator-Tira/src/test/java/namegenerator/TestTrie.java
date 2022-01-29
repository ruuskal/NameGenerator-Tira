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
        this.trie.insert("ac");
        this.trie.insert("bbc");
    }
    
    @Test
    public void insertsWholeWordAndItsSubstrings() {
        assertTrue(this.trie.search("ac"));
        assertTrue(this.trie.search("bbc"));
        assertTrue(this.trie.search("bc"));
       
    }
    
    @Test
    public void doesNotInsertMiddleOfWordAsWord() {
        assertFalse(trie.search("bb"));
    }
    
    @Test
    public void mostPopularIndexIsMostPopular() {
        assertEquals(98, this.trie.getMostPopularIndex(this.trie.getRoot()));
        this.trie.insert("dddd");
        assertEquals(100, this.trie.getMostPopularIndex(this.trie.getRoot()));
    }
    
    @Test
    public void noNullPointerWhenLastLetterHasNoChildren() {
        assertEquals("ac", trie.firstDegreeMarkov());
    }
}
