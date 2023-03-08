/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Main;

import RMIConnections.Client;
import UI.Login;

/**
 *
 * @author ryann
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Connection established with server.");
        Client client = new Client();
        
        //new LoginForm().setVisible(true);
        new Login().setVisible(true);
        
    }
}
