package namegenerator;

import java.util.Arrays;
import java.util.Random;

public class Trie {
    private TrieNode root;
    private int alphabetSize;
    
    public Trie(int alphabetSize) {
        this.alphabetSize = alphabetSize;
        this.root = new TrieNode(this.alphabetSize);   
    }
    
    public TrieNode getRoot() {
        return this.root;
    }
    
    public int getAlphabetSize() {
        return this.alphabetSize;
    }
    
    /**
     * Inserts given word to the tree in all substrings, that are over 2 
     * characters long. Does not accept empty or one char inputs, ignores
     * unicode values under 97 (eg punctuation marks and capital letters.)
     * @param name  
     */
    public void insert(String name) {
        name = name.toLowerCase();
        if (name.length() < 2) {
            return;
        }
        TrieNode node = this.root;
        for (int i = 0; i < name.length(); i++) {
            TrieNode[] children = node.getChildren();
            int index = name.charAt(i);
            if (index < 97) {
                continue;
            }
            if (children[index] == null) {
                node = new TrieNode(this.alphabetSize);
                children[index] = node;
            } else {
                node = children[index];
                node.increasePasses();
            }
        }
        node.setEnd();
        name = name.substring(1);
        insert(name);     
    }
    /**
    * Checks if given word is in the trie, finds also substrings of a word.
    * @param name
    * @return boolean
    */
    public boolean search(String name) {
        TrieNode node = this.root;
        for (int i = 0; i < name.length(); i++) {
            TrieNode[] children = node.getChildren();
            int index = name.charAt(i);
            if (children[index] == null) {
                return false;
            } else {
                node = children[index];
            }
        }
        if (node.getEnd() == false) {
            return false;
        }
        return true;
    }
    
    /**
     * Checks which of the nodes children has most passes
     * @param node
     * @return index of the most popular child
     */
    public int getMostPopularIndex(TrieNode node) {
        int mostPasses = 0;
        int mostPopularIndex = 0;
        int[] populars = new int[100];
        try {
            TrieNode[] children = node.getChildren();
            for (int i = 0; i < this.alphabetSize; i++) {
                if (children[i] != null) {
                    if (children[i].getPasses() >= mostPasses) {
                        mostPasses = children[i].getPasses();
                        mostPopularIndex = i;
                        
                    }  
                }
            }
        } catch (Exception e) {
            System.out.println("Error??: " + e);
            return -1;
        }
        return mostPopularIndex;
    }
    
    /** Finds nodes child, that is marked as ending node. Returns the first 
     * index with such child, or -1 if there is no ending nodes as child
     * 
     * @param node parent node
     * @param popular most popular child node
     * @return index of endnode or -1
     */
    public int getIdxForEnding(TrieNode node, int popular) {
        if (node.getChildren()[popular].getEnd() == true) {
            return popular;
        }
        int idx = -1;
        try {
            TrieNode[] children = node.getChildren();
            for (int i = 0; i < this.alphabetSize; i++) {
                if (children[i] != null) { 
                    if (children[i].getEnd() == true) {
                        idx = i;
                        return i;
                    }
                }   
            }
            return idx;
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
            return -1;
        }
    }
    
     /** Generates name from trie using 
     * k-degree Markov chain. Checks that last letter
     * is marked as last node in trie. If it is not, it tries the previous 
     * node.
     * @param history int[][] with nonempty first row
     * @param k degree of Markov chain, depth of one branch
     * @param n max length for name
     * @param ending should name end with legitimate node
     * @return int[][] of history
     */
    public int[][] generateHistory(int[][] history, int k, int n, boolean ending) {
        int firstInBranch = 0;
        TrieNode node = getRoot();
        
        for (int knownLetters = 1; knownLetters < n; knownLetters++) {
            if (knownLetters - k > 0) {
                firstInBranch = knownLetters - k;
            } 
            for (int j = firstInBranch; j < knownLetters; j++) { //crawl to right place
                node = node.getChildren()[history[j][0]];
            }
            
            int newIndex = getMostPopularIndex(node);
            if (newIndex <= 0) { 
                break; //node has no children, give up   
            }
            if (knownLetters == n - 1 && ending == true) { // last round
                if (node.getChildren()[newIndex].getEnd() == true) { //ending ok, best result
                    history[knownLetters][0] = newIndex;
                    break;
                } else if (ending == true) {
                    history = changeHistory(history, knownLetters);
                    break;
                }
            }
            history[knownLetters][0] = newIndex;
            int endingIdx  = getIdxForEnding(node, newIndex);
            history[knownLetters][1] = endingIdx;
            node = getRoot();
            
        }
        return history;
    }
        public int[][] changeHistory(int[][] history, int knownLetters) {
        for (int x = knownLetters - 1; x >= 0; x--) {
            if (history[x][1] > 0) {
                history[x][0] = history[x][1];
                break;
            }
            history[x][0] = 0;
        }
        return history;
    }
    
}
