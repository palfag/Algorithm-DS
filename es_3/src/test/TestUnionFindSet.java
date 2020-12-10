package test;
import code.*;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFindSet<T> {

    UnionFindSet<String> s = new UnionFindSet<String>();
    UnionFindSet<Integer> i = new UnionFindSet<Integer>();

    public void exampleStringSet(){
        s.makeSet("a");
        s.makeSet("b");
        s.makeSet("c");
        s.makeSet("d");
    }

    public void exampleIntegerSet(){
        i.makeSet(5);
        i.makeSet(4);
        i.makeSet(3);
        i.makeSet(2);
        i.makeSet(1);
    }

    @Test
    public void testMakeSetString(){
        exampleStringSet();
        HashMap<String, Node<String>> map = s.getMap();
        assertNotNull(map);
        assertEquals(4,map.size());
        assertEquals("a",map.get("a").getElem());
        assertEquals("b",map.get("b").getElem());
        assertEquals("c",map.get("c").getElem());
        assertEquals("d",map.get("d").getElem());
    }

    @Test
    public void testMakeSetInteger(){
        exampleIntegerSet();
        HashMap<Integer, Node<Integer>> map = i.getMap();
        assertNotNull(map);
        assertEquals(5,map.size());
        assertEquals((Integer) 1, map.get(1).getElem());
        assertEquals((Integer) 3, map.get(3).getElem());
        assertEquals((Integer) 2, map.get(2).getElem());
        assertEquals((Integer) 5, map.get(5).getElem());
        assertEquals((Integer) 4, map.get(4).getElem());
    }

    @Test
    public void findBeforeUnion(){
        exampleIntegerSet();
        assertEquals((Integer) 1, i.find(1).getElem());
        assertEquals((Integer) 3, i.find(3).getElem());
        assertNotNull(i.find(3));
    }

    @Test
    public void findAfterUnion(){
        exampleStringSet();
        assertEquals("a",s.find("a").getElem());
        assertEquals("b",s.find("b").getElem());
        s.union("a","b");
        assertEquals("a",s.find("a").getElem());
        assertEquals("a",s.find("b").getElem());
    }

    @Test
    public void unionSameRank(){
        exampleStringSet();
        s.union("a","b"); // Rank A = 0 , Rank B = 0
        assertEquals("a", s.find("a").getElem());
        assertEquals("a", s.find("b").getElem());
        assertEquals(1,s.find("a").getRank()); // incremented Rank A
    }

    @Test
    public void unionRankAGreaterThanB(){
        exampleStringSet();
        s.union("a","b");
        s.union("b", "d"); // Rank A = 1 , Rank B = 0
        assertEquals("a", s.find("d").getElem());
        assertEquals(1,s.find("a").getRank());
    }

    @Test
    public void unionRankALessThanRankB(){
        exampleIntegerSet();
        i.union(3,4);
        i.union(4,5);
        i.union(1,5); // Rank A = 0 , Rank B = 1
        assertEquals((Integer)3, i.find((Integer)1).getElem());
        assertEquals(1,i.find((Integer)1).getRank());
    }
}