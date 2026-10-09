public class ScoutDrone extends Drone {
    private final String id;

    public ScoutDrone(String id) {
        this.id = id;
    }

    @Override
    public String fly() {
        return "Scout drone " + id + " is flying";
    }
}