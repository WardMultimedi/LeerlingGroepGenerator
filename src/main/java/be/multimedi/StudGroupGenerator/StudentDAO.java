package be.multimedi.StudGroupGenerator;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StudentDAO {
    private static String sqlGetAllStudents = "SELECT * FROM Students";
    private static String sqlAddStudents = "INSERT INTO Students(firstName, lastName) VALUES(?,?)";

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

    public void addStudent(Student stud){
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(PreparedStatement stmt = con.prepareStatement(sqlAddStudents)){
                stmt.setString(1, stud.getFirstName());
                stmt.setString(2, stud.getLastName());
            }catch( SQLException se){
                System.out.println("Error while reading from DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
    }
}
