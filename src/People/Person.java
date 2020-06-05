package People;

public abstract class Person {
    private int idPerson;
    private static int idP = 0;
    private String name;
    private String surname;
    private int salary;

    public Person(String name, String surname, int salary){
        this.idPerson = idP;
        idP++;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
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

    public int getSalary() {
        return salary;
    }
}
