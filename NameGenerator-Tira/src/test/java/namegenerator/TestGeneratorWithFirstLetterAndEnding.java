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
        assertEquals("seppo", g.generateNameWithLetter(3, 5, "s", false));
        assertEquals("teppo", g.generateNameWithLetter(3, 5, "t", false));
    }
    
    @Test
    public void noNameReturnedWhenNoSuchFirtLetter() {
        assertEquals("No names starting with o.", g.generateNameWithLetter(3, 5, "o", false));
    }
    
    @Test
    public void returnsNothingWhenNoEndingAvailable() {
        assertEquals("", g.generateNameWithLetter(3, 4, "s", true));
    }
    
    @Test
    public void returnsCorrectEnding() {
        t.insert("sep");
        assertEquals("sep", g.generateNameWithLetter(4, 4, "s", true));
        t.insert("sepp");
        assertEquals("sepp", g.generateNameWithLetter(4, 4, "s", true));
    }
   
    
   
}
