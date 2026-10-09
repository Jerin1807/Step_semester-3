import java.util.*;

public class ShoppingPayment {
    static class Customer {
        final String name;
        Customer(String name) { this.name = name; }
    }

    static class Product {
        final String name;
        final double price;
        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    enum OrderStatus { PENDING, PAID }

    interface PaymentMethod {
        boolean processPayment(double amount);
    }

    static class CreditCardPayment implements PaymentMethod {
        public boolean processPayment(double amount) { return true; }
    }

    static class PayPalPayment implements PaymentMethod {
        public boolean processPayment(double amount) { return false; }
    }

    static class Order {
        final Customer customer;
        private final Map<Product, Integer> items = new LinkedHashMap<>();
        private OrderStatus status = OrderStatus.PENDING;

        Order(Customer customer) { this.customer = customer; }

        void addProduct(Product product, int quantity) {
            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
            items.merge(product, quantity, Integer::sum);
        }

        double total() {
            return items.entrySet().stream()
                    .mapToDouble(e -> e.getKey().price * e.getValue()).sum();
        }

        boolean pay(PaymentMethod method) {
            if (items.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return false;
            }
            if (status == OrderStatus.PAID) return true;

            boolean success = method.processPayment(total());
            if (success) status = OrderStatus.PAID;
            return success;
        }

        OrderStatus getStatus() { return status; }
    }
}