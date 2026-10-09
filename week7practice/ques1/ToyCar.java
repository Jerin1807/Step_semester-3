public class ToyCar extends Toy {
    private final String name;

    public ToyCar(String name) {
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}