package be.multimedi.StudGroupGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskGroupDAO {
    private final String sqlGetTaskGroup_byID = "SELECT * FROM TaskGroups where id = ?";
    private final String sqlGetTaskGroup_byName = "SELECT * FROM TaskGroups where name LIKE ?";
    private final String sqlRemoveTaskGroup_byID = "DELETE FROM TaskGroups where id = ?";
    private final String sqlRemoveTaskGroup_byName = "DELETE FROM TaskGroups where name LIKE ?";
    private final String sqlCreateTaskGroup = "INSERT INTO TaskGroups(name, minStudents) VALUES(?, ?)";

    public void createTaskGroup(String taskGroupName, int taskGroupMinStudents) {
        // Create TaskGroup
        try (Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)) {
            try (PreparedStatement stmt = con.prepareStatement(sqlCreateTaskGroup)) {
                stmt.setString(1, taskGroupName);
                stmt.setInt(2, taskGroupMinStudents);
                int rows = stmt.executeUpdate();
                System.out.println("Added " + rows + " TaskGroup row(s)");
            } catch (SQLException se) {
                System.out.println("Error while adding TaskGroup to DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        // find the created TaskGroup
        Integer taskGroupID = null;
        try (Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)) {
            try (PreparedStatement stmt = con.prepareStatement(sqlGetTaskGroup_byName)) {
                stmt.setString(1, taskGroupName);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    taskGroupID = rs.getInt("id");
                }
            } catch (SQLException se) {
                System.out.println("Error while adding TaskGroup to DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        if( taskGroupID == null ){
            System.out.println("error while getting taskGroupID");
            return;
        }

        int studentCount = new StudentDAO().getStudentCount();
        List<Student> totalStudentList = new StudentDAO().getAllStudents();
        int groups = studentCount / taskGroupMinStudents;
        for (int i = 0; i < groups; i++) {
            List<Student> studentGroupList = new ArrayList<>();
            for (int j = 0; j < taskGroupMinStudents; j++) {
                int studIndex = (int) (Math.random() * totalStudentList.size());
                studentGroupList.add(totalStudentList.remove(studIndex));
            }
            if( i == groups -1){
                studentGroupList.addAll(totalStudentList);
            }
            new StudentGroupDAO().createStudentGroup(taskGroupID, studentGroupList);
        }

    }

    public void removeTaskGroup_byName(String taskGroupName) {
        try (Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)) {
            try (PreparedStatement stmt = con.prepareStatement(sqlRemoveTaskGroup_byName)) {
                stmt.setString(1, taskGroupName);
                int rows = stmt.executeUpdate();
                System.out.println("Removed " + rows + " row(s)");
            } catch (SQLException se) {
                System.out.println("Error while removing TaskGroup from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
    }

    public void removeTaskGroup_byID(int taskGroupID) {
        try (Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)) {
            try (PreparedStatement stmt = con.prepareStatement(sqlRemoveTaskGroup_byID)) {
                stmt.setInt(1, taskGroupID);
                int rows = stmt.executeUpdate();
                System.out.println("Removed " + rows + " row(s)");
            } catch (SQLException se) {
                System.out.println("Error while removing TaskGroup from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
    }

    public TaskGroup getTaskGroup_byName(String taskGroupName) {
        try (Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)) {
            try (PreparedStatement stmt = con.prepareStatement(sqlGetTaskGroup_byName)) {
                stmt.setString(1, taskGroupName);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int taskGroupID = rs.getInt("id");
                    StudentGroupDAO sgd = new StudentGroupDAO();
                    TaskGroup tg = new TaskGroup(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("minStudents"),
                            sgd.getStudentGroups_byTaskGroup(taskGroupID));
                    return tg;
                }
            } catch (SQLException se) {
                System.out.println("Error while reading TaskGroup from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return null;
    }

    public TaskGroup getTaskGroup_byID(int taskGroupID) {
        try (Connection con = DriverManager.getConnection(DB.url, DB.login, DB.pwd)) {
            try (PreparedStatement stmt = con.prepareStatement(sqlGetTaskGroup_byID)) {
                stmt.setInt(1, taskGroupID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    StudentGroupDAO sgd = new StudentGroupDAO();
                    TaskGroup tg = new TaskGroup(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("minStudents"),
                            sgd.getStudentGroups_byTaskGroup(taskGroupID));
                    return tg;
                }
            } catch (SQLException se) {
                System.out.println("Error while reading TaskGroup from DB: " + se.getMessage());
            }
        } catch (SQLException se) {
            System.out.println("Connection to DataBase failed: " + se.getMessage());
        }
        return null;
    }
}
