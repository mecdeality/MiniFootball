package People;

public abstract class Person {
    private String name;
    private String surname;
    private String position;
    private int salary;

    public Person(String name, String surname, String position, int salary){
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }
}
