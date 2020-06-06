package config;

import java.sql.*;

public class Database {
    static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) return con;
        // get db, user, pass from settings file
        return getConnection("javaoop", "root", "196422");
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name+"?useUnicode=true&serverTimezone=UTC", user_name, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;
    }
    public void query(String query){ // For select, we have to know about what we'll select, and we will return it in a ArrayList.
        try {
            Statement stmt = getConnection().createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }
}

