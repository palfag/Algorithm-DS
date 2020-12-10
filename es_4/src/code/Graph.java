package code;

import java.util.*;

public class Graph<T,W> {
    private boolean isOriented;
    private int nSize;
    private int eSize;
    private HashMap<T, HashMap<T,W>> graph;

    /**
     * Constructor - Creates a new empty graph
     * @param isOr - technical specification: true to create directed graph / false to create undirected graph
     */
    public Graph(boolean isOr){
        graph = new HashMap<T, HashMap<T, W>>();
        this.isOriented = isOr;
        this.nSize = 0;
        this.eSize = 0;
    }


    /**
     * Check if the graph is oriented
     * @return true if the graph is oriented / false otherwise
     */
    public boolean isOriented(){
        if(this == null) throw new NullPointerException("Graph cannot be null");
        return this.isOriented;
    }


    /** 
     * Adds a new node to the graph 
     * @param elem - node to add to the graph
     * */
    public void addNode(T elem){
        if(elem == null) throw new NullPointerException("Elem cannot be null");
        if(!nodeIsContained(elem)) {
            graph.put(elem, new HashMap<T, W>());
            this.nSize++;
        }
    }


    /**
     * Adds a new edge to the graph
     * @param start - starting node
     * @param dest  - arrival node
     * @param weight - weight of the edge
     * @throws NullPointerException if at least one of the specified objects is null
     */
    public void addEdge(T start, T dest, W weight){
        if(start == null || dest == null) throw new NullPointerException("Node Start or Dest cannot be null");
        if(weight == null) throw new NullPointerException("Weight cannot be null");

        if(graph.containsKey(start) && graph.containsKey(dest)) {
            if (graph.get(start).putIfAbsent(dest, weight) == null)
                this.eSize++;
            if(!isOriented())
                if(graph.get(dest).putIfAbsent(start, weight) == null)
                    this.eSize++;
        }
    }


    /**
     * Check if a node is contained into the graph
     * @param node - node that must be verified
     * @return true: if the node is contained into the graph / false otherwise 
     */
    public boolean nodeIsContained(T node){
        return graph.containsKey(node);
    }


    /**
     * Check if an edge is contained into the graph
     * @param start - starting node
     * @param dest - arrival node
     * @return true: if the node is contained into the graph / false otherwise
     */
    public boolean edgeIsContained(T start, T dest) {
        return (graph.containsKey(start)) && graph.get(start).containsKey(dest);
    }


    /**
     * Deletes a node from the graph specified as parameter
     * @param node - node that must be deleted 
     */
    public void deleteNode(T node){
        if(nodeIsContained(node)){
            graph.forEach((key, arch) ->{
                if(arch.containsKey(node)) {
                    arch.remove(node);
                    this.eSize--;
                }
            } );
            graph.remove(node);
            this.nSize--;
        }
    }


    /**
     * Deletes an edge from the graph specified by start and dest
     * @param start - starting node
     * @param dest - arrival node
     */
    public void deleteEdge(T start, T dest){
        if(edgeIsContained(start, dest)){
            graph.get(start).remove(dest);
            this.eSize--;
            if(!isOriented())
                graph.get(dest).remove(start);
        }
    }


    /**
     * Gets nSize
     * @return number of nodes contained in the graph
     */
    public int getNodeSize(){
        return this.nSize;
    }


    /**
     * Gets eSize
     * @return number of edges contained in the graph
     */
    public int getEdgeSize(){
       return this.eSize / ((isOriented)? 1 : 2);
    }


    /**
     * @return set of nodes from the graph
     */
    public Set<T> getNodes(){
        return new HashSet<T>(graph.keySet());
    }


    /**
     * @return set of edges from the graph
     */
    public Set<Edge<T,W>> getEdges(){
        Set<Edge<T,W>> edgeSet = new HashSet<>();

        for (Map.Entry<T, HashMap<T,W>> temp: graph.entrySet()) {
            T start = temp.getKey();
            for (T dest: temp.getValue().keySet()) {
                W weight = temp.getValue().get(dest);
                Edge<T,W> e1 = new Edge<>(start,dest,weight);

                if(!isOriented) {
                    Edge<T,W> e2 = new Edge<>(dest,start,weight);
                    if(!edgeSet.contains(e1) && !edgeSet.contains(e2))
                        edgeSet.add(e1);
                }else{
                    edgeSet.add(e1);
                }
            }
        }
        return edgeSet;
    }


    /**
     * Gets node's adjacency list 
     * @param node - node to retrieve its adjacency list
     * @return adjacent nodes of a specified node
     */
    public Set<T> getAdjNodes(T node){
        return new HashSet<T>(graph.get(node).keySet());
    }


    /**
     * Gets edge's label   
     * @param start - starting node
     * @param dest - arrival node
     * @return label associated with a couple of nodes
     */
    public W getLabel(T start, T dest){
        if(graph.containsKey(start))
            if(graph.get(start).containsKey(dest))
                return graph.get(start).get(dest);
        return null;
    }
}
