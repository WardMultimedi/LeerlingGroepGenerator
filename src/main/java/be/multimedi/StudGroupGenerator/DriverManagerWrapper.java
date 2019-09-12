package be.multimedi.StudGroupGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DriverManagerWrapper {
    private static Connection con;

    public static Connection getConnection() throws SQLException {
        if( con == null ){
            con = DriverManager.getConnection(DB.url, DB.login, DB.pwd);
        }
        return con;
    }

    public static void closeConnection() throws SQLException {
        con.close();
        con = null;
    }
}
