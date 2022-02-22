package namegenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGeneratorWithFirstLetterAndEnding {

    private Trie t;
    private Generator g;
    
    @Before
    public void setUp() {
        this.t = new Trie(256);
        this.t.insert("seppo");
        this.t.insert("teppo");
        this.g = new Generator(t);
    }
    
    @Test
    public void selectsCorrectFirstLetter() {
        assertEquals("seppo", g.generateNameWithFirstLetter(3, 5, "s"));
        assertEquals("teppo", g.generateNameWithFirstLetter(3, 5, "t"));
    }
    
    @Test
    public void noNameReturnedWhenNoSuchFirtLetter() {
        assertEquals("No names starting with o.", g.generateNameWithFirstLetter(3, 5, "o"));
    }
    
    @Test
    public void returnsNothingWhenNoEndingAvailable() {
        assertEquals("", g.generateNaeWithGoodEnding(3, 4, "s"));
    }
    
    @Test
    public void returnsCorrectEnding() {
        t.insert("sep");
        assertEquals("sep", g.generateNaeWithGoodEnding(4, 4, "s"));
        t.insert("sepp");
        assertEquals("sepp", g.generateNaeWithGoodEnding(4, 4, "s"));
    }
    
   
}
