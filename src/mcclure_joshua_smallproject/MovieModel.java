/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcclure_joshua_smallproject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Josh
 */
public class MovieModel implements MovieModelInterface {

    public static String filePath = "documents/";
    public static File movieFile;
    public static File rentalFile;
    public static File clientFile;
    public static FileInputStream fis = null;
    public static int movieID, movieRented, i, i_r, movieIDCount, rentalIDCount;
    public static String movieTitle, stringToWrite;
    public static ArrayList<String> movies = new ArrayList<String>();
    public static ArrayList<String> rentals = new ArrayList<String>();
    public static ArrayList<String> clients = new ArrayList<String>();
    public static String[] movieArray, rentalArray, clientArray;
    public static String delimeter = ";";
    public static String returnString = "";
    
    public MovieModel(){
        filePath = "documents/";
        movieFile = new File(filePath + "movies.txt");
        rentalFile = new File(filePath + "rental_info.txt");
        clientFile = new File(filePath + "clients.txt");
    }
    public MovieModel(String file){
        filePath = file;
        movieFile = new File(filePath + "movies.txt");
        rentalFile = new File(filePath + "rental_info.txt");
        clientFile = new File(filePath + "clients.txt");
    }
    @Override
    public String createMovie(String movie_title) throws FileNotFoundException {
        int exists = 0;
        movieTitle = movie_title;
        movieRented = 0;
        movies = readFile(movieFile);
        movieIDCount = nextValue(movies);
        for(i = 0; i < movies.size(); i++){
            movieArray = movies.get(i).split(delimeter);
            if(movieArray[1].equalsIgnoreCase(movieTitle)){
                exists = 1;
                break;
            }
        }
        if(exists == 0){
            movies.add((movieIDCount) + ";" + movieTitle + ";" + movieRented);
            writeFile(movieFile, movies);
            return ("Movie " + movieTitle + " added with ID " + movieIDCount);
        }else{
            return ("A copy of " + movieTitle + " already exists in our library");
        }
    }

    @Override
    public String rentMovie(String movieTitleToRent, String CustomerName) throws FileNotFoundException {
        movies = readFile(movieFile);
        rentals = readFile(rentalFile);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
        Date date = new Date();
        String time = sdf.format(date);
        String rentalInfo;
        int rentedMovieCount = 0;
        int custID = getCustomerNumber(CustomerName);
        for (i = 0; i < rentals.size(); i++){
            rentalArray = rentals.get(i).split(delimeter);
            
            if(custID == Integer.parseInt(rentalArray[1])&&rentalArray[4].equalsIgnoreCase("null")){
                rentedMovieCount++;
            }
        }
        if(rentedMovieCount < 3){
            for (i = 0; i < movies.size(); i++) {
                movieArray = movies.get(i).split(delimeter);
                movieID = Integer.parseInt(movieArray[0]);
                movieTitle = movieArray[1];
                if (movieArray[2].equalsIgnoreCase("0\n") || movieArray[2].equalsIgnoreCase("1\n")) {
                    movieRented = Integer.valueOf(movieArray[2].substring(0, 1));
                } else {
                    movieRented = Integer.valueOf(movieArray[2]);
                }
                if (movieTitleToRent.equalsIgnoreCase(movieTitle)) {
                    if (movieRented == 0) {
                        movieRented = 1;
                        movies.set(i, movieID + ";" + movieTitle + ";" + movieRented);
                        rentalIDCount = nextValue(rentals);
                        rentalInfo = rentalIDCount + ";" + custID + ";" + movieID + ";"+ time + ";null";
                        rentals.add(rentalInfo);
                        writeFile(movieFile, movies);
                        writeFile(rentalFile, rentals);
                        returnString = movieTitle + " rented!";
                        break;
                    } else {
                        returnString = movieTitle + " is unavailable";
                    }
                }
            }
        }else{
            returnString = "You have more than 3 movies checked out";
        }
        
        return returnString;
    }

    @Override
    public String returnMovie(String movieTitleToReturn) throws FileNotFoundException {
        movies = readFile(movieFile);
        rentals = readFile(rentalFile);
        String r_ID, c_ID, m_ID,rentDate,returnDate;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY");
        Date date = new Date();
        String time = sdf.format(date);
        for (i = 0; i < movies.size(); i++) {
            movieArray = movies.get(i).split(delimeter);
            movieID = Integer.parseInt(movieArray[0]);
            movieTitle = movieArray[1];
            if (movieArray[2].equalsIgnoreCase("0\n") || movieArray[2].equalsIgnoreCase("1\n")) {
                movieRented = Integer.valueOf(movieArray[2].substring(0, 1));
            } else {
                movieRented = Integer.valueOf(movieArray[2]);
            }
            if (movieTitleToReturn.equalsIgnoreCase(movieTitle)) {
                if (movieRented == 1) {
                    movieRented = 0;
                    movies.set(i, movieID + ";" + movieTitle + ";" + movieRented);
                    for(i_r = 0; i_r < rentals.size(); i_r++){
                        rentalArray = rentals.get(i_r).split(delimeter);
                        if(rentalArray[4].equalsIgnoreCase("null")&& rentalArray[2].equalsIgnoreCase(movieArray[0])){
                            r_ID       = rentalArray[0];
                            c_ID       = rentalArray[1];
                            m_ID       = rentalArray[2];
                            rentDate   = rentalArray[3];
                            returnDate = time;
                            rentals.set(i_r, r_ID + ";" + c_ID + ";" + m_ID + ";" + rentDate + ";" + returnDate);
                        }    
                    }
                    writeFile(rentalFile,rentals);
                    writeFile(movieFile, movies);
                    returnString = movieTitle + " Returned!";
                    break;
                } else {
                    returnString = movieTitle + " is not rented";
                }
            }
        }
        return returnString;
    }

    @Override
    public ArrayList<String> showAllMovies() {
        ArrayList<String> returnArray = new ArrayList();
        movies = readFile(movieFile);
        //System.out.println("Movie ID  Movie Title  Status");
        for (i = 0; i < movies.size(); i++) {
            movieArray = movies.get(i).split(delimeter);
            movieID = Integer.parseInt(movieArray[0]);
            movieTitle = movieArray[1];
            if (movieArray[2].equalsIgnoreCase("0\n") || movieArray[2].equalsIgnoreCase("1\n")) {
                movieRented = Integer.valueOf(movieArray[2].substring(0, 1));
            } else {
                movieRented = Integer.valueOf(movieArray[2]);
            }
            if(movieRented == 1){
                String temp = movieID + ";" + movieTitle + ";Rented";
                returnArray.add(temp);
                //System.out.println("    " + movieID + "       " + movieTitle + "     Rented");
            }
        }
        for (i = 0; i < movies.size(); i++) {
            movieArray = movies.get(i).split(delimeter);
            movieID = Integer.parseInt(movieArray[0]);
            movieTitle = movieArray[1];
            if (movieArray[2].equalsIgnoreCase("0\n") || movieArray[2].equalsIgnoreCase("1\n")) {
                movieRented = Integer.valueOf(movieArray[2].substring(0, 1));
            } else {
                movieRented = Integer.valueOf(movieArray[2]);
            }
            if(movieRented == 0){
                String temp = movieID + ";" + movieTitle + ";Available";
                returnArray.add(temp);
                //System.out.println("    " + movieID + "       " + movieTitle + "     Available");
            }
        }
        return returnArray;
    }

    /**
     *
     * @return 
     */
    @Override
    public ArrayList<String> showRentedMovies() {
        movies = readFile(movieFile);
        ArrayList<String> returnArray = new ArrayList();
        //System.out.println("Movie ID  Movie Title  Status");
        for (i = 0; i < movies.size(); i++) {
            movieArray = movies.get(i).split(delimeter);
            movieID = Integer.parseInt(movieArray[0]);
            movieTitle = movieArray[1];
            if (movieArray[2].equalsIgnoreCase("0\n") || movieArray[2].equalsIgnoreCase("1\n")) {
                movieRented = Integer.valueOf(movieArray[2].substring(0, 1));
            } else {
                movieRented = Integer.valueOf(movieArray[2]);
            }
            if(movieRented == 1){
                String temp = movieID + ";" + movieTitle + ";Rented";
                returnArray.add(temp);
                //System.out.println("    " + movieID + "       " + movieTitle + "     Rented");
            }
        }
        return returnArray;
    }
    @Override
    public ArrayList<String> showMovieHistory(String movie) {
        movies = readFile(movieFile);
        rentals = readFile(rentalFile);
        clients = readFile(clientFile);
        int i_down;
        ArrayList<String> history = new ArrayList();
        String [] historyArray;
        String m_ID ="", m_Title ="", m_Status ="", r_ID, c_ID, date_Out, date_In,
                c_Name="",c_Status="", historyString, status;
        for(i = 0; i < movies.size(); i++){
            movieArray = movies.get(i).split(delimeter);
            if(movieArray[1].equalsIgnoreCase(movie)){
                m_ID = movieArray[0];
                m_Title = movieArray[1];
                m_Status = movieArray[2];
                break;
            }
        }
        for(i = 0; i < rentals.size(); i++){
            rentalArray = rentals.get(i).split(delimeter);
            if(rentalArray[2].equalsIgnoreCase(m_ID)){
                r_ID     = rentalArray[0];
                c_ID     = rentalArray[1];
                date_Out = rentalArray[3];
                date_In  = rentalArray[4];
                for(i_r = 0; i_r < clients.size(); i_r++){
                    clientArray = clients.get(i_r).split(delimeter);
                    if(clientArray[0].equalsIgnoreCase(c_ID)){
                        c_Name   = clientArray[1];
                        c_Status = clientArray[2];
                        break;
                    }
                }
                historyString = r_ID + ";" + c_ID + ";" + date_Out + ";" + date_In + ";" + c_Name + ";" + c_Status;
                history.add(historyString);
            }
        }
        //System.out.println("Showing Movie History for Movie - ID: " + m_ID + " Title: " + m_Title);
        if(m_Status.equalsIgnoreCase("0")||m_Status.equalsIgnoreCase("0\n")){
            //System.out.println("Current Status: Available");
        }else if (m_Status.equalsIgnoreCase("1")){
           //System.out.println("Current Status: Rented");
        }else{
            //System.out.println("Current Status: Error. Status Unavilable");
        }
        if(history.size() > 0){
          //System.out.println("ID  Date Out    Date In    Customer ID  "
                  //+ "Customer Name   Customer Status");
          i_down = history.size() - 1;
          for(i = 0; i < history.size(); i++){
              historyArray = history.get(i_down).split(delimeter);
              if(historyArray[5].equalsIgnoreCase("0") || historyArray[5].equalsIgnoreCase("0\n")){
                  status = "Active";
              }else{
                  status = "Deleted";
              }
              //System.out.println(historyArray[0] + "  "+ historyArray[2] + " " +
                      //historyArray[3] + "         " + historyArray[1] +
                      //"        " + 
                      //historyArray[4] +"       " + status);
              i_down--;
          }
        }else{
            //System.out.println("No Rental History is Avalable");
        }
        return history;
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
        ArrayList<String> arrayList = new ArrayList();
        String[] fileArray;
        String fileString = "";
        try {
            fis = new FileInputStream(fileName);
//          //System.out.println("Total file size to read (in bytes) : "
//					+ fis.available());
            int content;
            while ((content = fis.read()) != -1) {
                fileString = fileString + String.valueOf((char) content);
            }
            if (!(fileString.equalsIgnoreCase(""))) {
                fileArray = fileString.split(",");
                arrayList = new ArrayList(Arrays.asList(fileArray));
            }
        } catch (IOException e) {
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
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
    public static int getCustomerNumber(String customerName){
        int returnCustID = 0;
        clients = readFile(clientFile);
        for(i = 0; i < clients.size(); i++){
            clientArray = clients.get(i).split(delimeter);
            if(customerName.equalsIgnoreCase(clientArray[1])){
                returnCustID = Integer.parseInt(clientArray[0]);
                break;
            } else {
            }
        }
        return returnCustID;
    }
}
