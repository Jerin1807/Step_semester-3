import java.time.*;
import java.util.*;

public class CampusTicketCounter {
    interface Seat {
        String id();
        double price();
    }

    static class RegularSeat implements Seat {
        final String id;
        RegularSeat(String id) { this.id = id; }
        public String id() { return id; }
        public double price() { return 150; }
    }

    static class PremiumSeat implements Seat {
        final String id;
        PremiumSeat(String id) { this.id = id; }
        public String id() { return id; }
        public double price() { return 250; }
    }

    static class ReclinerSeat implements Seat {
        final String id;
        ReclinerSeat(String id) { this.id = id; }
        public String id() { return id; }
        public double price() { return 400; }
    }

    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    static class Show {
        final String title;
        final LocalDateTime startsAt;
        private final Set<String> bookedSeatIds = new HashSet<>();

        Show(String title, LocalDateTime startsAt) {
            this.title = title; this.startsAt = startsAt;
        }

        Booking book(Customer customer, List<Seat> seats) {
            if (seats.isEmpty() || seats.size() > 6)
                throw new IllegalArgumentException("Booking must contain 1 to 6 seats");

            for (Seat seat : seats) {
                if (bookedSeatIds.contains(seat.id()))
                    throw new IllegalStateException("Seat " + seat.id() + " is already booked");
            }

            bookedSeatIds.addAll(seats.stream().map(Seat::id).toList());
            return new Booking(customer, this, seats);
        }
    }

    static class Booking {
        final Customer customer;
        final Show show;
        final List<Seat> seats;
        private boolean cancelled = false;

        Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
        }

        double total() {
            return seats.stream().mapToDouble(Seat::price).sum();
        }

        void cancel(LocalDateTime now) {
            if (cancelled) throw new IllegalStateException("Booking already cancelled");
            if (!now.isBefore(show.startsAt))
                throw new IllegalStateException("Cannot cancel after the show starts");
            for (Seat seat : seats) show.bookedSeatIds.remove(seat.id());
            cancelled = true;
        }
    }
}