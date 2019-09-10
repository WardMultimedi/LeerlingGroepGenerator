package be.multimedi.StudGroupGenerator;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StudentDAO {
    private static String sqlGetAllStudents = "SELECT * FROM Students";
    private static String sqlGetStudent = "SELECT * FROM Students WHERE id = ?";
    private static String sqlAddStudents = "INSERT INTO Students(firstName, lastName) VALUES(?,?)";
    private static String sqlGetStudents_byStudentGroup = "SELECT s.* FROM Students as s, StudentGroupConnections as sgc WHERE s.id = sgc.student_id AND sgc.studGroup_id = ?";

    public List<Student> getStudents_byStudentGroup(int studGroupID){
        List<Student> list = new ArrayList<Student>();
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(PreparedStatement stmt = con.prepareStatement(sqlGetStudents_byStudentGroup)){
                stmt.setInt(1, studGroupID);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    Student stud = new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
                    list.add(stud);
                }
            }catch( SQLException se){
                System.out.println("Error while reading from DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return list;
    }

    public List<Student> getAllStudents(){
        List<Student> list = new ArrayList<Student>();
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(Statement stmt = con.createStatement()){
                ResultSet rs = stmt.executeQuery(sqlGetAllStudents);
                while(rs.next()){
                    Student stud = new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
                    list.add(stud);
                }
            }catch( SQLException se){
                System.out.println("Error while reading from DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return list;
    }

    public Student getStudent( int id ){
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(PreparedStatement stmt = con.prepareStatement(sqlGetStudent)){
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    Student stud = new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
                    return stud;
                }
            }catch( SQLException se){
                System.out.println("Error while reading Student from DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return null;
    }

    public void addStudent(Student stud){
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(PreparedStatement stmt = con.prepareStatement(sqlAddStudents)){
                stmt.setString(1, stud.getFirstName());
                stmt.setString(2, stud.getLastName());
            }catch( SQLException se){
                System.out.println("Error while writing Student to DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
    }
}
