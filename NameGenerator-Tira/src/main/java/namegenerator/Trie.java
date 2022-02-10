package namegenerator;

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
     * Inserts given word to the tree in all substrings, that are 4 to 2 
     * characters long. Does not accept empty or one char inputs.
     * 
     * @param name  
     */
    public void insert(String name) {
        if (name.length() < 2) {
            return;
        }
        TrieNode node = this.root;
        for (int i = 0; i < name.length(); i++) {
            TrieNode[] children = node.getChildren();
            int index = name.charAt(i);
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
        TrieNode[] children = node.getChildren();
        for (int i = 0; i < this.alphabetSize; i++) {
            if (children[i] != null) {
                if (children[i].getPasses() >= mostPasses) {
                    mostPasses = children[i].getPasses();
                    mostPopularIndex = i;
                }
            }
        }
        return mostPopularIndex;
    }
}
