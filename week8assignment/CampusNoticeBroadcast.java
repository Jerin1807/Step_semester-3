import java.util.*;

public class CampusNoticeBroadcaster {
    interface NotificationChannel {
        void send(String studentName, String noticeTitle);
    }

    static class EmailChannel implements NotificationChannel {
        public void send(String name, String title) {
            System.out.println("[Email → " + name + "] " + title);
        }
    }

    static class SmsChannel implements NotificationChannel {
        public void send(String name, String title) {
            System.out.println("[SMS → " + name + "] " + title);
        }
    }

    static class AppChannel implements NotificationChannel {
        public void send(String name, String title) {
            System.out.println("[App → " + name + "] " + title);
        }
    }

    static class Student {
        final String name;
        final String department;
        final List<NotificationChannel> preferences;

        Student(String name, String department, List<NotificationChannel> preferences) {
            this.name = name;
            this.department = department;
            this.preferences = new ArrayList<>(preferences);
        }
    }

    static class Notice {
        final String title;
        final Set<String> departments;

        Notice(String title, Set<String> departments) {
            if (title == null || title.isBlank())
                throw new IllegalArgumentException("Notice title is required");
            if (departments == null || departments.isEmpty())
                throw new IllegalArgumentException("At least one target department is required");
            this.title = title;
            this.departments = new HashSet<>(departments);
        }
    }

    static class NoticeBoard {
        final List<Student> students = new ArrayList<>();

        void addStudent(Student student) { students.add(student); }

        void post(Notice notice) {
            System.out.println("Notice '" + notice.title + "' posted to "
                    + String.join(", ", notice.departments));
            for (Student student : students) {
                if (notice.departments.contains(student.department)) {
                    for (NotificationChannel channel : student.preferences)
                        channel.send(student.name, notice.title);
                }
            }
        }
    }
}