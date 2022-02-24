package namegenerator;

import java.util.Arrays;

public class Generator {
    private Trie trie;
    
    public Generator(Trie trie) {
        this.trie = trie;
    }

    /** Checks parameters and generates the beginning for name. Call's generateHistory
     * and then constructName to make the name.
     * @param k degree for Markov chain
     * @param n max length for name
     * @param letter first letter for name, if unicode less than 97, start with most popular one 
     * @param ending true if name should end with legitimate node 
     * @return String name
     */
    public String generateName(int k, int n, String letter, boolean ending) {
        if (k < 0 || n <= 0 || n > 25) {
            return "Bad parameters";
        }
        int[][] history = new int[n][2];
        int letterCode = letter.charAt(0);
        
        if (letterCode < 97) {
            letterCode = trie.getMostPopularIndex(trie.getRoot());
        } 
        if (trie.getRoot().getChildren()[letterCode] == null && ending == true) {
            return "No names starting with " + letter + ".";
        } else if (trie.getRoot().getChildren()[letterCode] == null) {
            return "Not enogh names in trie";
        }
        
        history[0][0] = letterCode;
        history[0][1] = trie.getIdxForEnding(trie.getRoot(), letterCode);
        
        int[][] name = generateHistory(history, k, n, ending);
        return constructName(name);
    }
    
    
    /** Generates name from trie using 
     * k-degree Markov chain. Checks that last letter
     * is marked as last node in trie. If it is not, it tries the previous 
     * node.
     * @param history int[][] with nonempty first row
     * @param k degree of Markov chain, depth of one branch
     * @param n max length for name
     * @param ending true if name should end with legitimate node
     * @return int[][] of history
     */
    public int[][] generateHistory(int[][] history, int k, int n, boolean ending) {
        int firstInBranch = 0;
        TrieNode node = this.trie.getRoot();
        
        for (int knownLetters = 1; knownLetters < n; knownLetters++) {
            if (knownLetters - k > 0) {
                firstInBranch = knownLetters - k;
            } 
            for (int j = firstInBranch; j < knownLetters; j++) { //crawl to right place
                node = node.getChildren()[history[j][0]];
            }
            
            int newIndex = this.trie.getMostPopularIndex(node);
            if (newIndex <= 0) { 
                break; //node has no children, give up   
            }
            if (knownLetters == n - 1 && ending == true) { // last round
                if (node.getChildren()[newIndex].getEnd() == true) { //ending ok, best result
                    history[knownLetters][0] = newIndex;
                    break;
                } else {
                    history = changeHistory(history, knownLetters);
                    break;
                }
            }
            history[knownLetters][0] = newIndex;
            int endingIdx  = trie.getIdxForEnding(node, newIndex);
            history[knownLetters][1] = endingIdx;
            node = this.trie.getRoot();
            
        }
        return history;
    }
    
    
    public int[][] changeHistory(int[][] history, int knownLetters) {

  //  System.out.println(Arrays.deepToString(history));
       
        for (int x = knownLetters - 1; x >= 0; x--) {
            if (history[x][1] > 0) {
                history[x][0] = history[x][1];
                break;
            }
            history[x][0] = 0;
        }
        return history;
    }
    
    
    /** Turns array of ints into their corresponding unicode String values.
     * 
     * @param history int array (unicode values)
     * @return String name
     */
    public String constructName(int[][] history) {
        String name = "";
        for (int i = 0; i < history.length; i++) {
            if (history[i][0] == 0) {
                return name;
            }
            name += Character.toString((char) history[i][0]);
        }
        return name;
    }
}