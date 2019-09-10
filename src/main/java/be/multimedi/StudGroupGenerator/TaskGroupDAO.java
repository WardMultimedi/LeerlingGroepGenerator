package be.multimedi.StudGroupGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskGroupDAO {
    private final String sqlGetTaskGroup_byID = "SELECT * FROM TaskGroups where id = ?";
    private final String sqlGetTaskGroup_byName = "SELECT * FROM TaskGroups where name LIKE ?";

    public TaskGroup getTaskGroup_byName(String taskGroupName){
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(PreparedStatement stmt = con.prepareStatement(sqlGetTaskGroup_byName)){
                stmt.setString(1, taskGroupName);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    int taskGroupID = rs.getInt("id");
                    StudentGroupDAO sgd = new StudentGroupDAO();
                    TaskGroup tg = new TaskGroup(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("minStudents"),
                            sgd.getStudentGroups_byTaskGroup(taskGroupID));
                    return tg;
                }
            }catch( SQLException se){
                System.out.println("Error while reading TaskGroup from DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return null;
    }

    public TaskGroup getTaskGroup_byID(int taskGroupID){
        try(Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)){
            try(PreparedStatement stmt = con.prepareStatement(sqlGetTaskGroup_byID)){
                stmt.setInt(1, taskGroupID);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    StudentGroupDAO sgd = new StudentGroupDAO();
                    TaskGroup tg = new TaskGroup(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("minStudents"),
                            sgd.getStudentGroups_byTaskGroup(taskGroupID));
                    return tg;
                }
            }catch( SQLException se){
                System.out.println("Error while reading TaskGroup from DB: " + se.getMessage());
            }
        }catch (SQLException se){
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return null;
    }
}
