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
     * Right now, method selects the most popular node
     * until name is ten characters long or there are no node to continue to. 
     * If two nodes are equaly popular, select the last one. Name can be only two chars
     * long.
     * @return 
     */
    public String firstDegreeMarkov() {
        String suggestion = "";
        TrieNode[] rootChildren = this.trie.getRoot().getChildren();
        TrieNode node = this.trie.getRoot();
        
        for (int j = 0; j < 10; j++) {
            int mostPopIndex = getMostPopularIndex(node);
            suggestion += Character.toString((char) mostPopIndex);
            if (rootChildren[mostPopIndex] == null) {
                return suggestion;
            }
            TrieNode nextNode = rootChildren[mostPopIndex];
            node = nextNode;
        }
        
        return suggestion;
    }
}
