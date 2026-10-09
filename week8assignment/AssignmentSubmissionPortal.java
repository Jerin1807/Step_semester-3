import java.time.*;
import java.util.*;

public class AssignmentSubmissionPortal {
    enum Status { SUBMITTED, GRADED }

    interface AssignmentType {
        String title();
        LocalDate dueDate();
        int maxMarks();
        double applyLatePenalty(double marks, long lateDays);
    }

    static class CodingAssignment implements AssignmentType {
        final String title;
        final LocalDate due;
        final int max;

        CodingAssignment(String title, LocalDate due, int max) {
            this.title = title; this.due = due; this.max = max;
        }

        public String title() { return title; }
        public LocalDate dueDate() { return due; }
        public int maxMarks() { return max; }
        public double applyLatePenalty(double marks, long days) {
            return marks * Math.max(0, 1 - 0.10 * days);
        }
    }

    static class WrittenAssignment implements AssignmentType {
        final String title;
        final LocalDate due;
        final int max;

        WrittenAssignment(String title, LocalDate due, int max) {
            this.title = title; this.due = due; this.max = max;
        }

        public String title() { return title; }
        public LocalDate dueDate() { return due; }
        public int maxMarks() { return max; }
        public double applyLatePenalty(double marks, long days) {
            return marks * Math.max(0, 1 - 0.20 * days);
        }
    }

    static class Student {
        final String name;
        Student(String name) { this.name = name; }
    }

    static class Submission {
        final Student student;
        final AssignmentType assignment;
        final LocalDate submittedOn;
        private Status status = Status.SUBMITTED;
        private double finalMarks;

        Submission(Student student, AssignmentType assignment, LocalDate date) {
            this.student = student;
            this.assignment = assignment;
            this.submittedOn = date;
        }

        double grade(double awardedMarks) {
            if (status == Status.GRADED)
                throw new IllegalStateException("Submission is already graded");
            if (awardedMarks < 0 || awardedMarks > assignment.maxMarks())
                throw new IllegalArgumentException("Marks out of range");

            long lateDays = Math.max(0,
                    ChronoUnit.DAYS.between(assignment.dueDate(), submittedOn));
            finalMarks = assignment.applyLatePenalty(awardedMarks, lateDays);
            status = Status.GRADED;
            return finalMarks;
        }

        Status getStatus() { return status; }

        void resubmit() {
            if (status == Status.GRADED)
                throw new IllegalStateException("Cannot resubmit: assignment has already been graded");
            throw new IllegalStateException("Resubmission behavior is not enabled");
        }
    }

    static Submission submit(Student student, AssignmentType assignment, LocalDate date) {
        return new Submission(student, assignment, date);
    }
}