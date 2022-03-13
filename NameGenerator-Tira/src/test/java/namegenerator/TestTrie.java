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
    
    @Test
    public void mostPopularIndexIsMostPopular() {
        assertEquals(98, trie.getMostPopularIndex(trie.getRoot())); //b
        trie.insert("dddd");
        assertEquals(100, trie.getMostPopularIndex(trie.getRoot()));    //d
    }
    
    @Test
    public void mostPopularIndexIsNegativeWhenNoChildren() {
        TrieNode node = trie.getRoot().getChildren()[100];  //d
        assertEquals(-1, trie.getMostPopularIndex(node));
    }
    
    @Test
    public void idxForEndingIsMostPopWhenMostPopIsEnding() {
        TrieNode node = trie.getRoot().getChildren()[98];   //b
        assertEquals(99, trie.getIdxForEnding(node, 99));   
    }
    
    @Test
    public void idxForEndingIsNegativeWhenNoEndingChild() {
        Trie t = new Trie(256);
        t.insert("anni");
        t.insert("antti");
        TrieNode node = t.getRoot().getChildren()[97].getChildren()[110];   //a-n
        assertEquals(-1, t.getIdxForEnding(node, 116)); //t 
    }  
}
