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
    /**
     * Selects most popular node and it's most popular child, until there are no
     * more node to select or name is ten characters long.
     * @return String name
     */
    public String secondDegreeMarkov() { // ei testejä vielä
        String suggestion = "";
        int[] name = new int[12];
        TrieNode[] rootChildren = this.trie.getRoot().getChildren();
        int firstPop = getMostPopularIndex(this.trie.getRoot());
        TrieNode nodeOne = rootChildren[firstPop];
        name[0] = firstPop;
        
        int secondPop = getMostPopularIndex(nodeOne);
        TrieNode nodeTwo = nodeOne.getChildren()[secondPop];
        name[1] = secondPop;

        for (int j = 0; j < 10; j++) {
            int thirdPop = getMostPopularIndex(nodeTwo);
            name[j + 2] = thirdPop;
            nodeOne = rootChildren[secondPop];
            nodeTwo = nodeOne.getChildren()[thirdPop];
            secondPop = thirdPop;
            if (rootChildren[secondPop] == null) {
                break;
            }
        }
        for (int i : name) {
            suggestion += Character.toString((char) i);
        }
        
        return suggestion;
    }
}
