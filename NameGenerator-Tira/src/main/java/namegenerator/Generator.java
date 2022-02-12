package namegenerator;


public class Generator {
    private Trie trie;
    
    public Generator(Trie trie) {
        this.trie = trie;
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
        String suggestion = "";
        int[] history = new int[k + 1];
        TrieNode node = this.trie.getRoot();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (history[j] == 0) {
                    break;
                } else {
                    node = node.getChildren()[history[j]];
                }
            }
            int newIndex = this.trie.getMostPopularIndex(node);
            if (newIndex == 0 || newIndex == -1) {
                break;
            }
            suggestion += Character.toString((char) newIndex);
            if (i >= k) {
                history[k] = newIndex;
                for (int j = 0; j < k; j++) {
                    history[j] = history[j + 1];
                }
            } else {
                history[i] = newIndex;
            }
            node = this.trie.getRoot();
        }
   
        return suggestion;
    }
}