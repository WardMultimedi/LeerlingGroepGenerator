package be.multimedi.StudGroupGenerator;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    private ConsoleTool ct = new ConsoleTool();

    public static void main(String[] args) {
        App app = new App();
        app.start();
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
                    //TODO: Create random task group
                    break;
                case 4:
                    //TODO: Remove task group
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

    private void showTaskGroup() {
        String input = ct.askUserInputString("Geef de naam of nr van de groep: ", 1);
        TaskGroupDAO tgd = new TaskGroupDAO();
        TaskGroup tg;
        if (input.matches("\\d+")) {
            int id = Integer.parseInt(input);
            tg = tgd.getTaskGroup_byID(id);
            System.out.println("by id");
        } else {
            tg = tgd.getTaskGroup_byName(input);
            System.out.println("by Name");
        }
        if( tg == null){
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
}