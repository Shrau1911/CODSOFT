import java.util.ArrayList;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }
}

class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    private static ArrayList<Course> courseDatabase = new ArrayList<>();
    private static ArrayList<Student> studentDatabase = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        mainMenu();
    }

    private static void seedData() {
        courseDatabase.add(new Course("CS101", "Introduction to Programming", "Basic programming concepts", 30, "MWF 10:00-11:00"));
        courseDatabase.add(new Course("CS102", "Data Structures", "Learn about data organization", 25, "TTh 12:00-1:30"));
        courseDatabase.add(new Course("CS103", "Algorithms", "Problem-solving techniques", 20, "MWF 2:00-3:00"));
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register a Student");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. List Student's Registered Courses");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerStudent();
                    break;
                case 3:
                    registerCourse();
                    break;
                case 4:
                    dropCourse();
                    break;
                case 5:
                    listStudentCourses();
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase) {
            System.out.println("Course Code: " + course.code + ", Title: " + course.title + ", Description: " + course.description + ", Capacity: " + course.enrolled + "/" + course.capacity + ", Schedule: " + course.schedule);
        }
    }

    private static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student student = new Student(studentID, name);
        studentDatabase.add(student);

        System.out.println("Student registered successfully!");
    }

    private static Student findStudent(String studentID) {
        for (Student student : studentDatabase) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourse(String courseCode) {
        for (Course course : courseDatabase) {
            if (course.code.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static void registerCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);

        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        if (course.enrolled >= course.capacity) {
            System.out.println("Course is full!");
            return;
        }

        student.registeredCourses.add(course);
        course.enrolled++;
        System.out.println("Course registered successfully!");
    }

    private static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);

        if (course == null) {
            System.out.println("Course not found!");
            return;
        }

        if (!student.registeredCourses.contains(course)) {
            System.out.println("Student is not registered for this course!");
            return;
        }

        student.registeredCourses.remove(course);
        course.enrolled--;
        System.out.println("Course dropped successfully!");
    }

    private static void listStudentCourses() {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudent(studentID);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\nRegistered Courses:");
        for (Course course : student.registeredCourses) {
            System.out.println("Course Code: " + course.code + ", Title: " + course.title + ", Description: " + course.description + ", Schedule: " + course.schedule);
        }
    }
}
