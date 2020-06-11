package People;

public class Director extends Person {
    private int budget;

    public Director(String name, String surname, int salary) {
        super(name, surname, salary);
        this.budget = 5000;
    }

    public int subsBudget(){
        if(budget < 300){
            budget -= 300;
            return 300;
        }return 0;
    }
    public String toString(){
        return "Name and Surname: " + this.getName()+" "+this.getSurname()+", Budget: "+this.budget +", Salary: "+this.getSalary();
    }
}
