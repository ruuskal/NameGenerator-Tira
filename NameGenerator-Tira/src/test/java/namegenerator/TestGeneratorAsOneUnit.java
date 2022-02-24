package namegenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGeneratorAsOneUnit {

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
    public void negativeParametersDenied() {
        assertEquals("Bad parameters", generator.generateName(-1, 1, ".", false));
        assertEquals("Bad parameters", generator.generateName(1, 30, ".", false));
        assertEquals("Bad parameters", generator.generateName(1, 0, ".", false));
    }
    
    @Test
    public void generatingFromEmptyTrieDosentBreak() {
        Trie t = new Trie(256);
        Generator g = new Generator(t);
        assertEquals("Not enogh names in trie", g.generateName(1, 10, ".", false));
    }
    
    @Test
    public void trieWithOneCharDosentBreak() {
        Trie t = new Trie(256);
        t.insert("a");
        Generator g = new Generator(t); 
        assertEquals("Not enogh names in trie", g.generateName(1, 10, ".", false));
        assertEquals("No names starting with a.", g.generateName(5, 10, "a", true));
    } 
    
    @Test
    public void noNullPointerWhenLastLetterHasNoChildren() {
        assertEquals("bc", generator.generateName(1, 10, ".", false));
        assertEquals("bc", generator.generateName(2, 10, ".", false));
    }
    
    @Test
    public void generateNameReturnsAllEndingChars() {
        assertEquals("bc", generator.generateName(2, 10, ".", false));
        trie.insert("bcfz");
        assertEquals("bcfz", generator.generateName(2, 10, ".", false));
    }
    @Test
    public void firstDegreeOnlyAffectedByOnePreviousNode() {
        Trie t = new Trie(256);
        t.insert("llama");
        Generator g = new Generator(t);
        assertEquals("llllllllll", g.generateName(1, 10, ".", false));
    }
    
    @Test
    public void xDegreesOnlyAffectebByXPreviousNodes() {
        Trie t = new Trie(256);
        t.insert("llama");
        t.insert("lll");
        Generator g = new Generator(t);
        assertEquals("llllllllll", g.generateName(2, 10, ".", false));
        assertEquals("lll", g.generateName(3, 10, ".", false));
        assertEquals("lll", g.generateName(30, 10, ".", false));
        t.insert("llla");
        assertEquals("lllama", g.generateName(3, 10, ".", false));
        assertEquals("llla", g.generateName(4, 10, ".", false));
        assertEquals("llla", g.generateName(30, 10, ".", false));
    }
    
    @Test
    public void selectsCorrectFirstLetter() {
        assertEquals("ac", generator.generateName(3, 5, "a", true));
        assertEquals("bc", generator.generateName(3, 5, "b", false));
    }
    
    @Test
    public void noNameReturnedWhenNoSuchFirtLetter() {
        assertEquals("No names starting with o.", generator.generateName(3, 5, "o", true));
    }
   
}
