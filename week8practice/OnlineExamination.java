import java.util.*;

public class OnlineExamination {
    static class Student {
        final String name;
        Student(String name) { this.name = name; }
    }

    static class Question {
        final String id;
        final int points;
        final String correctAnswer;

        Question(String id, int points, String correctAnswer) {
            this.id = id;
            this.points = points;
            this.correctAnswer = correctAnswer;
        }

        int score(String answer) {
            return correctAnswer.equalsIgnoreCase(answer) ? points : 0;
        }
    }

    static class Examination {
        final String title;
        final List<Question> questions;
        private final Set<Student> submittedStudents =
                Collections.newSetFromMap(new IdentityHashMap<>());

        Examination(String title, List<Question> questions) {
            this.title = title;
            this.questions = questions;
        }

        Attempt start(Student student) {
            if (submittedStudents.contains(student))
                throw new IllegalStateException("Student already submitted an attempt");
            return new Attempt(student, this);
        }
    }

    static class Attempt {
        final Student student;
        final Examination exam;
        private final Map<Question, String> answers = new HashMap<>();
        private boolean submitted = false;

        Attempt(Student student, Examination exam) {
            this.student = student;
            this.exam = exam;
        }

        void answer(Question question, String answer) {
            if (submitted) throw new IllegalStateException(
                    "Cannot change answers for a submitted examination");
            if (!exam.questions.contains(question))
                throw new IllegalArgumentException("Question is not in this exam");
            answers.put(question, answer);
        }

        int submit() {
            if (submitted) throw new IllegalStateException("Attempt already submitted");
            submitted = true;
            exam.submittedStudents.add(student);

            int total = 0;
            for (Question q : exam.questions)
                total += q.score(answers.getOrDefault(q, ""));
            return total;
        }
    }
}