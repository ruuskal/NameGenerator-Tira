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
     * Inserts given word to the tree in all substrings, that
     * are longer than one character.
     * 
     * @param word  
     */
    public void insert(String word) {
        TrieNode node = this.root;
        
        for (int i = 0; i < word.length(); i++) {
            TrieNode[] children = node.getChildren();
            int index = word.charAt(i);
            if (children[index] == null) {
                node = new TrieNode(this.alphabetSize);
                children[index] = node;
            } else {
                node = children[index];
                node.increasePasses();
            }
        }
        node.setEnd();
        word = word.substring(1);
        if (word.length() > 1) {
            insert(word);
        }       
    }
    /**
    * Checks if given word is in the trie, finds also substrings of a word.
    * @param word
    * @return boolean
    */
    public boolean search(String word) {
        TrieNode node = this.root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode[] children = node.getChildren();
            int index = word.charAt(i);
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
     * Checks witch of the nodes children has most passes
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
    /**
     * Right now, method selects first nonempty node, then the most popular one
     * until node is flagged as last one or name is nine characters long. 
     * @return 
     */
    public String firstDegreeMarkov() {
        String suggestion = "";
        TrieNode node = this.root;
        TrieNode[] children = node.getChildren();
        for (int i = 0; i < this.alphabetSize; i++) {
            if (children[i] != null) {
                suggestion = Character.toString((char) i);
                node = children[i];
                break;
            }
        }
        
        for (int j = 0; j < 10; j++) {
            int mostPopIndex = getMostPopularIndex(node);
            suggestion += Character.toString((char) mostPopIndex);
            if (this.root.getChildren()[mostPopIndex] == null) {
                return suggestion;
            }
            TrieNode nextNode = this.root.getChildren()[mostPopIndex];
            int nextPopIndex = getMostPopularIndex(nextNode);
            if (nextNode.getChildren()[nextPopIndex].getEnd()) {
                suggestion  += Character.toString((char) nextPopIndex);
                return suggestion;
            }
            node = nextNode;
        }
        
        return suggestion;
    }
}
