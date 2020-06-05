package League;

import java.util.ArrayList;

public class League{

    private String name;
    private ArrayList<Team> teams;

    public League(String name){
        this.name=name;
        teams=new ArrayList<Team>();
    }

    public String getLeagueinfo(){
        return getName()+"\n"+getTeams();
    }

    private String getName() {
        return name;
    }

    private String getTeams() {
        String q="";
        for (Team team:teams) {
            q+=team.getName();
            q+="\n";
        }
        return q;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addTeam(Team team) {
        boolean q=false;
        if(checkTeam()){

            if(!team.isInLeague()){
                this.teams.add(team);
                team.setInLeague(true);
            }

            else {
                System.out.println("This team is already participating in another league.");
            }
            q=true;

        }
        if(!q){
            System.out.println("You can't add any team to this league. It is already full.");
        }
    }

    private boolean checkTeam(){
        if(teams.size()==4){
            return false;
        }
        else return true;
    }

}
