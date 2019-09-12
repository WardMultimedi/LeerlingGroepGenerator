package be.multimedi.StudGroupGenerator;

import be.multimedi.StudGroupGenerator.StudentGroup.StudentGroup;
import be.multimedi.StudGroupGenerator.TaskGroup.TaskGroup;
import be.multimedi.StudGroupGenerator.TaskGroup.TaskGroupDAO;
import be.multimedi.StudGroupGenerator.student.Student;
import be.multimedi.StudGroupGenerator.student.StudentDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 */
public final class App {
    private ConsoleTool ct = new ConsoleTool();

    public static void main(String[] args) {
        App app = new App();
        app.start();
        try {
            DriverManagerWrapper.closeConnection();
        } catch (SQLException se) {
            System.out.println("Error while closing DB: " + se.getMessage());
        }
    }

    private void start() {
        boolean running = true;
        while (running) {
            int choice;
            System.out.println("Menu:");
            System.out.println("1) Show students");
            System.out.println("2) Show task-group(s)");
            System.out.println("3) Create random task-group");
            System.out.println("4) Remove task-group");
            System.out.println("5) Create student");
            System.out.println("6) Remove student");
            System.out.println("0) Exit");
            choice = ct.askUserInputInteger("Your choice: ", 0, 6);
            switch (choice) {
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    showTaskGroup();
                    break;
                case 3:
                    createRandomTaskGroup();
                    break;
                case 4:
                    removeTaskGroup();
                    break;
                case 5:
                    createStudent();
                    break;
                case 6:
                    removeStudent();
                    break;
                default:
                    running = false;
            }
        }
    }

    private void showAllStudents() {
        List<Student> list = new StudentDAO().getAllStudents();
        System.out.println("Students " + list.size());
        int i = 1;
        for (Student s : list) {
            System.out.format("%d) %s\n", i++, s.toString());
        }
        ct.askPressEnterToContinue();
    }

    private void showTaskGroup() {
        String input = ct.askUserInputString("Geef de naam of nr van de groep: ", 1);
        TaskGroupDAO tgd = new TaskGroupDAO();
        TaskGroup tg;
        if (input.matches("\\d+")) {
            int id = Integer.parseInt(input);
            tg = tgd.getTaskGroup_byID(id);
            //System.out.println("by id");
        } else {
            tg = tgd.getTaskGroup_byName(input);
            //System.out.println("by Name");
        }
        if (tg == null) {
            System.out.println("No targetGroups found with id or name " + input);
            ct.askPressEnterToContinue();
            return;
        }
        System.out.println("Groep nr: " + tg.getId());
        System.out.println("Groep naam: " + tg.getName());
        System.out.println("Groep min lln: " + tg.getMinStudentsPerGroup());
        int i = 1;
        for (StudentGroup sg : tg.getStudGroups()) {
            String names = "";
            for (Student s : sg.getStudents()) {
                if (names.length() > 0) {
                    names += ", " + s.getFirstName();
                } else {
                    names = s.getFirstName();
                }
            }
            System.out.printf("%d) %s\n", i++, names);
        }
        ct.askPressEnterToContinue();
    }

    private void createRandomTaskGroup() {
        String name = ct.askUserInputString("TaskGroup name: ", 1);
        int min = ct.askUserInputInteger("minimum of students per group: ", 0);
        TaskGroupDAO tgd = new TaskGroupDAO();
        tgd.createTaskGroup(name, min);
    }

    private void removeTaskGroup() {
        String input = ct.askUserInputString("Geef de naam of nr van de groep: ", 1);
        TaskGroupDAO tgd = new TaskGroupDAO();
        TaskGroup tg;
        if (input.matches("\\d+")) {
            int id = Integer.parseInt(input);
            tgd.removeTaskGroup_byID(id);
        } else {
            tgd.removeTaskGroup_byName(input);
        }
    }

    private void removeStudent() {
        String input = ct.askUserInputString("Geef de voornaam of nr van de student: ", 1);
        StudentDAO sd = new StudentDAO();
        if (input.matches("\\d+")) {
            int id = Integer.parseInt(input);
            sd.removeStudent_byID(id);
        } else {
            sd.removeStudent_byFirstName(input);
        }
    }

    private void createStudent() {
        String firstName = ct.askUserInputString("Voornaam: ", 1);
        String lastName = ct.askUserInputString("achternaam: ", 1);
        Student stud = new Student(firstName, lastName);
        StudentDAO sd = new StudentDAO();
        sd.addStudent(stud);
    }
}