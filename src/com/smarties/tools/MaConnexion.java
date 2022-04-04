
package com.smarties.tools;

import java.sql.*;


public class MaConnexion {
    public final String url  ="jdbc:mysql://localhost:3306/pi";
    public final String user ="root";
    public final String pwd = "";
    private Connection cnx;
    public static MaConnexion ct;

    private MaConnexion() {
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static MaConnexion getInstance(){
        if(ct==null)
            ct=new MaConnexion();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
}
