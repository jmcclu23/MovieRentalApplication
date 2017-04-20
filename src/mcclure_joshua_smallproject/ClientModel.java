/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcclure_joshua_smallproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Josh
 */
public class ClientModel implements ClientModelInterface {
    public static String filePath;
    public static File movieFile;
    public static File rentalFile;
    public static File clientFile;
    public static FileInputStream fis = null;
    public static String delimeter = ";";
    public static int i = 0;
    public static ArrayList<String> clients = new ArrayList();
    public static String[] clientArray;
    public static String stringToWrite;
    public ClientModel(){
        filePath = "documents/";
        movieFile = new File(filePath + "movies.txt");
        rentalFile = new File(filePath + "rental_info.txt");
        clientFile = new File(filePath + "clients.txt");
    }
    public ClientModel(String file){
        filePath = file;
        movieFile = new File(filePath + "movies.txt");
        rentalFile = new File(filePath + "rental_info.txt");
        clientFile = new File(filePath + "clients.txt");
    }
    public String addCustomer(String name) throws FileNotFoundException{
        int exists = 0;
        int clientID;
        int active = 0;
        clients = readFile(clientFile);
        clientID = nextValue(clients);
        
        for(i = 0; i < clients.size(); i++){
            clientArray = clients.get(i).split(delimeter);
            if(clientArray[1].equalsIgnoreCase(name)){
                exists = 1;
                break;
            }
        }
        if(exists == 0){
            clients.add(clientID + ";" + name + ";" + active);
            writeFile(clientFile, clients);
            
            return ("Customer " + name + " added with ID " + clientID);
        }else{
            return ("Customer " + name + " already exists");
        }
    
    }
    public int deleteCustomer(){
        return 1;
    }
    public void searchCustomer(){
        
    }
    public ArrayList<String> showAllCustomers(){
        ArrayList<String> returnArray = new ArrayList();
        clients = readFile(clientFile);
        String clientID, clientName, clientStatus, statusDecoded;
        System.out.println("Showing All Customers");
        System.out.println("Customer ID      Customer Name     Status");
        for (i = 0; i < clients.size(); i++) {
            clientArray = clients.get(i).split(delimeter);
            clientID = clientArray[0];
            clientName = clientArray[1];
            if (clientArray[2].equalsIgnoreCase("0\n") || clientArray[2].equalsIgnoreCase("1\n")) {
                clientStatus = clientArray[2].substring(0, 1);
            } else {
                clientStatus = clientArray[2];
            }
            if(clientStatus.equalsIgnoreCase("0")){
                statusDecoded = "Active";
            }else{
                statusDecoded = "Deleted";
            }
                String temp = clientID + ";" + clientName + ";" + statusDecoded;
                returnArray.add(temp);
                System.out.println("    " + clientID + "       " + clientName + "     "+statusDecoded);
        }
        return returnArray;
    }
    public static void writeFile(File fileName, ArrayList<String> arrayList) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            for (i = 0; i < arrayList.size(); i++) {
                if (i == 0) {
                    stringToWrite = arrayList.get(i);
                } else {
                    stringToWrite = stringToWrite + "," + arrayList.get(i);
                }
            }
            out.print(stringToWrite);
        }
       
    }
     public static ArrayList readFile(File fileName) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String[] fileArray;
        String fileString = "";
        try {
            fis = new FileInputStream(fileName);
//          System.out.println("Total file size to read (in bytes) : "
//					+ fis.available());
            int content;
            while ((content = fis.read()) != -1) {
                fileString = fileString + String.valueOf((char) content);
            }
            if (!(fileString.equalsIgnoreCase(""))) {
                fileArray = fileString.split(",");
                arrayList = new ArrayList<String>(Arrays.asList(fileArray));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return arrayList;
    }
    public static int nextValue(ArrayList<String> arrayList){
       int count = 0;
       
       for (i = 0; i < arrayList.size(); i++) {
           String[] stringArray;
           stringArray = arrayList.get(i).split(delimeter);
           if (count < Integer.parseInt(stringArray[0])){
               count = Integer.parseInt(stringArray[0]);
           }
       }
       return count + 1;
    }
}
