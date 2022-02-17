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
        assertEquals(98, trie.getMostPopularIndex(trie.getRoot()));
        trie.insert("dddd");
        assertEquals(100, trie.getMostPopularIndex(trie.getRoot()));
    }
    
    @Test
    public void mostPopularIndexIsNegativeWhenNoChildren() {
        TrieNode node = trie.getRoot().getChildren()[100];
        assertEquals(-1, trie.getMostPopularIndex(node));
    }
    
    @Test
    public void findNodeWithChildrenReturnsNegativeWhenNoChildren() {
        TrieNode node = trie.getRoot().getChildren()[97].getChildren()[99];
        assertEquals(-1, trie.getNodeWithChildren(node));
    }
    
    @Test
    public void findTrieWithChildrenReturnsTrieWithChildren() {
        Trie t = new Trie(256);
        t.insert("bbc");
        t.insert("bbaa");
        TrieNode node = t.getRoot().getChildren()[98].getChildren()[98];
        assertEquals(97, t.getNodeWithChildren(node));
    }
}
