package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    public Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
         return con;
        } catch (SQLException e1) {
            e1.printStackTrace();
            System.out.println("sql fail");
        }
        catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return con;
    }
}
