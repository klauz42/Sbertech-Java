package ru.xou.task02_streams;
import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Sentences {

    public static void main(String[] args) throws Exception {
        String[] strings = sentencesGet("/home/claus/testdir/test.txt");
        for (String s: strings){
            System.out.println(s);
        }
    }

    public static String[] sentencesGet (String filename) throws Exception{

        File f = new File(filename);
        ArrayList<String> strings = new ArrayList<>();

        try(Scanner sc = new Scanner(f).useDelimiter("\\.\\s")){
           // StringBuilder stringBuilder = new StringBuilder();

            while (sc.hasNext()){
                strings.add(sc.next());
            }
            return strings.toArray(new String[0]);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }


}
