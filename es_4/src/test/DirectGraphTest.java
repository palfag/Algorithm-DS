package test;

import code.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

public class DirectGraphTest {
    Graph<String, Integer> direct;
    Set<Edge<String,Integer>> s1;
    Set<Edge<String,Integer>> s2;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setDirectGraph(){
        direct = new Graph<String,Integer>(true);
        direct.addNode("A");
        direct.addNode("B");
        direct.addNode("C");
        direct.addNode("D");
        direct.addNode("E");

        direct.addEdge("A", "B", 5);
        direct.addEdge("C","B",2);
        direct.addEdge("D", "A",3);
        direct.addEdge("B", "D",4);
        direct.addEdge("B", "A",1);
        direct.addEdge("C", "D",8);
        direct.addEdge("D", "E",10);

        s1 = new HashSet<>();
        s1.add(new Edge<>("D","E",10));
        s1.add(new Edge<>("C","D",8));
        s1.add(new Edge<>("B","A",1));
        s1.add(new Edge<>("B","D",4));
        s1.add(new Edge<>("D", "A",3));
        s1.add(new Edge<>("C","B",2));
        s1.add(new Edge<>("A", "B", 5));

        s2 = new HashSet<>();
        s2.add(new Edge<>("E","D",10));
        s2.add(new Edge<>("D","C",10));
        s2.add(new Edge<>("A","E",10)); //
        s2.add(new Edge<>("D","B",10));
        s2.add(new Edge<>("A","D",10));
        s2.add(new Edge<>("B","C",10));
        s2.add(new Edge<>("B","E",10)); //



    }
    @Test
    public void isOriented() {
        assertTrue(direct.isOriented());
    }

    @Test
    public void isOrientedNull(){
        Graph<String,Integer> g = null;
        exception.expect(NullPointerException.class);
        g.isOriented();
    }

    /** Adding new Node --> size of Node increments (+1) */
    @Test
    public void addNode() {
        assertEquals(5,direct.getNodeSize());
        direct.addNode("F");
        assertEquals(6,direct.getNodeSize());
    }

    @Test
    public void addNullNode(){
        exception.expect(NullPointerException.class);
        direct.addNode(null);
    }

    /** Trying to add an existing Node --> size of Nodes does not change */
    @Test
    public void addExistingNode() {
        assertEquals(5, direct.getNodeSize());
        direct.addNode("A");
        assertEquals(5, direct.getNodeSize());
    }

    /** Adding new Edge --> size of Edges increments (+1) */
    @Test
    public void addEdge() {
        assertEquals(7, direct.getEdgeSize());
        direct.addEdge("E","D",10);
        assertEquals(8, direct.getEdgeSize());
    }

    @Test
    public void addNullEdge(){
        exception.expect(NullPointerException.class);
        direct.addEdge(null,null,null);
    }

    /**
     * Trying to add an existing Edge --> size of Edges does not change (because it is an Undirect Graph) */
    @Test
    public void addExistingEdge() {
        assertEquals(7, direct.getEdgeSize());
        direct.addEdge("A","B",90); // Trying to insert a edge with different weight
        assertEquals(7, direct.getEdgeSize()); // it should be ignored
    }

    @Test
    public void nodeIsContained() {
        assertTrue(direct.nodeIsContained("A"));
        assertTrue(direct.nodeIsContained("B"));
        assertTrue(direct.nodeIsContained("C"));
        assertTrue(direct.nodeIsContained("D"));
        assertTrue(direct.nodeIsContained("E"));

        assertFalse(direct.nodeIsContained("X"));
        assertFalse(direct.nodeIsContained("Y"));
        assertFalse(direct.nodeIsContained("Z"));

        assertFalse(direct.nodeIsContained(null));
    }

    @Test
    public void edgeIsContained() {

        direct.addEdge("A", "B", 5);
        direct.addEdge("C","B",2);
        direct.addEdge("D", "A",3);
        direct.addEdge("B", "D",4);
        direct.addEdge("B", "A",1);
        direct.addEdge("C", "D",8);
        direct.addEdge("D", "E",10);

        assertTrue(direct.edgeIsContained("A","B"));
        assertTrue(direct.edgeIsContained("C","B"));
        assertTrue(direct.edgeIsContained("D","A"));
        assertTrue(direct.edgeIsContained("B","D"));
        assertTrue(direct.edgeIsContained("B","A"));
        assertTrue(direct.edgeIsContained("C","D"));
        assertTrue(direct.edgeIsContained("D","E"));

        // Reverse edges -- if(!direct.isOriented()) --> it should be assertTrue();
        assertFalse(direct.edgeIsContained("E","D"));
        assertFalse(direct.edgeIsContained("D","C"));
        assertFalse(direct.edgeIsContained("D","B"));
        assertFalse(direct.edgeIsContained("B","C"));
        assertFalse(direct.edgeIsContained("A","D"));

        // Inexistent edges
        assertFalse(direct.edgeIsContained("A","C"));
        assertFalse(direct.edgeIsContained("B","E"));

        // null start && dest edge
        assertFalse(direct.edgeIsContained(null,null));

    }

    @Test
    public void deleteNode() {
        assertTrue(direct.nodeIsContained("A"));
        direct.deleteNode("A");
        assertFalse(direct.nodeIsContained("A"));
    }

    /** Cascade removal : DeleteNode("A") ----> it removes all node where A is dest*/
    @Test
    public void deleteEdgeFromNode() {
        assertTrue(direct.edgeIsContained("D","A"));
        assertTrue(direct.edgeIsContained("B","A"));

        direct.deleteNode("A");

        assertFalse(direct.edgeIsContained("D","A"));
        assertFalse(direct.edgeIsContained("B","A"));
    }

    @Test
    public void deleteEdge() {
        assertTrue(direct.edgeIsContained("D","E"));
        direct.deleteEdge("D", "E");
        assertFalse(direct.edgeIsContained("D","E"));
    }

    @Test
    public void getNodeSize() {
        assertEquals(5, direct.getNodeSize());
    }

    @Test
    public void getEdgeSize() {
        assertEquals(7, direct.getEdgeSize());
    }

    @Test
    public void getNodes() {
        Set<String> s = new HashSet<>();
        s.add("E");
        s.add("D");
        s.add("C");
        s.add("B");
        s.add("A");
        assertEquals(s,direct.getNodes());
    }

    @Test
    public void getEdges() {
        assertEquals(s1.size(),direct.getEdgeSize());

        Iterator <Edge<String,Integer>> iterator = direct.getEdges().iterator();
        while (iterator.hasNext()){
            Edge<String,Integer> elem = iterator.next();
            assertTrue(s1.contains(elem));
        }
    }

    @Test
    public void getEdgesReverseAndIn() {
        assertEquals(s2.size(),direct.getEdgeSize());

        Iterator <Edge<String,Integer>> iterator = direct.getEdges().iterator();
        while (iterator.hasNext()){
            Edge<String,Integer> elem = iterator.next();
            assertFalse(s2.contains(elem));
        }
    }

    @Test
    public void getAdjNodes() {
        Set<String> adjSet = new HashSet<>();
        //Adjacent Nodes of A
        adjSet.add("B");
        assertEquals(adjSet,direct.getAdjNodes("A"));
    }

    @Test
    public void getLabel() {
        assertTrue(direct.getLabel("A","B") == 5);
        assertTrue(direct.getLabel("D","E") == 10);
        assertEquals(null,direct.getLabel("A","E"));
    }
}
