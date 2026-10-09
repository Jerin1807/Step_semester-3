import java.time.LocalDate;
import java.util.*;

public class HotelBookingSystem {
    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    abstract static class Room {
        final String id;
        Room(String id) { this.id = id; }
        abstract double pricePerNight();

        double calculatePrice(LocalDate start, LocalDate end) {
            long nights = end.toEpochDay() - start.toEpochDay();
            if (nights <= 0) throw new IllegalArgumentException("Invalid date range");
            return nights * pricePerNight();
        }
    }

    static class StandardRoom extends Room {
        StandardRoom(String id) { super(id); }
        double pricePerNight() { return 100; }
    }

    static class DeluxeRoom extends Room {
        DeluxeRoom(String id) { super(id); }
        double pricePerNight() { return 180; }
    }

    static class Reservation {
        final Customer customer;
        final Room room;
        final LocalDate start, end, cancellationDeadline;
        boolean active = true;

        Reservation(Customer c, Room r, LocalDate start, LocalDate end,
                    LocalDate cancellationDeadline) {
            customer = c;
            room = r;
            this.start = start;
            this.end = end;
            this.cancellationDeadline = cancellationDeadline;
        }

        boolean overlaps(LocalDate otherStart, LocalDate otherEnd) {
            return active && start.isBefore(otherEnd) && otherStart.isBefore(end);
        }
    }

    static class BookingService {
        final List<Reservation> reservations = new ArrayList<>();

        boolean isAvailable(Room room, LocalDate start, LocalDate end) {
            return reservations.stream().noneMatch(r ->
                    r.room == room && r.overlaps(start, end));
        }

        Reservation reserve(Customer customer, Room room,
                            LocalDate start, LocalDate end,
                            LocalDate cancellationDeadline) {
            if (!isAvailable(room, start, end)) return null;
            Reservation r = new Reservation(customer, room, start, end,
                    cancellationDeadline);
            reservations.add(r);
            System.out.println("Reservation confirmed for " + customer.name
                    + ", " + room.id + ". Price: $"
                    + room.calculatePrice(start, end));
            return r;
        }

        boolean cancel(Reservation r, LocalDate today) {
            if (r == null || !r.active || today.isAfter(r.cancellationDeadline))
                return false;
            r.active = false;
            return true;
        }
    }
}