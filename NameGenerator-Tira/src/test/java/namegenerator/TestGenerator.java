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
    public void mostPopularIndexIsMostPopular() {
        assertEquals(98, generator.getMostPopularIndex(trie.getRoot()));
        trie.insert("dddd");
        assertEquals(100, generator.getMostPopularIndex(trie.getRoot()));
    }
    
    @Test
    public void noNullPointerWhenLastLetterHasNoChildren() {
        assertEquals("bc", generator.firstDegreeMarkov());
        assertEquals("bc", generator.secondDegreeMarkov());
    }
    
    @Test
    public void secondDegreeReturnsAllEndingChars() {
        assertEquals("bc", generator.secondDegreeMarkov());
        trie.insert("bcfz");
        assertEquals("bcfz", generator.secondDegreeMarkov()); 
    }
    
    @Test
    public void generatingFromEmptyTrieWorks() {
        Trie t = new Trie(256);
        Generator g = new Generator(t);
        assertEquals("Trie does not have long enough names.", g.secondDegreeMarkov());
        assertEquals("", g.firstDegreeMarkov());
    }
    
    @Test
    public void trieWithOneCharWorks() {
        Trie t = new Trie(256);
        t.insert("a");
        Generator g = new Generator(t); 
        assertEquals("a", g.firstDegreeMarkov());
        assertEquals("Trie does not have long enough names.", g.secondDegreeMarkov());
    }
    
    @Test
    public void beginningForSecondDegreeWorkswithEmptyTrie() {
        Trie t = new Trie(256);
        Generator g = new Generator(t);
        int[] empty = g.getBeginningForSecondDegree();
        assertEquals(0, empty[0]);
        assertEquals(0, empty[1]);
    }
    
    @Test
    public void beginningForSecondDegreeWorkswithNotEmptyTrie() {
        int[] name = generator.getBeginningForSecondDegree();
        assertEquals(98, name[0]);
        assertEquals(99, name[1]);
    }
    
}
