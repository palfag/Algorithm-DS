package test;
import code.EditDistance;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class TestEditDistance{

    @Test
    public void testEditEmptyFistString(){
        String a = "";
        String b = "Paolo";
        EditDistance dis = new EditDistance();
        int res = dis.editDistance(a, b);
        assertEquals(res,b.length());
    }

    @Test
    public void testEditDynEmptyFistString(){
        String a = "";
        String b = "Paolo";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,b.length());
    }

    @Test
    public void testEditEmptyBoth(){
        String a = "";
        String b = "";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,0);
    }

    @Test
    public void testEditDynEmptyBoth(){
        String a = "";
        String b = "";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,0);
    }

    @Test
    public void testEditEmptySecondString(){
        String a = "Kristian";
        String b = "";
        EditDistance dis = new EditDistance();
        int res = dis.editDistance(a, b);
        assertEquals(res,a.length());
    }

    @Test
    public void testEditDynEmptySecondString(){
        String a = "Kristian";
        String b = "";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,a.length());
    }

    @Test
    public void testEditEquals(){
        String a = "Lavinia";
        String b = "Lavinia";
        EditDistance dis = new EditDistance();
        int res = dis.editDistance(a, b);
        assertEquals(res,0);
    }

    @Test
    public void testEditDynEquals(){
        String a = "Lavinia";
        String b = "Lavinia";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,0);
    }

    @Test
    public void testEditCanc(){
        String a = "casa";
        String b = "cassa";
        EditDistance dis = new EditDistance();
        int res = dis.editDistance(a, b);
        assertEquals(res,1);
    }

    @Test
    public void testEditDynCanc(){
        String a = "casa";
        String b = "cassa";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,1);
    }

    @Test
    public void testEditRimp(){
        String a = "casa";
        String b = "cara";
        EditDistance dis = new EditDistance();
        int res = dis.editDistance(a, b);
        assertEquals(res,1);
    }

    @Test
    public void testEditDynRimp(){
        String a = "casa";
        String b = "cara";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,1);
    }

    @Test
    public void testEditIns(){
        String a = "vinaio";
        String b = "vino";
        EditDistance dis = new EditDistance();
        int res = dis.editDistance(a, b);
        assertEquals(res,2);
    }

    @Test
    public void editDistanceDynIns(){
        String a = "vinaio";
        String b = "vino";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,2);
    }

    @Test
    public void testEditCancANDIns(){
        String a = "tassa";
        String b = "passato";
        EditDistance dis = new EditDistance();
        int res = dis.editDistance(a, b);
        assertEquals(res,3);
    }

    @Test
    public void testEditDynCancANDIns(){
        String a = "tassa";
        String b = "passato";
        EditDistance dis = new EditDistance();
        int res = dis.editDistanceDyn(a, b);
        assertEquals(res,3);
    }
}
