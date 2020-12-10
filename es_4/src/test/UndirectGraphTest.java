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

public class UndirectGraphTest {
    Graph<String, Integer> undirect;
    Set<Edge<String,Integer>> s1;
    Set<Edge<String,Integer>> s2;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUndirectGraph(){
        undirect = new Graph<String,Integer>(false);

        undirect.addNode("New York");
        undirect.addNode("Washington");
        undirect.addNode("Miami");
        undirect.addNode("Atlanta");
        undirect.addNode("Baltimore");
        undirect.addNode("Philadelphia");

        undirect.addEdge("New York", "Miami", 2057);
        undirect.addEdge("Miami","Washington",1696);
        undirect.addEdge("New York", "Washington",365);
        undirect.addEdge("New York", "Atlanta",1416);
        undirect.addEdge("Atlanta", "Baltimore",1101);
        undirect.addEdge("Atlanta", "Philadelphia",1250);
        undirect.addEdge("Philadelphia", "New York",152);

        s1 = new HashSet<>();
        s1.add(new Edge<>("New York", "Miami", 2057));
        s1.add(new Edge<>("Miami","Washington",1696));
        s1.add(new Edge<>("New York", "Washington",365));
        s1.add(new Edge<>("New York", "Atlanta",1416));
        s1.add(new Edge<>("Atlanta", "Baltimore",1101));
        s1.add(new Edge<>("Atlanta", "Philadelphia",1250));
        s1.add(new Edge<>("Philadelphia", "New York",152));

        s2 = new HashSet<>();
        s2.add(new Edge<>("Miami", "New York", 2057));
        s2.add(new Edge<>("Washington","Miami",1696));
        s2.add(new Edge<>("Washington", "New York",365));
        s2.add(new Edge<>("Atlanta", "New York",1416));
        s2.add(new Edge<>("Baltimore", "Atlanta",1101));
        s2.add(new Edge<>("Philadelphia", "Atlanta",1250));
        s2.add(new Edge<>("New York", "Philadelphia",152));
    }

    @Test
    public void isOriented() {
        assertFalse(undirect.isOriented());
    }

    @Test
    public void isOrientedNull(){
        Graph<String,Integer> g = null;
        exception.expect(NullPointerException.class);
        g.isOriented();
    }

    /**
     * Adding new Node --> size of Node increments (+1) */
    @Test
    public void addNode() {
        assertEquals(6, undirect.getNodeSize());
        undirect.addNode("Paris");
        assertEquals(7, undirect.getNodeSize());
    }

    @Test
    public void addNullNode(){
        exception.expect(NullPointerException.class);
        undirect.addNode(null);
    }

    /**
     * Trying to add an existing Node --> size of Nodes does not change */
    @Test
    public void addExistingNode() {
        assertEquals(6, undirect.getNodeSize());
        undirect.addNode("New York");
        assertEquals(6, undirect.getNodeSize());
    }


    /**
     * Adding new Edge --> size of Edges increments (+1) */
    @Test
    public void addEdge() {
        assertEquals(7, undirect.getEdgeSize());
        undirect.addEdge("Washington","Atlanta",1028);
        assertEquals(8, undirect.getEdgeSize());
    }

    @Test
    public void addNullEdge(){
        exception.expect(NullPointerException.class);
        undirect.addEdge(null,null,null);
    }

    /**
     * Trying to add an existing Edge --> size of Edges does not change (because it is an Undirect Graph) */
    @Test
    public void addExistingEdge() {
        assertEquals(7, undirect.getEdgeSize());
        undirect.addEdge("Miami","New York",1234);
        assertEquals(7, undirect.getEdgeSize());
    }

    @Test
    public void nodeIsContained() {
        assertTrue(undirect.nodeIsContained("New York"));
        assertTrue(undirect.nodeIsContained("Washington"));
        assertTrue(undirect.nodeIsContained("Miami"));
        assertTrue(undirect.nodeIsContained("Atlanta"));
        assertTrue(undirect.nodeIsContained("Baltimore"));
        assertTrue(undirect.nodeIsContained("Philadelphia"));
        assertFalse(undirect.nodeIsContained("Turin"));
        assertFalse(undirect.nodeIsContained("Milan"));
        assertFalse(undirect.nodeIsContained(null));
    }

    @Test
    public void edgeIsContained() {
        assertTrue(undirect.edgeIsContained("New York", "Miami"));
        assertTrue(undirect.edgeIsContained("Miami", "New York"));

        assertTrue(undirect.edgeIsContained("Miami","Washington"));
        assertTrue(undirect.edgeIsContained("New York", "Washington"));
        assertTrue(undirect.edgeIsContained("New York", "Atlanta"));
        assertTrue(undirect.edgeIsContained("Atlanta", "Baltimore"));
        assertTrue(undirect.edgeIsContained("Atlanta", "Philadelphia"));
        assertTrue(undirect.edgeIsContained("Philadelphia", "New York"));

        assertFalse(undirect.edgeIsContained("Berlin","Munich"));
        assertFalse(undirect.edgeIsContained(null,null));
    }

    @Test
    public void deleteNode() {
        assertTrue(undirect.nodeIsContained("New York"));
        undirect.deleteNode("New York");
        assertFalse(undirect.nodeIsContained("New York"));
    }

    /**
     *  */
    @Test
    public void deleteEdgesFromNode(){
        assertTrue(undirect.edgeIsContained("New York","Miami"));
        assertTrue(undirect.edgeIsContained("Miami","New York"));
        assertTrue(undirect.edgeIsContained("Philadelphia","New York"));
        assertTrue(undirect.edgeIsContained("New York","Philadelphia"));

        undirect.deleteNode("New York");

        assertFalse(undirect.edgeIsContained("New York","Miami"));
        //assertFalse(undirect.edgeIsContained("Miami","New York"));
        assertFalse(undirect.edgeIsContained("Philadelphia","New York"));
        //assertFalse(undirect.edgeIsContained("New York","Philadelphia"));
    }

    @Test
    public void deleteEdge() {
        assertTrue(undirect.edgeIsContained("New York","Miami"));
        assertTrue(undirect.edgeIsContained("Miami","New York"));

        undirect.deleteEdge("New York","Miami");

        assertFalse(undirect.edgeIsContained("New York","Miami"));
        assertFalse(undirect.edgeIsContained("Miami","New York"));
    }

    @Test
    public void getNodeSize() {
        assertEquals(6, undirect.getNodeSize());
    }

    @Test
    public void getEdgeSize() {
        assertEquals(7, undirect.getEdgeSize());
    }

    @Test
    public void getNodes() {
        Set<String> s = new HashSet<>();
        s.add("New York");
        s.add("Washington");
        s.add("Miami");
        s.add("Atlanta");
        s.add("Baltimore");
        s.add("Philadelphia");
        assertEquals(s,undirect.getNodes());
    }

    @Test
    public void getEdges() {
        assertEquals(s1.size(),undirect.getEdgeSize());

        Iterator <Edge<String,Integer>> iterator = undirect.getEdges().iterator();
        while (iterator.hasNext()){
            Edge<String,Integer> elem = iterator.next();
            assertTrue(s1.contains(elem) || s2.contains(elem));
        }
    }

    @Test
    public void getAdjNodes() {
        Set<String> adjSet = new HashSet<>();
        //Adjacent Nodes of New York
        adjSet.add("Miami");
        adjSet.add("Washington");
        adjSet.add("Atlanta");
        adjSet.add("Philadelphia");
        assertEquals(adjSet,undirect.getAdjNodes("New York"));
    }

    @Test
    public void getLabel() {
        assertTrue(undirect.getLabel("New York","Miami") == 2057);
        assertTrue(undirect.getLabel("Miami","New York") == 2057);

        assertEquals(null,undirect.getLabel("New York","Baltimore"));
    }
}