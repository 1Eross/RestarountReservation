package InfoElements;

public class Node {
    public String key;
    public String dataline;
    public boolean isNotDeleted = true;
    public Node(String key, String dataline){ //isNotDeleted
        this.key = key;
        this.dataline = dataline;
    }
}
