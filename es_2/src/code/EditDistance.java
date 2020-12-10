package code;

import java.lang.*;
public class EditDistance{

    /* Returns lowest value between 4 Int (not needed, but why not?)*/
    private static int min(int a, int b, int c,int d){
        return Math.min(Math.min(a, b) , Math.min(c,d));
    }

    /* Returns lowest value between 3 Int (not needed, but why not?)*/
    private static int min(int a, int b, int c){ return Math.min(Math.min(a,b),c); }

    /* Returns the edit distance between two Strings (s1 and s2)
     * RECURSIVE */
    public int editDistance(String s1, String s2){
        if(s1.length() == 0)
            return s2.length();
        else if(s2.length() == 0)
            return s1.length();
        else{
            String rest1 = s1.substring(1);
            String rest2 = s2.substring(1);
            char c1 = s1.charAt(0);
            char c2 = s2.charAt(0);

            int Dnop = Integer.MAX_VALUE;
            if(c1 == c2)
                Dnop = editDistance(rest1, rest2);

            int Dcanc = 1 + editDistance(s1, rest2);
            int Dins = 1 + editDistance(rest1, s2);
            int Dreplace = 1 + editDistance(rest1, rest2);

            return min(Dnop,Dcanc,Dins,Dreplace);
        }
    }

    /* Returns the edit distance between two Strings (s1 and s2)
     * RECURSIVE
     * DYNAMIC programming  */
    public int editDistanceDyn(String s1, String s2){
        int [][] tab = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab[i].length; j++)
                tab[i][j] = -1;

            return aux (s1, s2, (s1.length()), (s2.length()), tab);
    }

    /* Auxiliary method used by editDistanceDyn
    *  RECURSIVE */
    private static int aux(String s1, String s2, int i, int j, int[][] tab){
        if(i == 0){
            tab[i][j] = j;
            return j;
        }

        if(j == 0){
            tab[i][j] = i;
            return i;
        }

        if(tab[i][j] != -1)
            return tab[i][j];

        if(s1.charAt(i - 1) == s2.charAt(j - 1))
            tab[i][j] = aux(s1,s2,i-1,j-1,tab);
        else{
            tab[i][j] = 1 + min( aux(s1,s2,i,j-1,tab), aux(s1,s2,i-1,j-1,tab), aux(s1,s2,i-1,j,tab));
        }
        return tab[i][j];
    }

    /*----------------------------------------------------------------------------------------------------------------*/


    /* Returns the edit distance between two Strings (s1 and s2)
     * ITERATIVE
     * (not required) */
    public static int editDistanceIter(String s1, String s2){
        int [][] tab = new int[s2.length()+1][s1.length()+1];
        for(int i=0; i<= s1.length() ; i++)
            tab[0][i] = i;

        for(int j=0; j <= s2.length(); j++)
            tab[j][0] = j;

        for(int i=1; i< tab.length ; i++){
            for(int j=1; j< tab[i].length ; j++){
                if(s1.charAt(j-1) == s2.charAt(i-1))
                    tab[i][j] = tab[i-1][j-1];
                else
                    tab[i][j] = min(tab[i][j-1], tab[i-1][j-1], tab[i-1][j]) +1;
            }
        }
        return tab[s2.length()][s1.length()];
    }

    /* This just prints a 2D Array (for ex--> arr[][]) with the correct format
    * (not required) */
    private static void matrixPrint(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

}