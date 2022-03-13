package namegenerator;

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
     * @param ending should name end with legitimate node 
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
        int[][] name = trie.generateHistory(history, k, n, ending);
        return constructName(name);
    }
    
    /** Turns array of ints into their corresponding unicode String values.
     * 
     * @param history unicode values for name
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