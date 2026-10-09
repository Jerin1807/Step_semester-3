public class ToyRobot extends Toy {
    private final String name;

    public ToyRobot(String name) {
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}