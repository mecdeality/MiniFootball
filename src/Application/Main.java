package Application;

import People.Director;
import People.Player;

import java.sql.SQLException;
import java.util.Scanner;

import static Application.Menu.displayUserMenu;
import static Application.Menu.displayAdminMenu;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("What account would you like to log in?( type \"admin\" or \"user\"): ");
        String response = scan.nextLine();
        if(response.equalsIgnoreCase("ADMIN")){
            displayAdminMenu();
        }else if(response.equalsIgnoreCase("USER")){
            displayUserMenu();
        }else{
            System.out.println("Invalid input.");
        }
    }
}
