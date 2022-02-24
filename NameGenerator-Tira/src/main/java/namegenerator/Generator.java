package namegenerator;

import java.util.Arrays;

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
    
    public String constructNameDoubleInt(int[][] history) {
        String name = "";
        for (int i = 0; i < history.length; i++) {
            if (history[i][0] == 0) {
                return name;
            }
            name += Character.toString((char) history[i][0]);
        }
        return name;
    }
    
    /** TÄMÄ ON TARKOITUS POISTAA 
     * Generates name from trie using k-degree Markov chain.
     * 
     * @param k degree of Markov chain, must be positive
     * @param n max length of name, between 1 and 25
     * @return generated name as String
     */
    public String generateName(int k, int n) {
        if (k < 0 || n <= 0 || n > 25) {
            return "Bad parameters";
        }
        int[] history = new int[n + 1];
        TrieNode node = this.trie.getRoot();
        int firstInBranch = 0;
        for (int knownLetters = 0; knownLetters < n; knownLetters++) {
            if (knownLetters - k > 0) {  //if we already have enough letters for one branch
                firstInBranch = knownLetters - k;
            } 
            for (int j = firstInBranch; j < knownLetters; j++) { //crawl to right place
                node = node.getChildren()[history[j]];
            }
            int newIndex = this.trie.getMostPopularIndex(node);
            if (newIndex <= 0) {
                newIndex = trie.getNodeWithChildren(node);
                if (newIndex <= 0) {
                    break; //node has no children, give up
                }
            }
            history[knownLetters] = newIndex;
            node = this.trie.getRoot();
        }
        return constructName(history);
    }
 
    
    /** Generates name from trie using 
     * k-degree Markov chain and given first letter. Checks that last letter
     * is marked as last node in trie. If it is not, it tries the previous 
     * node.
     * @param k degree for Markov chain
     * @param n max length for name
     * @param letter first letter for name, if unicode less than 97, start with most popular one 
     * @param ending true, if user want's to implement good ending 
     * @return 
     */
    public String generateNameWithLetter(int k, int n, String letter, boolean ending) {
        if (k < 0 || n <= 0 || n > 25) {
            return "Bad parameters";
        }
        int[][] history = new int[n][2];
        int firstLetter = letter.charAt(0);
        
        if (firstLetter < 97) {
            firstLetter = trie.getMostPopularIndex(trie.getRoot());
        } 
        if (trie.getRoot().getChildren()[firstLetter] == null) {
            return "No names starting with " + letter + ".";
        }
        TrieNode node = this.trie.getRoot();
        history[0][0] = firstLetter;
        history[0][1] = trie.getIdxForEnding(trie.getRoot().getChildren()[firstLetter]);
        int firstInBranch = 0;

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
            int endingIdx  = trie.getIdxForEnding(node);
            history[knownLetters][1] = endingIdx;
            node = this.trie.getRoot();
            
        }
        return constructNameDoubleInt(history);
    }
    
    public int[][] changeHistory(int[][] history, int knownLetters) {

    //System.out.println(Arrays.deepToString(history));
       
        for (int x = knownLetters - 1; x >= 0; x--) {
            if (history[x][1] > 0) {
                history[x][0] = history[x][1];
                break;
            }
            history[x][0] = 0;
        }
        return history;
    }
}