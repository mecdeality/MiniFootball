package Game_logic;

import League.Team;

public class Play {

    private static double ts1;
    private static double ts2;

    private static int r1,r2;

    public static String Play(Team t1, Team t2){

             if(t1.isInLeague()&&t2.isInLeague()) {

                 int stat1 = 0;
                 int stat2 = 0;

                 ts1 += t1.getStatsa() + t1.getStatsd();
                 ts2 += t2.getStatsa() + t2.getStatsd();
                 ts1 /= 50;
                 ts2 /= 50;


                 for (int i = 0; i < 5; i++) {
                     String q = strongT(ts1, ts2);
                     char c1 = q.charAt(0);
                     char c2 = q.charAt(2);

                     r1 = Character.getNumericValue(c1);
                     r2 = Character.getNumericValue(c2);

                     if (r1 - r2 >=1) {
                         team1g();
                         stat1++;
                     }

                     else if (r2 - r1 >= 1) {
                         team2g();
                         stat2++;
                     }


                     else {

                         if (1 + (int) Math.random() * 2 == 1) {
                             team1s();
                         }

                         else team2s();

                     }


                 }


                 String str = "\nTeam1 wins";
                 if (stat2 > stat1) {
                     t1.subsBudget();
                     t2.addBudget();
                     str = "\nTeam 2 wins";
                 } else if (stat1 > stat2) {
                     t2.subsBudget();
                     t1.addBudget();
                 } else if(stat1==stat2) {
                     str = "\n DRAW";
                 }

                 String q = Integer.toString(stat1) + " " + Integer.toString(stat2);

                 q += str;

                 return q;
             }
             else return "One of the team is not in League";
    }

    public static String strongT(double ts1, double ts2){

        int q1,q2;

        if(ts1>ts2){

            if(ts1-ts2>1){
                q1=4 + (int) (Math.random()*4);
                q2=2 + (int) (Math.random()*5);
            }

            else {
                q1=2 + (int) (Math.random()*3);
                q2=2 + (int) (Math.random()*3);
            }

        }

        else{
            if(ts2-ts1>1){
                q2=4 + (int) (Math.random()*4);
                q1=2 + (int) (Math.random()*5);
            }

            else {
                q1=2 + (int) (Math.random()*3);
                q2=2 + (int) (Math.random()*3);
            }
        }
        String q  =Integer.toString(q1)+" "+Integer.toString(q2);
        return q;
    }

    private  static String a1 = "Team1 makes an attack, he kicks... Ohhhh MY GOOD what a beautiful safe!!!";
    private  static String a2 = "Team2 makes an attack, he kicks... Ohhhh MY GOOD what a beautiful safe!!!";
    private  static String[] s1 = new String[] {"What an incredible goal.", "GOAL! GOAL! GOAL!", "And again GOOOAAAAAAAL!!! We are watching history dear friends."};
    private  static String[] st1 = new String[] {"Today team1 is on fire.", "It is definitely team1's day."};
    private  static String[] st2 = new String[] {"Today team2 is on fire.", "It is definitely team2's day."};

    public static String team1g(){
        int g = (int) (Math.random() * 3);
        int g1 = (int) (Math.random() * 2);
        String q1 = s1[g];
        return q1+st1[g1];
    }

    public static String team2g(){
        int g = (int) (Math.random() * 3);
        int g1 = (int) (Math.random() * 2);
        String q1 = s1[g];
        return q1+st2[g1];
    }

    public static String team1s(){
        return a2;
    }

    public static String team2s(){
        return a1;
    }


}
