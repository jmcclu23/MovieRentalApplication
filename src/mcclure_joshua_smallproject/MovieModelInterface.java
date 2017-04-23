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
public interface MovieModelInterface {

    String createMovie(String movie_title) throws FileNotFoundException;
    String rentMovie(String movieTitleToRent, String CustomerName) throws FileNotFoundException;
    String returnMovie(String movieTitleToReturn) throws FileNotFoundException; 
    ArrayList<String> showAllMovies();
    ArrayList<String> showRentedMovies();
    ArrayList<String> showMovieHistory(String movie);
    void registerObserver(Observer o);
    void removeObserver(Observer o);
}
