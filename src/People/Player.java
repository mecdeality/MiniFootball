package People;

public class Player extends Person{
    private int defence;
    private int attack;
    private boolean isForward;
    private boolean isDefender;
    private boolean isGoalkeeper;



    public Player(PlayerBuilder p) {
        super(p.name, p.surname, p.position, p.salary);
        this.attack = p.attack;
        this.defence = p.defence;
        this.isForward = p.isForward;
        this.isDefender = p.isDefender;
        this.isGoalkeeper = p.isGoalkeeper;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public boolean isGoalkeeper() {
        return isGoalkeeper;
    }

    public boolean isDefender() {
        return isDefender;
    }

    public boolean isForward() {
        return isForward;
    }

    public static class PlayerBuilder{
        private String name;
        private String surname;
        private String position;
        private int salary;
        private int defence;
        private int attack;
        private boolean isForward;
        private boolean isDefender;
        private boolean isGoalkeeper;

        public PlayerBuilder(String name, String surname, String position, int salary, int defence, int attack){
            this.name = name;
            this.surname = surname;
            this.position = position;
            this.salary = salary;
            this.defence = defence;
            this.attack = attack;
        }
        public PlayerBuilder isForward(boolean a){
            this.isForward = a;
            return this;
        }
        public PlayerBuilder isDefender(boolean a){
            this.isDefender = a;
            return this;
        }
        public PlayerBuilder isGoalkeeper(boolean a){
            this.isGoalkeeper = a;
            return this;
        }

        public Player build(){
            Player player = new Player(this);
            return player;
        }

    }
}
