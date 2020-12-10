package code;

public class Node<T>{
    private T elem;
    private Node<T> parent;
    private int rank;

    public Node(T elem){
        this.elem = elem;
        this.parent = this;
        this.rank = 0;
    }

    public T getElem() {
        return elem;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> n){
        this.parent = n;
    }

    public void incrementRank(){
        this.rank++;
    }

    public int getRank() {
        return rank;
    }
}
