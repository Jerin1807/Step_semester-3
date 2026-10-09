import java.util.*;

public class VehicleRentalSystem {
    abstract static class Vehicle {
        final String id;
        private boolean available = true;

        Vehicle(String id) { this.id = id; }
        abstract double calculateCharge(int days);

        boolean isAvailable() { return available; }
        void setAvailable(boolean value) { available = value; }
    }

    static class Sedan extends Vehicle {
        Sedan(String id) { super(id); }
        double calculateCharge(int days) { return days * 50.0; }
    }

    static class SUV extends Vehicle {
        SUV(String id) { super(id); }
        double calculateCharge(int days) { return days * 80.0; }
    }

    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    static class Rental {
        final Vehicle vehicle;
        final Customer customer;
        final int days;
        final double charge;
        boolean active = true;

        Rental(Vehicle v, Customer c, int days) {
            vehicle = v;
            customer = c;
            this.days = days;
            charge = v.calculateCharge(days);
        }
    }

    static class RentalService {
        List<Rental> rentals = new ArrayList<>();

        Rental rent(Vehicle v, Customer c, int days) {
            if (!v.isAvailable() || days <= 0) return null;
            Rental rental = new Rental(v, c, days);
            v.setAvailable(false);
            rentals.add(rental);
            System.out.println(v.id + " rented successfully by " + c.name
                    + ". Rental charge: $" + rental.charge);
            return rental;
        }

        void returnVehicle(Rental rental) {
            if (rental != null && rental.active) {
                rental.active = false;
                rental.vehicle.setAvailable(true);
                System.out.println(rental.vehicle.id + " returned by "
                        + rental.customer.name);
            }
        }
    }
}