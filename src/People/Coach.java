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
}
