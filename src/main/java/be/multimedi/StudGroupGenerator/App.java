package be.multimedi.StudGroupGenerator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    //private Scanner keyb = new Scanner(System.in);
    private ConsoleTool ct = new ConsoleTool();

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    private void start() {
        boolean running = true;
        while (running) {
            int choice;
//            do {
//                choice = -1;

                System.out.println("1) Show students");
                System.out.println("2) Show student groups");
                System.out.println("3) Create random groups");
                System.out.println("4) Remove group");
                System.out.println("5) Create student");
                System.out.println("6) Remove student");
                System.out.println("0) Exit");
//                System.out.print("Your choice: ");
                choice = ct.askUserInputInteger("Your choice: ", 0, 6);
//                try {
//                    choice = keyb.nextInt();
//                } catch (InputMismatchException ime) {
//                    System.out.println(ime.getMessage());
//                } finally {
//                    keyb.nextLine();
//                }
//            } while (choice < 0 || choice > 6);
            switch (choice) {
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    //TODO: Show student groups
                    break;
                case 3:
                    //TODO: Create random group
                    break;
                case 4:
                    //TODO: Remove group
                    break;
                case 5:
                    //TODO: Create Student
                    break;
                case 6:
                    //TODO: Remove Student
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
}