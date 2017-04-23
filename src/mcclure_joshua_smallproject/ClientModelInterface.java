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
public interface ClientModelInterface {
   String addCustomer(String name) throws FileNotFoundException;
   String deleteCustomer(String customerName);
   String searchCustomer(String Name);
   ArrayList<String> showAllCustomers();
   void registerObserver(Observer o);
   void removeObserver(Observer o);     

}
