package namegenerator;


public class Generator {
    private Trie trie;
    
    public Generator(Trie trie) {
        this.trie = trie;
    }
    
    /** Turns array of ints into their corresponding unicode String values.
     * 
     * @param history int array (unicode values)
     * @return String name
     */
    public String constructName(int[] history) {
        String name = "";
        for (int i = 0; i < history.length; i++) {
            if (history[i] == 0) {
                return name;
            }
            name += Character.toString((char) history[i]);
        }
        return name;
    }
    
    /** Generates name from trie using k-degree Markov chain.
     * 
     * @param k degree of Markov chain, must be positive
     * @param n max length of name, between 1 and 25
     * @return generated name as String
     */
    public String generateName(int k, int n) {
        if (k < 0 || n <= 0 || n > 25) {
            return "Bad parameters";
        }
        int[] history = new int[26];
        TrieNode node = this.trie.getRoot();
        int knownLetters = 0;
        int firstInBranch = 0;

        for (int i = 0; i < n; i++) {
            if (knownLetters != 0) { //if not first looping...
                if (i - k > 0) {  //if we already have enough letter for one branch
                    firstInBranch = i - k;
                } 
                for (int j = firstInBranch; j < knownLetters; j++) { //crawl to right place
                    node = node.getChildren()[history[j]];
                }
            }
            int newIndex = this.trie.getMostPopularIndex(node);
            if (newIndex <= 0) {
                newIndex = trie.getNodeWithChildren(node);
                if (newIndex <= 0) {
                    break; //node has no children, give up
                }
            }
            history[knownLetters] = newIndex;
            knownLetters++;
            node = this.trie.getRoot();
        }
        return constructName(history);
    }
}