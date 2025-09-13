package DB;

import java.sql.*;

public class DBConnection {
    public static final String url = "jdbc:mysql://localhost:3306/gladiator";
    public static final String db_user = "root";
    public static final String db_password = "root";
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, db_user, db_password);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        return con;
    }
}