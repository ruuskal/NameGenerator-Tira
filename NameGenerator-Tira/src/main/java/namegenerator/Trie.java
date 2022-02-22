package namegenerator;

import java.util.Arrays;

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
            System.out.println("Error: " + e);
            return -1;
        }
        return mostPopularIndex;
    }
    
    /**Tires to find child with at least one child.
     * 
     * @param node
     * @return index for the node with child
     */
    public int getNodeWithChildren(TrieNode node) {
        int idx = -1;
        try {
            TrieNode[] children = node.getChildren();
            for (int i = 0; i < this.alphabetSize; i++) {
                if (children[i] != null) { 
                    if (children[i].getChildren() != null) {
                        return i;
                    }
                }   
            }
            return idx;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return -1;
        }
    }
    /** Finds nodes child, that is marked as ending node. Returns the first 
     * index with such child, or -1 if there is no ending nodes as child
     * 
     * @param node
     * @return index of endnode or -1
     */
    public int getIdxForEnding(TrieNode node) {
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
    
}
