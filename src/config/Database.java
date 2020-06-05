package config;

import java.sql.*;

public class Database {
    private static Database instance;
    private  Connection con;
    private String url = "jdbc:mysql://localhost:3306/db_name?useUnicode=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "196422";
    private Database(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url, user, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return con;
    }
    public static Connection getInstance() throws SQLException {

        if (instance == null) {
            instance = new Database();
        } else if (instance.getConnection().isClosed()) {
            instance = new Database();
        }
        return (Connection) instance;
    }
    public void query(String query){ // For select, we have to know about what we'll select, and we will return it in a ArrayList.
        try {
            Statement stmt = getInstance().createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }

}

