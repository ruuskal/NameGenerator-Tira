package namegenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGenerator {
    private Trie trie;
    private Generator generator;
    private int[][] history = new int[10][2];
    
   
    @Before
    public void setUp() {
        this.trie = new Trie(256);
        this.trie.insert("seppo");
        this.generator = new Generator(trie);
        history[0][0] = 115;    //s is the first letter
        history[0][1] = -1;     //first letter is not ending node
        
    }
    
    @Test
    public void generateHistoryReturnsNothingWhenNoEndingAvailable() {
        int[][] answer = trie.generateHistory(history, 3, 4, true);
        assertEquals(0, answer[0][0]);
    }
    
    @Test
    public void generateHistoryIgnoresEndingWhenNeeded() {
        int[][] answer = trie.generateHistory(history, 1, 3, false);
        assertEquals(115, answer[0][0]);    //s
        assertEquals(101, answer[1][0]);    //e
        assertEquals(112, answer[2][0]);    //p
        assertEquals(0, answer[3][0]);    
        answer = trie.generateHistory(history, 1, 3, true);
        assertEquals(0, answer[0][0]);    
    }
    
    @Test
    public void generateHistoryDoesntChangeOkEnding() {
        trie.insert("seppi");
        int[][] answer = trie.generateHistory(history, 2, 5, true);
        assertEquals(112, answer[3][0]);    //p
        assertEquals(111, answer[4][0]);    //o
        assertEquals(0, answer[5][0]);    
        
    }
    
    @Test
    public void constructNameStopsWithZero() {
        int[][] t = new int[26][2];
        t[0][0] = 97;   //a
        t[1][0] = 98;   //b
        t[3][0] = 0;
        t[4][0] = 99;   //c
        assertEquals("ab", generator.constructName(t));
    }
    
    @Test
    public void changeHistoryStopsWithFirstPositiveIndex() {
        int[][] t = new int[26][2];
        t[0][0] = 97;   //a
        t[1][0] = 98;   //b
        t[3][0] = 99;   //c 
        t[4][0] = 100;  //d
        t[0][1] = 100;
        t[1][1] = 97;
        t[3][1] = -1;
        t[4][1] = -1;
        int[][] answer = trie.changeHistory(t, 4);
        assertEquals(97, answer[0][0]);
        assertEquals(97, answer[1][0]);
        assertEquals(0, answer[2][0]);
        assertEquals(0, answer[3][0]);
    
    }
  
}
