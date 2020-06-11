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

import static Application.UserMenu.displayUserMenu;


public abstract class AdminMenu {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Team> teams = new ArrayList<Team>();
    private static ArrayList<Player> players = new ArrayList<Player>();
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

    public static void displayAdminMenu() throws SQLException {
        int n = 0;
        do {
            try {
                trycatch = false;
                System.out.println("Menu: ");
                System.out.println("Create new league (press 1)");
                System.out.println("Show information about finished leagues (press 2)");
                n = input.nextInt();
            } catch (Exception e) {
                input.next();
                trycatch = true;
                System.out.println("Error!");
            }
        }while (trycatch == true);
        switch(n) {
            case 1 :
                addLeague();
                break;
            case 2 :
                UserMenu();
                break;
            default:
                System.out.println("Wrong Command!");
        }
    }
    public static void UserMenu() throws SQLException {
        System.out.println("You're logged as an user");
        displayUserMenu();
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

        stmt.executeUpdate("INSERT INTO allteams (name, budget, league_id) VALUES (\""+ name +"\", \""+team.getBudget()+"\", "+lid+")");
        ResultSet rs = stmt.executeQuery("SELECT id FROM allteams WHERE name = \""+ name+"\" ORDER BY id DESC LIMIT 1 ");
        while(rs.next())
            tid = rs.getInt(1);

        stmt.executeUpdate("INSERT INTO alldirectors (name, surname, budget, salary, team_id) VALUES (\""+ director.getName() +"\", \""+director.getSurname()+"\", \""+director.getBudget()+"\", \""+director.getSalary()+"\", "+tid+")");
        stmt.executeUpdate("INSERT INTO allcoaches (name, surname, exp, salary, team_id) VALUES (\""+ coach.getName() +"\", \""+coach.getSurname()+"\", \""+coach.getExperienceYear()+"\", \""+coach.getSalary()+"\", "+tid+")");

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

            stmt.executeUpdate("INSERT INTO allplayers (name, surname, position , salary, defence, attack,team_id) VALUES (\""+ player.getName() +"\", \""+player.getSurname()+"\", \""+player.getPostition()+"\", \""+player.getSalary()+"\", \""+player.getDefence()+"\", \""+player.getAttack()+"\", "+tid+")");
            stmt.executeUpdate("UPDATE allteams SET players = players+1 WHERE id = "+tid);

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
        stmt.executeUpdate("UPDATE leagues SET teams = teams+1 WHERE id = "+lid);

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
            System.out.println("Show all teams (press 2)");
            System.out.println("Play match (press 3)");
            System.out.println("Show all players of the league (press 4)");
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
                showTeams(league);
                break;
            case 3 :
                playMatch(league);
                break;
            case 4 :
                showPlayers(league);
                break;
            default:
                System.out.println("Wrong Command!");
        }
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
        System.out.println("Write the team name that you want to see the players: ");
        String name = input.next() + input.nextLine();
        ArrayList<Team> teams = league.getTeamArray();
        for(Team x : teams){
            if(x.getName().equalsIgnoreCase(name)){
                x.getPlayers();
                leagueMenu(league);
            }else{
                System.out.println("Oops, wrong team name, please try again.");
                showPlayers(league);
            }
        }
//        t.getPlayers();
//        leagueMenu(league);
    }
}
