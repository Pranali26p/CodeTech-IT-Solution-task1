import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int rollNumber;
    int[] subjectMarks;

    public Student(String name, int rollNumber, int[] subjectMarks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = subjectMarks;
    }
}

class GradeManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static final int NUM_SUBJECTS = 3; // Assuming 3 subjects
    private static final int PASSING_MARKS = 40; // Change this based on your criteria

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.next();

        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();

        int[] subjectMarks = new int[NUM_SUBJECTS];
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            subjectMarks[i] = scanner.nextInt();
        }

        Student newStudent = new Student(name, rollNumber, subjectMarks);
        students.add(newStudent);

        System.out.println("Student added successfully!");
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter the roll number of the student to update: ");
        int rollNumber = scanner.nextInt();

        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                System.out.print("Enter new marks for subject 1: ");
                student.subjectMarks[0] = scanner.nextInt();

                System.out.print("Enter new marks for subject 2: ");
                student.subjectMarks[1] = scanner.nextInt();

                System.out.print("Enter new marks for subject 3: ");
                student.subjectMarks[2] = scanner.nextInt();

                System.out.println("Student information updated successfully!");
                return;
            }
        }

        System.out.println("Student not found with roll number: " + rollNumber);
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter the roll number of the student to delete: ");
        int rollNumber = scanner.nextInt();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).rollNumber == rollNumber) {
                students.remove(i);
                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student not found with roll number: " + rollNumber);
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("RollNumber\tName\t\tPercentage\tGrade");
            for (Student student : students) {
                double percentage = calculatePercentage(student.subjectMarks);
                String grade = calculateGrade(percentage);

                System.out.println(student.rollNumber + "\t\t" + student.name + "\t\t" + percentage + "%\t\t" + grade);
            }
        }
    }

    private static double calculatePercentage(int[] marks) {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return (double) totalMarks / NUM_SUBJECTS;
    }

    private static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
