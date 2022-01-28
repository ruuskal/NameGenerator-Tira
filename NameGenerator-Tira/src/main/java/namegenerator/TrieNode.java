package namegenerator;

public class TrieNode {
    private TrieNode[] children;
    private boolean isEnd;
    
    public TrieNode(int alphabetSize) {
        this.children = new TrieNode[alphabetSize];
        this.isEnd = false;
    }
    
    public TrieNode[] getChildren() {
        return this.children;
    }
    
    public void setEnd() {
        this.isEnd = true;
    }
    
    public boolean getIsEnd() {
        return this.isEnd;
    }
    
}
