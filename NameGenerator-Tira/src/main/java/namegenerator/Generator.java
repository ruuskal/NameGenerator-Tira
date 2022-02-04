package namegenerator;


public class Generator {
    private Trie trie;
    private TrieNode[] rootChildren;
    
    public Generator(Trie trie) {
        this.trie = trie;
        this.rootChildren = this.trie.getRoot().getChildren();
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
     * If two nodes are equally popular, select the last one. Name can be 2-10 chars long.
     * @return 
     */
    public String firstDegreeMarkov() {
        String suggestion = "";
        TrieNode node = this.trie.getRoot();
        
        for (int j = 0; j < 10; j++) {
            int mostPopIndex = getMostPopularIndex(node);
            if (mostPopIndex != 0) { //ei laiteta tyhjää merkkiä nimiehdotukseen
                suggestion += Character.toString((char) mostPopIndex);
            }
            if (rootChildren[mostPopIndex] == null) {
                return suggestion;
            }
            TrieNode nextNode = rootChildren[mostPopIndex];
            node = nextNode;
        }
        
        return suggestion;
    }
    /**
     * Selects node's most popular child, until there are no
     * more nodes to select or name is ten characters long.
     * If two nodes are equally popular, select the last one. Generated name must
     * be at least 2 chars long.
     * @return String name
     */
    public String secondDegreeMarkov() {
        String suggestion = "";
        int[] name = getBeginningForSecondDegree();
        if (name[1] == 0) {
            return "Trie does not have long enough names.";
        }
        TrieNode nodeOne = rootChildren[name[0]];
        TrieNode nodeTwo = nodeOne.getChildren()[name[1]];
        suggestion += Character.toString((char) name[0]);
        suggestion += Character.toString((char) name[1]);

        for (int j = 2; j < 10; j++) {
            int nextPopIndex = getMostPopularIndex(nodeTwo); 
            if (nextPopIndex == 0) {
                return suggestion; //can be 0, let's not but empty values to suggestion
            }
            if (getMostPopularIndex(rootChildren[name[j - 1]].getChildren()[nextPopIndex]) == 0) {
                suggestion += Character.toString((char) nextPopIndex);
                return suggestion; //in case of abc, we get also c in the name, not just ab
            }
            name[j] = nextPopIndex;
            nodeOne = rootChildren[name[j - 1]];
            nodeTwo = nodeOne.getChildren()[nextPopIndex];
            suggestion += Character.toString((char) nextPopIndex);
        }
        return suggestion;
    }
    /**
     * Selects root's most popular child and it's most popular child to begin
     * second degree Markov chain.
     * @return int array with indexes to first and second nodes for generating name.
     * Zeroes indicate, that trie does not have enough names to generate a new one.
     */
    
    public int[] getBeginningForSecondDegree() {
        int[] name = new int[10]; //max name lenght
        name[0] = getMostPopularIndex(this.trie.getRoot());
        if (name[0] == 0) {
            return name;
        }
        TrieNode nodeOne = rootChildren[name[0]];
        name[1] = getMostPopularIndex(nodeOne);
        if (name[1] == 0) {
            return name;
        }
        return name; 
    }
}
