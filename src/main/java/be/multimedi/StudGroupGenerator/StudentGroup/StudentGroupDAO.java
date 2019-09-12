package be.multimedi.StudGroupGenerator.StudentGroup;

import be.multimedi.StudGroupGenerator.DriverManagerWrapper;
import be.multimedi.StudGroupGenerator.student.Student;
import be.multimedi.StudGroupGenerator.student.StudentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentGroupDAO {
    private static String sqlGetAllStudentGroups = "SELECT * FROM StudentGroups";
    private static String sqlGetStudentGroup_byTaskGroup = "SELECT * FROM StudentGroups WHERE taskGroup_id = ?";
    private static String sqlCreateStudentGroup = "INSERT INTO StudentGroups(taskGroup_id) VALUES(?)";
    private static String sqlCreateStudentGroupConnection = "INSERT INTO StudentGroupConnections(student_id, studGroup_id) VALUES(?, ?)";

    public void createStudentGroup(int taskGroupID, List<Student> studentList) {
        //Create StudentGroup
        Integer studentGroupID = null;
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (PreparedStatement stmt = con.prepareStatement(sqlCreateStudentGroup, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, taskGroupID);
                int rows = stmt.executeUpdate();
                System.out.println("Added " + rows + " StudentGroup row(s)");
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next())
                    studentGroupID = rs.getInt(1);
            } catch (SQLException se) {
                System.out.println("Error while adding StudentGroup to DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        if (studentGroupID == null) {
            System.out.println("Error failed creating StudentGroup");
            return;
        }

        //Create StudentGroupConnections
        for (Student stud : studentList) {
            try {
                Connection con = DriverManagerWrapper.getConnection();
                try (PreparedStatement stmt = con.prepareStatement(sqlCreateStudentGroupConnection)) {
                    stmt.setInt(1, stud.getId());
                    stmt.setInt(2, studentGroupID);
                    int rows = stmt.executeUpdate();
                    System.out.println("Added " + rows + " StudentGroupConnection row(s)");
                } catch (SQLException se) {
                    System.out.println("Error while adding StudentGroupConnection to DB: " + se.getMessage());
                }
            } catch (SQLException se) {
                System.out.println("Connection to DataBase failed: " + se.getMessage());
            }
        }
    }

    public List<StudentGroup> getStudentGroups_byTaskGroup(int taskGroupID) {
        List<StudentGroup> list = new ArrayList<StudentGroup>();
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (PreparedStatement stmt = con.prepareStatement(sqlGetStudentGroup_byTaskGroup)) {
                stmt.setInt(1, taskGroupID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int studGroupID = rs.getInt("id");
                    List<Student> studList = new StudentDAO().getStudents_byStudentGroup(studGroupID);
                    StudentGroup sg = new StudentGroup(studGroupID, studList);
                    list.add(sg);
                }
            } catch (SQLException se) {
                System.out.println("Error while reading StudentGroup from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return list;
    }
}
