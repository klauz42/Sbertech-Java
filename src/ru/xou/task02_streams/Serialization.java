package ru.xou.task02_streams;
import java.io.*;
import java.util.*;

import com.google.gson.*;

public class Serialization {
    final static String cat = "/home/claus/testdir/";

    public static void main(String[] args){
        try {

            Flight flight1 = new Flight(42, "DME-TBW");
            Flight flight2 = new Flight(43, "DME-VLG");
            Flight flight3 = new Flight(44, "DME-SPB");

            ArrayList<Flight> flights = new ArrayList<>();
            flights.add(flight1);
            flights.add(flight2);
            flights.add(flight3);

            File f = new File(cat + "saved.obj");
            if(!f.exists()){
                f.createNewFile();
            }

            Flight.saveToFileAll(flights, f);

            //ArrayList<Flight> newFlights = Flight.loadFromFileAll(f);

            for (Flight fl : flights) {
                System.out.println(fl.toString());
            }

            System.out.println("------------------");
            Flight.serializeAll(flights, cat + "gson.obj");
            ArrayList<Flight> flightsJSON = Flight.deserializeAll(cat + "gson.obj");
            for (Flight fl : flightsJSON) {
                System.out.println(fl.toString());
            }

            /*
            System.out.println(flight1);
            flight1.saveToFile(f.getPath());

            Flight flight4 = Flight.loadFromFile(cat + "saved.obj");
            System.out.println(flight4);

            System.out.println("----------------");
            flight1.serialize(cat + "gson.obj");
            Flight flight5 = Flight.deserialize(cat + "gson.obj");
            System.out.println(flight5);
            */
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}

class Flights {
    protected List<Flight> flights = new ArrayList<>();

    Flights(ArrayList<Flight> fl){
        this.flights = fl;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}

class Flight implements Serializable{
    int number;
    String name;

    @Override
    public String toString() {
        return "Flight number is " + this.number + " and its name is " + this.name;
    }

    //Cохранить несколько объектов

    public static void saveToFileAll(ArrayList<Flight> flights, String pathToSavingFile){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToSavingFile))){
            for (Flight f : flights) {
                oos.writeObject(f);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void saveToFileAll(ArrayList<Flight> flights, File file){
        saveToFileAll(flights, file.getPath());
    }

    //Загрузить несколько объектов

    public static ArrayList<Flight> loadFromFileAll (String pathToLoadingFile) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToLoadingFile))){
            ArrayList<Flight> flights = new ArrayList<>();
            while (ois.available() > 0){
                flights.add((Flight) ois.readObject());
            }
            return flights;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static ArrayList<Flight> loadFromFileAll (File file){
        return loadFromFileAll(file.getPath());
    }

    //JSON-движ

    public void serialize(String filename) throws Exception{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))){
            Gson gson = new Gson();
            String JSON = gson.toJson(this);
            bufferedWriter.write(JSON);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void serializeAll(ArrayList<Flight> flightArrayList, String filename) throws Exception{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))){
            Gson gson = new Gson();
            Flights flights = new Flights(flightArrayList);
            String JSON = gson.toJson(flights);
            bufferedWriter.write(JSON);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static Flight deserialize(String filename) throws Exception{
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            Gson gson = new Gson();
            Flight fl = gson.fromJson(bufferedReader, Flight.class);
            return fl;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static ArrayList<Flight> deserializeAll (String filename) throws Exception{
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            Gson gson = new Gson();
            Flights flights = gson.fromJson(bufferedReader, Flights.class);
            return (ArrayList) flights.getFlights();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }


    //Работа с единственным объектом

    public void saveToFile(String pathToSavingFile) throws Exception{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToSavingFile))){
            oos.writeObject(this);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static  Flight loadFromFile(String pathToLoadingFile) throws Exception{
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToLoadingFile))){
            Flight f = (Flight) ois.readObject();
            return f;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return new Flight(0, "");
        }
    }

    //конструирование

    public Flight (int flightNumber, String flightName){
        number = flightNumber;
        name = flightName;
    }


}

