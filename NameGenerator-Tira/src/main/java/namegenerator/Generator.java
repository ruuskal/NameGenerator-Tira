package namegenerator;


public class Generator {
    private Trie trie;
    
    public Generator(Trie trie) {
        this.trie = trie;
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
        for (int i = 0; i < this.trie.getAlphabetSize(); i++) {
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
        TrieNode node = this.trie.getRoot();
        TrieNode[] children = node.getChildren();
        for (int i = 0; i < this.trie.getAlphabetSize(); i++) {
            if (children[i] != null) {
                suggestion = Character.toString((char) i);
                node = children[i];
                break;
            }
        }
        
        for (int j = 0; j < 10; j++) {
            int mostPopIndex = getMostPopularIndex(node);
            suggestion += Character.toString((char) mostPopIndex);
            if (this.trie.getRoot().getChildren()[mostPopIndex] == null) {
                return suggestion;
            }
            TrieNode nextNode = this.trie.getRoot().getChildren()[mostPopIndex];
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
