import java.time.LocalDate;

public class LeaveWorkflow {
    enum Status { PENDING, APPROVED, REJECTED }

    interface LeavePolicy {
        boolean allows(int days);
    }

    static class Employee {
        final String name;
        final LeavePolicy policy;

        Employee(String name, LeavePolicy policy) {
            this.name = name;
            this.policy = policy;
        }

        LeaveRequest submit(LocalDate start, LocalDate end) {
            int days = (int) (end.toEpochDay() - start.toEpochDay() + 1);
            if (days <= 0 || !policy.allows(days))
                throw new IllegalArgumentException("Leave request violates policy");
            return new LeaveRequest(this, start, end);
        }
    }

    static class LeaveRequest {
        final Employee employee;
        final LocalDate start, end;
        private Status status = Status.PENDING;

        LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
            this.employee = employee;
            this.start = start;
            this.end = end;
        }

        void review(boolean approved) {
            if (status != Status.PENDING)
                throw new IllegalStateException("Request has already been reviewed");
            status = approved ? Status.APPROVED : Status.REJECTED;
        }

        Status getStatus() { return status; }
    }

    static class Manager {
        final String name;
        Manager(String name) { this.name = name; }
        void review(LeaveRequest request, boolean approve) {
            request.review(approve);
        }
    }

    public static void main(String[] args) {
        Employee john = new Employee("John", days -> days <= 20);
        LeaveRequest request = john.submit(
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        new Manager("Alice").review(request, true);
        System.out.println(request.getStatus()); // APPROVED
    }
}