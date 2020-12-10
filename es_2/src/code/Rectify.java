package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rectify{
    /* PATH to input files */
    private static final String pathCorrectMe = "src/code/correctme.txt";
    private static final String pathDictionary = "src/code/dictionary.txt";
    private static File correctMe;
    private static File dictionary;

    public static void corrector() throws FileNotFoundException{
        Rectify.correctMe = new File(pathCorrectMe);
        Scanner quote = new Scanner(correctMe);
        quote.useDelimiter("[\\s,!?.:]+");

        while(quote.hasNext()){
            String phr = quote.next();
            ArrayList<String> list = fixer(phr);

            for(String item : list)
                System.out.print(item + " ");
            System.out.println();
        }
        quote.close();

    }

    private static ArrayList<String> fixer(String phr) throws FileNotFoundException{
        ArrayList<String> l = new ArrayList<String>();
        Rectify.dictionary = new File(pathDictionary);

        if(!Rectify.firstCheck(phr)) {  //check if the word is already in the dictionary

            EditDistance dis = new EditDistance();
            String s = "";
            int min = Integer.MAX_VALUE;

            Scanner second = new Scanner(dictionary); // open dictionary (second opening)
            while (second.hasNextLine()) {
                s = second.nextLine();
                int d = dis.editDistanceDyn(phr.toLowerCase(), s);
                if (!(min < d)) {
                    if (min > d) {
                        l.clear();
                        l.add(s);
                        min = d;
                    } else // min == d
                        l.add(s);
                }
            }
            second.close(); //close (second opening)
        }
        return l;
    }

    private static boolean firstCheck(String phr) throws FileNotFoundException{
        String s="";
        boolean check = false;
        Scanner first = new Scanner(dictionary);// open dictionary (first opening)

        while(first.hasNextLine() && !check){
            s = first.nextLine();
            if(s.equalsIgnoreCase(phr)){
                System.out.print(phr + " ");
                check = true;
            }
        }
        first.close(); // close dictionary (first opening)
        return check;
    }

    public static void main(String[] args){
        try {
            Rectify.corrector();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}