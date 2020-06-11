package People;

public class Coach extends Person {
    private int experienceYear;

    public Coach(String name, String surname, int salary, int experienceYear) {
        super(name, surname, salary);
        this.experienceYear = experienceYear;
    }

    public int getExperienceYear() {
        return experienceYear;
    }
    public String toString(){
        return "Name and Surname: " + this.getName()+" "+this.getSurname()+", Year of experience: "+this.experienceYear +", Salary: "+this.getSalary();
    }
}
