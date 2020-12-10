package code;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Kruskal{

    public static String path = "src/code/italian_dist_graph.csv";

    private static Graph<String,Float> kruskalAlgorithm(Graph<String,Float> graph){
        Graph<String,Float> mst = new Graph<>(false);
        UnionFindSet<String> union = new UnionFindSet<>(graph.getNodes());

        ArrayList<Edge<String,Float>> edgeArray = new ArrayList<>(graph.getEdges());
        edgeArray.sort((e1,e2) -> Float.compare(e1.getWeight(),e2.getWeight()));

        float total = 0;
        for(int i=0; i<edgeArray.size(); i++){
            String start = edgeArray.get(i).getStart();
            String dest = edgeArray.get(i).getDest();
            Float weight = edgeArray.get(i).getWeight();

            if(union.find(start) != union.find(dest)){
                mst.addNode(start);
                mst.addNode(dest);
                mst.addEdge(start,dest,weight);
                union.union(start,dest);
                total += weight;
            }
        }
        total/=1000; //meters in Km
        edgeArray.clear();
        System.out.println("\n********KRUSKAL********\nMinimal Spanning Tree \nWeight: " + total +" Km");
        return mst;
    }

    public static void main(String[] args){
        //String path = "src/code/italian_dist_graph.csv";
        String line="";
        String[] edge;
        float weight;
        Graph<String,Float> graph = new Graph<>(false);
        try {
            File f = new File(path);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                line = sc.nextLine();
                edge = line.split(","); //edge[0]->START    edge[1]->DEST   edge[2]->WEIGHT
                weight = Float.parseFloat(edge[2]);
                graph.addNode(edge[0]);
                graph.addNode(edge[1]);
                graph.addEdge(edge[0],edge[1],weight);
            }
            sc.close();

        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch (NullPointerException ex){
            System.err.println(ex.getMessage());
        }catch (NumberFormatException exc){
            System.err.println(exc.getMessage());
        }

        Graph<String,Float> mst = new Graph<>(false);
        mst = kruskalAlgorithm(graph);
        System.out.println("#Nodes: " + mst.getNodeSize() + "\n#Edges: " + mst.getEdgeSize() +"\n**********************");
    }
}
