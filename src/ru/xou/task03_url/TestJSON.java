package ru.xou.task03_url;
import com.google.gson.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJSON {
    public static void main(String[] args) throws Exception {
        String s = "1962-1-17";
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatOut = new SimpleDateFormat("dd MM yyyy");
        Date date = formatIn.parse(s);
        System.out.println(formatOut.format(date));
        System.out.printf("%1$td %1$tB %1$tY",  date);
    }
}
