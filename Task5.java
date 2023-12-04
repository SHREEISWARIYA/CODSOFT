import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<String> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(String studentID) {
        if (registeredStudents.size() < capacity && !registeredStudents.contains(studentID)) {
            registeredStudents.add(studentID);
            return true; // Registration successful
        } else {
            return false; // Registration unsuccessful (full capacity or already registered)
        }
    }

    public boolean dropStudent(String studentID) {
        return registeredStudents.remove(studentID); // Remove the student from the course
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some sample courses
        Course c1 = new Course("CSE101", "Introduction to Computer Science", "Fundamental concepts of programming", 30, "Mon/Wed 10:00 AM");
        Course c2 = new Course("MATH201", "Calculus II", "Advanced calculus topics", 25, "Tue/Thu 2:00 PM");
        Course c3 = new Course("ENG102", "English Composition", "Writing and communication skills", 20, "Mon/Fri 1:00 PM");

        // Create some sample students
        Student s1 = new Student("S001", "John Doe");
        Student s2 = new Student("S002", "Jane Smith");

        // Display available courses
        System.out.println("Available Courses:");
        displayCourseDetails(c1);
        displayCourseDetails(c2);
        displayCourseDetails(c3);

        // Allow the user to add a new course
        System.out.println("Do you want to add a new course? (yes/no)");
        String addCourseChoice = scanner.nextLine().toLowerCase();

        if (addCourseChoice.equals("yes")) {
            Course newCourse = createNewCourse(scanner);
            displayCourseDetails(newCourse);

            // You can add the new course to a list of courses or perform other actions as needed
        } else {
            System.out.println("No new course added.");
        }

        // Allow the user to register a student for a course
        System.out.println("Do you want to register a student for a course? (yes/no)");
        String registerStudentChoice = scanner.nextLine().toLowerCase();

        if (registerStudentChoice.equals("yes")) {
            System.out.println("Enter student ID:");
            String studentID = scanner.nextLine();

            // Check if the student exists
            Student student = findStudentById(studentID);
            if (student != null) {
                // Display available courses
                System.out.println("Available Courses:");
                displayCourseDetails(c1);
                displayCourseDetails(c2);
                displayCourseDetails(c3);

                System.out.println("Enter course code to register for:");
                String courseCode = scanner.nextLine();

                // Check if the course exists
                Course course = findCourseByCode(courseCode);
                if (course != null) {
                    // Register the student for the course
                    if (course.registerStudent(studentID)) {
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Registration unsuccessful. Full capacity or already registered.");
                    }
                } else {
                    System.out.println("Course not found.");
                }
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("No student registered.");
        }

        // Allow the user to drop a student from a course
        System.out.println("Do you want to drop a student from a course? (yes/no)");
        String dropStudentChoice = scanner.nextLine().toLowerCase();

        if (dropStudentChoice.equals("yes")) {
            System.out.println("Enter student ID:");
            String studentID = scanner.nextLine();

            // Check if the student exists
            Student student = findStudentById(studentID);
            if (student != null) {
                // Display courses registered by the student
                displayStudentCourses(student);

                System.out.println("Enter course code to drop:");
                String courseCode = scanner.nextLine();

                // Check if the course exists
                Course course = findCourseByCode(courseCode);
                if (course != null) {
                    // Drop the student from the course
                    if (course.dropStudent(studentID)) {
                        System.out.println("Student dropped from the course.");
                    } else {
                        System.out.println("Student not registered for the course.");
                    }
                } else {
                    System.out.println("Course not found.");
                }
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("No student dropped.");
        }

        // Close the scanner
        scanner.close();
    }

    // Helper method to create a new course
    private static Course createNewCourse(Scanner scanner) {
        System.out.println("Enter course code:");
        String newCourseCode = scanner.nextLine();

        System.out.println("Enter course title:");
        String newCourseTitle = scanner.nextLine();

        System.out.println("Enter course description:");
        String newCourseDescription = scanner.nextLine();

        System.out.println("Enter course capacity:");
        int newCourseCapacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter course schedule:");
        String newCourseSchedule = scanner.nextLine();

        return new Course(newCourseCode, newCourseTitle, newCourseDescription, newCourseCapacity, newCourseSchedule);
    }

    // Helper method to display course details
    private static void displayCourseDetails(Course course) {
        System.out.println("Course Code: " + course.getCourseCode());
        System.out.println("Title: " + course.getTitle());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Capacity: " + course.getCapacity());
        System.out.println("Schedule: " + course.getSchedule());
        System.out.println();
    }

    // Helper method to display courses registered by a student
    private static void displayStudentCourses(Student student) {
        System.out.println("Registered Courses for " + student.getName() + " (" + student.getStudentID() + "):");
        for (String courseCode : student.getRegisteredCourses()) {
            System.out.println(courseCode);
        }
        System.out.println();
    }

    // Helper method to find a student by ID
    private static Student findStudentById(String studentID) {
        // Implement logic to search for the student in the list of students
        // Return null if not found (you need to implement this logic)
        return null;
    }

    // Helper method to find a course by code
    private static Course findCourseByCode(String courseCode) {
        // Implement logic to search for the course in the list of courses
        // Return null if not found (you need to implement this logic)
        return null;
    }
}
