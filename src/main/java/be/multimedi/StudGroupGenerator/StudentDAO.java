package be.multimedi.StudGroupGenerator;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StudentDAO {
    private static String sqlGetAllStudents = "SELECT * FROM Students";
    private static String sqlGetStudent = "SELECT * FROM Students WHERE id = ?";
    private static String sqlCreateStudent = "INSERT INTO Students(firstName, lastName) VALUES(?,?)";
    private static String sqlGetStudents_byStudentGroup = "SELECT s.* FROM Students as s, StudentGroupConnections as sgc WHERE s.id = sgc.student_id AND sgc.studGroup_id = ?";
    private static String sqlGetStudentCount = "SELECT COUNT(*) as Count FROM Students";
    private final String sqlRemoveStudent_byID = "DELETE FROM Students where id = ?";
    private final String sqlRemoveStudent_byName = "DELETE FROM Students where firstName LIKE ?";

    public int getStudentCount() {
        int count = 0;
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(sqlGetStudentCount);
                while (rs.next()) {
                    count = rs.getInt("count");
                }
            } catch (SQLException se) {
                System.out.println("Error while reading Student from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return count;
    }

    public List<Student> getStudents_byStudentGroup(int studGroupID) {
        List<Student> list = new ArrayList<Student>();
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (PreparedStatement stmt = con.prepareStatement(sqlGetStudents_byStudentGroup)) {
                stmt.setInt(1, studGroupID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Student stud = new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
                    list.add(stud);
                }
            } catch (SQLException se) {
                System.out.println("Error while reading Student from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return list;
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<Student>();
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(sqlGetAllStudents);
                while (rs.next()) {
                    Student stud = new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
                    list.add(stud);
                }
            } catch (SQLException se) {
                System.out.println("Error while reading Student from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return list;
    }

    public Student getStudent(int id) {
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (PreparedStatement stmt = con.prepareStatement(sqlGetStudent)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
                }
            } catch (SQLException se) {
                System.out.println("Error while reading Student from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return null;
    }

    public void addStudent(Student stud) {
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (PreparedStatement stmt = con.prepareStatement(sqlCreateStudent)) {
                stmt.setString(1, stud.getFirstName());
                stmt.setString(2, stud.getLastName());
                int rows = stmt.executeUpdate();
                System.out.println("Added " + rows + " Student row(s)");
            } catch (SQLException se) {
                System.out.println("Error while writing Student to DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
    }

    public void removeStudent_byID(int studID) {
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (PreparedStatement stmt = con.prepareStatement(sqlRemoveStudent_byID)) {
                stmt.setInt(1, studID);
                int rows = stmt.executeUpdate();
                System.out.println("Removed " + rows + " row(s)");
            } catch (SQLException se) {
                System.out.println("Error while removing Student from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
    }

    public void removeStudent_byFirstName(String firstName) {
        try {
            Connection con = DriverManagerWrapper.getConnection();
            try (PreparedStatement stmt = con.prepareStatement(sqlRemoveStudent_byName)) {
                stmt.setString(1, firstName);
                int rows = stmt.executeUpdate();
                System.out.println("Removed " + rows + " row(s)");
            } catch (SQLException se) {
                System.out.println("Error while removing Student from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
    }
}
