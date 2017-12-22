package ru.xou.task03_url;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.google.gson.*;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;

public class UrlTest {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String firstName = input.next();
        System.out.print("Введите фамилию: ");
        String secName = input.next();
        //System.out.printf("Name: %s %s\n", firstName, secName);
        String path = String.format("http://www.theimdbapi.org/api/find/person?name=%s+%s", firstName, secName);

        URL url = new URL(path);
        URLConnection uc = url.openConnection();
        uc.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        uc.addRequestProperty("Content-Type", "text/html; charset=utf-8");
        uc.connect();
        //BufferedReader
        InputStream is = uc.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is, "CP1251");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        int c;
        while ((c=bufferedReader.read())!=-1){
            System.out.print((char) c);
        }
        is.close();
    }
}
