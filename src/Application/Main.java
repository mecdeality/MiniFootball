package Application;

import Game_logic.Play;
import League.League;
import League.Team;
import People.Coach;
import People.Director;
import People.Player;

import java.sql.SQLException;
import java.util.Scanner;

//import static Application.Menu.displayUserMenu;
//import static Application.Menu.displayAdminMenu;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Director director = new Director("dname", "dsurname", 78);
        Director director2 = new Director("dname", "dsurname", 78);

        Coach coach = new Coach("cname", "csurname", 45, 4);
        Coach coach2 = new Coach("cname", "csurname", 45, 4);
        Team team = new Team("Arsenal", coach, director);
        Team team2 = new Team("Arga", coach2, director2);

        Player player = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isGoalkeeper(true)
                .build();
        Player player2 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();
        Player player3 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();
        Player player4 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();
        Player player5 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();

        Player player6 = new Player.PlayerBuilder("pname", "psurname", 55, 55, 55)
                .isGoalkeeper(true)
                .build();
        Player player7 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();
        Player player8 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();
        Player player9 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();
        Player player10 = new Player.PlayerBuilder("pname", "psurname", 45, 45, 45)
                .isDefender(true)
                .build();

        team.addPlayer(player);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);

        team2.addPlayer(player6);
        team2.addPlayer(player7);
        team2.addPlayer(player8);
        team2.addPlayer(player9);
        team2.addPlayer(player10);

        League l = new League("Adro");

        l.addTeam(team);
        l.addTeam(team2);

        System.out.println(Play.Play(team, team2));
    }
}
