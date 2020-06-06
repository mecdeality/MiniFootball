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

    public String getName() {
        return name;
    }

    public  String getTeams() {
        String q="";
        for (Team team:teams) {
            q+=team.getName();
            q+="\n";
        }
        return q;
    }
    public ArrayList<Team> getTeamArray(){
        return teams;
    }

    public void deleteTeam(String name){
        for(Team t : teams){
            if(t.getName().equalsIgnoreCase(name)){
                teams.remove(t);
                System.out.println("Team \"" + name + "\" removed. ");
                return;
            }
        }System.out.println("There is no such team in league. ");
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addTeam(Team team) {
        boolean q=false;
        if(canJoin(team)){

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
        else System.out.println("Team can not join to the league because there are not enough players.");

    }

    private boolean checkTeam(){
        if(teams.size()==4){
            return false;
        }
        else return true;
    }

    private boolean canJoin(Team team){
        if(team.isFull()){
            return true;
        }
        else return false;
    }

}
