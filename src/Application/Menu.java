package Application;

import java.util.Scanner;

public abstract class Menu {
    private static Scanner input = new Scanner(System.in);

    public static void displayAdminMenu(){
        try{
            System.out.println("Menu: ");
            System.out.println("Create new team (press 1)");
            System.out.println("Display Statistics for team (press 3)");
            System.out.println("Display the Premier League Table (press 4)");
            System.out.println("Add a Played Match (press 5)");
            System.out.println("Display Calendar and Find Match (press 6)");
            int n = input.nextInt();
        }catch (Exception e){
            System.out.println("Error!");
            displayAdminMenu();
        }
    }
    public static void displayUserMenu(){
        System.out.println("You're logged as an user");
    }
}
