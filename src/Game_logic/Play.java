package Game_logic;

import League.Team;

public class Play {

    private double ts1;
    private double ts2;

    private int r1,r2;

    public void Play(Team t1, Team t2){
             ts1+=t1.getStatsa()+t1.getStatsd();
             ts2+=t2.getStatsa()+t2.getStatsd();
             ts1/=100;
             ts2/=100;
             if(ts1>ts2){

                 if(ts1-ts2>2){
                    r1++;
                 }

                 else{

                 }

             }

             else{

             }
    }

}
