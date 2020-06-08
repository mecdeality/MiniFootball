package Game_logic;

import java.util.ArrayList;

public class Commentator {

    private  String a1 = "Team1 makes an attack, he kicks... Ohhhh MY GOOD what a beautiful safe!!!";
    private  String a2 = "Team2 makes an attack, he kicks... Ohhhh MY GOOD what a beautiful safe!!!";
    private  String[] s1 = new String[] {"What an incredible goal.", "GOAL! GOAL! GOAL!", "And again GOOOAAAAAAAL!!! We are watching history dear friends."};
    private  String[] st1 = new String[] {"Today team1 is on fire.", "It is definitely team1's day."};
    private  String[] st2 = new String[] {"Today team2 is on fire.", "It is definitely team2's day."};

    public String team1g(){
        int g = (int) (Math.random() * 3);
        int g1 = (int) (Math.random() * 2);
        String q1 = s1[g];
        return q1+st1[g1];
    }

    public String team2g(){
        int g = (int) (Math.random() * 3);
        int g1 = (int) (Math.random() * 2);
        String q1 = s1[g];
        return q1+st2[g1];
    }

    public String team1s(){
        return a2;
    }

    public String team2s(){
        return a1;
    }



}
