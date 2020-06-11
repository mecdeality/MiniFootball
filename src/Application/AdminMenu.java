package Application;

import League.League;
import League.Team;
import People.Coach;
import People.Director;
import People.Player;
import config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AdminMenu {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Team> teams = new ArrayList<Team>();
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Connection con = Database.getConnection();
    private static Statement stmt;

    private static int lid;
    private static int tid;

    static {
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayAdminMenu() throws SQLException {
        int n = 0;
        try{
            System.out.println("Menu: ");
            System.out.println("Create new league (press 1)");
            System.out.println("Show information about finished leagues (press 2)");
            n = input.nextInt();
        }catch (Exception e){
            System.out.println("Error!");
            displayAdminMenu();
        }
        switch(n) {
            case 1 :
                addLeague();
                break;
            case 2 :
                displayUserMenu();
                break;
            default:
                System.out.println("Wrong Command!");
        }
    }
    public static void displayUserMenu(){
        System.out.println("You're logged as an user");
    }

    private static void addLeague() throws SQLException {
        String name = "";
        League l = null;
        try{
            System.out.println("The name of the league: ");
            name = input.next() + input.nextLine();
            l = new League(name);
        }catch (Exception e){
            System.out.println("Error!");
        }

        stmt.executeUpdate("INSERT INTO leagues (name) VALUES (\""+ name +"\")");
        ResultSet rs = stmt.executeQuery("SELECT id FROM leagues WHERE name = \""+ name+"\" ORDER BY id DESC LIMIT 1 ");
        while(rs.next())
            lid = rs.getInt(1);

        System.out.println("The league \""+ l.getName() +"\" is created, now let's create teams, and add them to this league. ");
        createTeams(l);
    }

    private static void createTeams(League league) throws SQLException {
        System.out.println("Name of the team: ");
        String name = input.next() + input.nextLine();
        System.out.println("Football team must have one director, one coach and at least 5 players. Let's start creating with director.");
        System.out.println("Name of the director: ");
        String dname = input.next() + input.nextLine();
        System.out.println("Surname of the director: ");
        String dsurname = input.next() + input.nextLine();
        System.out.println("Salary of the director: ");
        int dsalary = input.nextInt();
        Director director = new Director(dname, dsurname, dsalary);


        System.out.println("Now let's create a coach, Name of the coach: ");
        String cname = input.next() + input.nextLine();
        System.out.println("Surname of the coach: ");
        String csurname = input.next() + input.nextLine();
        System.out.println("Salary of the coach: ");
        int csalary = input.nextInt();
        System.out.println("Year's experience of the coach: ");
        int cexp = input.nextInt();
        Coach coach = new Coach(cname, csurname, csalary, cexp);
        Team team = new Team(name, coach, director);

        stmt.executeUpdate("INSERT INTO allteams (name, league_id) VALUES (\""+ name +"\", "+lid+")");
        ResultSet rs = stmt.executeQuery("SELECT id FROM allteams WHERE name = \""+ name+"\" ORDER BY id DESC LIMIT 1 ");
        while(rs.next())
            tid = rs.getInt(1);

        System.out.println("Well! now let's create players!");
        boolean check = true;
        int c = 0;
        while(check){
            System.out.println("Name of the player: ");
            String pname = input.next() + input.nextLine();
            System.out.println("Surname of the player: ");
            String psurname = input.next() + input.nextLine();
            System.out.println("Salary of the player: ");
            int psalary = input.nextInt();
            System.out.println("Defence(exp) of the player:(int) ");
            int pdefence = input.nextInt();
            System.out.println("Attack(exp) of the player:(int) ");
            int patack = input.nextInt();
            Player player = null;
            System.out.println("Is the player a defender?(Yes/No): ");
            String ans1 = input.next() + input.nextLine();
            if(ans1.equalsIgnoreCase("NO")){
                System.out.println("Is the player a forward?(Yes/No): ");
                String ans2 = input.next() + input.nextLine();
                if(ans2.equalsIgnoreCase("NO")){
                    System.out.println("Ok, the player's position is a goalkeeper. ");
                    player = new Player.PlayerBuilder(pname, psurname, psalary, pdefence, patack)
                            .isGoalkeeper(true)
                            .build();
                }else{
                    player = new Player.PlayerBuilder(pname, psurname, psalary, pdefence, patack)
                            .isForward(true)
                            .build();
                }
            }else{
                player = new Player.PlayerBuilder(pname, psurname, psalary, pdefence, patack)
                        .isDefender(true)
                        .build();
            }
            players.add(player);
            team.addPlayer(player);
            c++;
            if(c > 4){
                System.out.println("Would you like to add more players?(Yes/No): ");
                String ans3 = input.next() + input.nextLine();
                if(ans3.equalsIgnoreCase("NO")){
                    check = false;
                }
            }
        }
        teams.add(team);
        league.addTeam(team); // addTeam should return a boolean!


        System.out.println("Would you like to create a team again?(Yes/No): ");
        String a = input.next() + input.nextLine();
        if(a.equalsIgnoreCase("YES")){
            createTeams(league);
        }else{
            leagueMenu(league);
        }
    }

    private static void leagueMenu(League league) throws SQLException {
        int n = 0;
        try{
            System.out.println("League Menu: ");
            System.out.println("Add team (press 1)");
            System.out.println("Delete team (press 2)");
            System.out.println("Show all teams (press 3)");
            System.out.println("Play match (press 4)");
            System.out.println("Show all players of the league (press 5)");
            n = input.nextInt();
        }catch (Exception e){
            System.out.println("Error!");
            leagueMenu(league);
        }
        switch(n) {
            case 1 :
                createTeams(league);
                break;
            case 2 :
                deleteTeam(league);
                break;
            case 3 :
                showTeams(league);
                break;
            case 4 :
                playMatch(league);
                break;
            case 5 :
                showPlayers(league);
                break;
            default:
                System.out.println("Wrong Command!");
        }
    }

    private static void deleteTeam(League league) throws SQLException {
        System.out.println("Name of the team that you want to delete: ");
        String name = input.next() + input.nextLine();
        league.deleteTeam(name);
        leagueMenu(league);
    }

    private static void showTeams(League league) throws SQLException {
        System.out.println(league.getTeams());
        leagueMenu(league);
    }

    private static void playMatch(League league) throws SQLException {
        //in process
        leagueMenu(league);
    }
    private static void showPlayers(League league) throws SQLException {
        //in process
        leagueMenu(league);
    }
}
