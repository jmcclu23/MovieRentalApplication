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
public class Controller implements ControllerInterface{
    MovieModelInterface movieModel;
    ClientModelInterface clientModel;
    MovieRentalStoreView view;
    public Controller(MovieModelInterface movieModel, ClientModelInterface clientModel) {
		this.movieModel  = movieModel;
                this.clientModel = clientModel;
                view = new MovieRentalStoreView(this, movieModel, clientModel);
                view.run();
    }
    @Override
    public String addCustomer(String name) throws FileNotFoundException {
       return clientModel.addCustomer(name);
    }

    @Override
    public String deleteCustomer(String customerName) {
        return clientModel.deleteCustomer(customerName);
    }

    @Override
    public String searchCustomer(String name) {
        return clientModel.searchCustomer(name);
    }

    @Override
    public ArrayList<String> showAllCustomers() {
        return clientModel.showAllCustomers();
    }

    @Override
    public String createMovie(String movie_title) throws FileNotFoundException {
        return movieModel.createMovie(movie_title);
    }

    @Override
    public String rentMovie(String movieTitleToRent, String CustomerName) throws FileNotFoundException {
        return movieModel.rentMovie(movieTitleToRent, CustomerName);
        
    }

    @Override
    public String returnMovie(String movieTitleToReturn) throws FileNotFoundException {
        return movieModel.returnMovie(movieTitleToReturn);
    }

    @Override
    public ArrayList<String> showAllMovies() {
         return movieModel.showAllMovies();
    }

    @Override
    public ArrayList<String> showRentedMovies() {
        return movieModel.showRentedMovies();
    }

    @Override
    public ArrayList<String> showMovieHistory(String movie) {
       return movieModel.showMovieHistory(movie);
    }
}
