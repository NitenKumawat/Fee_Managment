/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FeesManager;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author niten
 */
public  class DBConnection {
    public static Connection getConnection(){
        Connection con = null;
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Fee_management","niten","niten");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
            
}
