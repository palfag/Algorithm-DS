package code;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class UnionFindSet<T>{

    HashMap <T, Node<T>> map;

    public UnionFindSet(){
        map = new HashMap <T, Node<T>>();
    }

    public UnionFindSet(Set<T> c){
        map = new HashMap <T, Node<T>>();

        Iterator<T> iterator = c.iterator();
        while(iterator.hasNext()){
            T elem = iterator.next();
            makeSet(elem);
        }
    }

    public HashMap<T, Node<T>> getMap(){
        return map;
    }


    public void makeSet (T elem){
        Node<T> n = new Node<T>(elem);
        map.put(elem, n);
    }


    public Node<T> find(T elem){
        Node<T> n = map.get(elem);
        Node <T> parent = n.getParent();
        if(parent == n){
            return parent;
        }else{
            n.setParent(find(n.getParent().getElem())); // setting the new parent --- path compression!!
            return n.getParent();
        }
    }

    public T union(T a, T b){
        Node<T> rappA = find(a);
        Node<T> rappB = find(b);

        if(rappA.getRank() > rappB.getRank()){
            rappB.setParent(rappA);
            return rappA.getElem();
        }
        else if (rappA.getRank() < rappB.getRank()){
            rappA.setParent(rappB);
            return rappB.getElem();
        }
        else{
            /* rappA.getRank() == rappB.getRank()
               In this case it doesn't matter who becomes parent of whom */
            rappB.setParent(rappA);
            rappA.incrementRank();
            return rappA.getElem();
        }
    }
}
