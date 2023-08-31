/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Haseeb
 */
public class DbConnection {
    
    private static Connection connection=null;
    
    public  static Connection getConnection(){
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system", "root", "1234");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    
    
}
