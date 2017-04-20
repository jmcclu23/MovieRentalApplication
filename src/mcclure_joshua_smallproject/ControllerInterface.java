/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcclure_joshua_smallproject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public interface ControllerInterface {
    String addCustomer(String name) throws FileNotFoundException;
    int deleteCustomer();
    void searchCustomer();
    void showAllCustomers();
    String createMovie(String movie_title) throws FileNotFoundException;
    String rentMovie(String movieTitleToRent, int custID) throws FileNotFoundException;
    String returnMovie(String movieTitleToReturn) throws FileNotFoundException; 
    void showAllMovies();
    void showRentedMovies();
    ArrayList<String> showMovieHistory(String movie);
}
