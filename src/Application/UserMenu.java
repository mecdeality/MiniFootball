package Application;

import config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public abstract class UserMenu {
    private static Scanner input = new Scanner(System.in);
    private static Connection con = Database.getConnection();
    private static Statement stmt;
    private static boolean trycatch;

    private static int lid;
    private static int tid;

    static {
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayUserMenu() throws SQLException {
        int n = 0;
        do {
            try {
                trycatch = false;
                System.out.println("Menu: ");
                System.out.println("All finished leagues (press 1)");
                System.out.println("Quit menu (press 2)");
                n = input.nextInt();
            } catch (Exception e) {
                input.next();
                trycatch = true;
                System.out.println("Error!");
            }
        }while (trycatch == true);
        switch(n) {
            case 1 :
                allLeagues();
                break;
            case 2 :
                System.out.println("Good bye!");
                break;
            default:
                System.out.println("Wrong Command!");
        }
    }
    public static void allLeagues() throws SQLException {
        System.out.println("All finished leagues: ");
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM leagues ");
        while(rs1.next())
            System.out.println(rs1.getString(2)+", "+rs1.getInt(3)+" teams.");

        System.out.println("Write the name of the league to see more information: ");
        String l = input.next() + input.nextLine();

        ResultSet rs2 = stmt.executeQuery("SELECT id FROM leagues WHERE name = \""+ l +"\" ORDER BY id DESC LIMIT 1 ");
        while(rs2.next())
            lid = rs2.getInt(1);

        showLeagueInfo(lid);
    }
    public static void showLeagueInfo(int id) throws SQLException {
        System.out.println(id);
        ResultSet rs = stmt.executeQuery("SELECT * FROM allteams WHERE league_id = "+id);
//        if(rs.next() == false){
//            System.out.println("League name is invalid or there are no teams in this league. \n");
//            allLeagues();
//        }
        while (rs.next()) {
            System.out.println("Team name: " + rs.getString(2) + ", Number of players: " + rs.getInt(3) + ", budget: " + rs.getInt(4) + "$");
        }
        System.out.println("Write the name of the team to see more information: ");
        String t = input.next() + input.nextLine();

        ResultSet rs2 = stmt.executeQuery("SELECT * FROM allteams WHERE name = \""+ t +"\" AND league_id = "+ lid);
//        if(!rs2.next()){
//            System.out.println("Check the correctness of the team.");
//            showLeagueInfo(id);
//        }
        while (rs2.next())
            tid = rs2.getInt(1);

        showTeamInfo(tid);

    }

    public static void showTeamInfo(int tid) throws SQLException {
        int n = 0;
        do {
            try {
                trycatch = false;
                System.out.println("Director of the team (press 1)");
                System.out.println("Coach of the team (press 2)");
                System.out.println("Players of the team (press 3)");

                n = input.nextInt();
            } catch (Exception e) {
                input.next();
                trycatch = true;
                System.out.println("Error!");
            }
        }while (trycatch == true);
        switch(n) {
            case 1 :
                showDirector(tid);
                break;
            case 2 :
                showCoach(tid);
                break;
            case 3 :
                showPlayers(tid);
                break;
            default:
                System.out.println("Wrong Command!");
        }
    }

    public static void showDirector(int id) throws SQLException{
        System.out.println(id);

        ResultSet rs = stmt.executeQuery("SELECT * FROM alldirectors WHERE team_id = "+id);
        while(rs.next())
            System.out.println("Name and Surname: " + rs.getString(2)+" "+rs.getString(3)+", Budget: "+rs.getInt(4) +"$, Salary: "+rs.getInt(5)+"$ \n");
        showTeamInfo(id);
    }

    public static void showCoach(int id) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT * FROM allcoaches WHERE team_id = "+id);
        while(rs.next())
            System.out.println("Name and Surname: " + rs.getString(2)+" "+rs.getString(3)+", Year of experience: "+rs.getInt(4) +" year, Salary: "+rs.getInt(5)+"$ \n");
        showTeamInfo(id);
    }

    public static void showPlayers(int id) throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT * FROM allplayers WHERE team_id = "+id);
        while(rs.next())
            System.out.println("Name and Surname: " + rs.getString(2) + " " + rs.getString(3)+ ", position: "+rs.getString(4)+", Salary: "+rs.getInt(5)+"$, defence: "+ rs.getInt(6)+", attack: "+ rs.getInt(7));
        showTeamInfo(id);
    }

}
