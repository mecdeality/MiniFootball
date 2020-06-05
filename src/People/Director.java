package People;

public class Director extends Person {
    private int budget;

    public Director(String name, String surname, int salary, int budget) {
        super(name, surname, salary);
        this.budget = budget;
    }

    public int getBudget(){
        if(budget < 300){
            budget -= 300;
            return 300;
        }return 0;
    }
}
