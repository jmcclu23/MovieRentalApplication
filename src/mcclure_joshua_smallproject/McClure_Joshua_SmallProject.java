/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcclure_joshua_smallproject;

import java.io.FileNotFoundException;

/**
 *
 * @author Josh
 */
public class McClure_Joshua_SmallProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(Movie.createMovie("Jurrasic World II"));
          //new MovieRentalStoreView().setVisible(true);
//        System.out.println(Movie.createMovie("Get Out"));
//        System.out.println(Movie.createMovie("Up"));
//        System.out.println(Movie.createMovie("Wall-e"));
//        System.out.println(Client.addCustomer("Emily McClure"));
//        Client.showAllCustomers();
        //System.out.println("All Movies:");
        //MovieModel.showAllMovies();
        //System.out.println("Rented Movies:");
        //Movie.showRentedMovies();
        
        //System.out.println(Movie.rentMovie("Wall-e",1));
//        System.out.println(Movie.rentMovie("Hidden Figures",1));
//        System.out.println(Movie.rentMovie("Star Wars",1));
//        System.out.println(Movie.rentMovie("Moana",1));
        
        //System.out.println("All Movies:");
        //Movie.showAllMovies();
//        System.out.println("Rented Movies:");
//        Movie.showRentedMovies();
        //System.out.println(Movie.returnMovie("Star Wars"));
        //Movie.showMovieHistory("Jurrasic World");
        
        MovieModelInterface   movieModel = new MovieModel();
        ClientModelInterface clientModel = new ClientModel();
	//ControllerInterface movieController = new MovieController(movieModel);
        ControllerInterface controller = new Controller(movieModel,clientModel);
        //clientController.showAllCustomers();
        //System.out.println(movieController.createMovie("Haunted Mansion"));
    }
    
}
