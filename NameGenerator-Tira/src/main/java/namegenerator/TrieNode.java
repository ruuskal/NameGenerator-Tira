package namegenerator;

public class TrieNode {
    private TrieNode[] children;
    private boolean isEnd;
    private int passes;
    
    public TrieNode(int alphabetSize) {
        this.children = new TrieNode[alphabetSize];
        this.isEnd = false;
        this.passes = 1;
    }
    
    public TrieNode[] getChildren() {
        return this.children;
    }
    
    public void setEnd() {
        this.isEnd = true;
    }
    
    public boolean getEnd() {
        return this.isEnd;
    }
    
    public int getPasses() {
        return this.passes;
    }
    
    public void increasePasses() {
        this.passes++;
    }
    
    
}
