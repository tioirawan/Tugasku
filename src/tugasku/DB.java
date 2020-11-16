/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author indmind
 */
public class DB {
    private Connection connect;
    
    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/tugasku";
    private String username = "root";
    private String password = "";
    
    public Connection getConnection() {
        if(connect == null){
            try {
                Class.forName(driverName);
                System.out.println("Class driver ditemukan ");
                
                connect = DriverManager.getConnection(url, username, password);
                
                System.out.println("Koneksi database sukses");
            } catch (SQLException ex) {
                System.out.println("Koneksi database gagal");
                System.exit(0);
            } catch (ClassNotFoundException ex) {
                System.out.println("Class driver tidak ditemukan, terjadi kesalahan pada: " + ex);
            } 
        }
        
        return connect;
    }
}
