import java.util.*;

public class HostelLaundryQueue {
    interface WashType {
        int durationMinutes();
        double charge();
        String name();
    }

    static class Quick implements WashType {
        public int durationMinutes() { return 30; }
        public double charge() { return 20; }
        public String name() { return "Quick"; }
    }

    static class Normal implements WashType {
        public int durationMinutes() { return 45; }
        public double charge() { return 30; }
        public String name() { return "Normal"; }
    }

    static class Heavy implements WashType {
        public int durationMinutes() { return 60; }
        public double charge() { return 45; }
        public String name() { return "Heavy"; }
    }

    static class Student {
        final String name;
        Student(String name) { this.name = name; }
    }

    static class WashingMachine {
        final String id;
        private WashCycle activeCycle;

        WashingMachine(String id) { this.id = id; }
        boolean isFree() { return activeCycle == null; }

        WashCycle start(Student student, WashType type) {
            if (!isFree()) return null;
            activeCycle = new WashCycle(student, this, type);
            return activeCycle;
        }

        void complete() {
            if (activeCycle != null) {
                System.out.println(id + " cycle completed. " + id + " is now free.");
                activeCycle = null;
            }
        }
    }

    static class WashCycle {
        final Student student;
        final WashingMachine machine;
        final WashType type;

        WashCycle(Student student, WashingMachine machine, WashType type) {
            this.student = student;
            this.machine = machine;
            this.type = type;
        }
    }

    static void startWash(WashingMachine machine, Student student, WashType type) {
        WashCycle cycle = machine.start(student, type);
        if (cycle == null) {
            System.out.println("Machine " + machine.id + " is currently busy.");
        } else {
            System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                    type.name(), machine.id, student.name,
                    type.durationMinutes(), type.charge());
        }
    }
}