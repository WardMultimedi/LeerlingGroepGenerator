package be.multimedi.StudGroupGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentGroupDAO {
    private static String sqlGetAllStudentGroups = "SELECT * FROM StudentGroups";
    private static String sqlGetStudentGroup_byTaskGroup = "SELECT * FROM StudentGroups WHERE taskGroup_id = ?";
    private static String sqlAddStudentGroup = "INSERT INTO StudentGroups(taskGroup_id) VALUES(?)";

    public List<StudentGroup> getStudentGroups_byTaskGroup(int taskGroupID){
        List<StudentGroup> list = new ArrayList<StudentGroup>();
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(PreparedStatement stmt = con.prepareStatement(sqlGetStudentGroup_byTaskGroup)){
                stmt.setInt(1, taskGroupID);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    int studGroupID = rs.getInt("id");
                    List<Student> studList = new StudentDAO().getStudents_byStudentGroup(studGroupID);
                    StudentGroup sg = new StudentGroup(studGroupID, studList);
                    list.add(sg);
                }
            }catch( SQLException se){
                System.out.println("Error while reading StudentGroup from DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return list;
    }
}
