/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Abonnement;
import com.smarties.entities.Location;
import com.smarties.tools.MaConnexion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.net.PasswordAuthentication;
import javax.mail.Authenticator;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class AbonnementService {

   

    Connection cnx;

    public AbonnementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterAbonnement(Abonnement a) {

        String query = "insert into abonnement(id_user_id,type,dateD,dateF,prix) values(?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
           // ste.setInt(1, (int) a.getId());
            ste.setInt(1, (int) a.getId_user_id());
            ste.setString(2, a.getType());
            // ste.setDate(2, Date.valueOf(e.getDate_d()));
            ste.setDate(3, Date.valueOf(a.getDateD()));
            ste.setDate(4, Date.valueOf(a.getDateF()));
            ste.setInt(5, (int) a.getPrix());
            ste.executeUpdate();
            System.out.println("Abonnement Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Abonnement> afficherAbonnement() {

        List<Abonnement> abonnements = new ArrayList<>();
        String sql = "select * from abonnement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Abonnement a = new Abonnement();
                a.setId(rs.getInt("id"));
                a.setId_user_id(rs.getInt("id_user_id"));
                a.setType(rs.getString("type"));
                Date dateD = rs.getDate("dated");
                a.setDateD(dateD.toLocalDate());
                Date dateF = rs.getDate("datef");
                a.setDateF(dateF.toLocalDate());

                a.setPrix(rs.getInt("prix"));
                abonnements.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return abonnements;

    }

    public void modifierAbonnement(Abonnement a) {
        try {
            String req = "UPDATE abonnement SET type= ?, dateD = ?,dateF = ?,prix = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getType());
              ps.setDate(2, Date.valueOf(a.getDateD()));
            ps.setDate(3, Date.valueOf(a.getDateF()));
         
            ps.setInt(4,(int) a.getPrix());
            ps.setInt(5,(int) a.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerAbonnement(int id) {
        try {
            String req = "DELETE FROM abonnement WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne supprimée dans la table...");
        } catch (SQLException e) {

        }

    }
    
    
    
    public List<Abonnement> ChercherType(String titreN) {
       List<Abonnement> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM abonnement where type=?";
             PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE en cours ..");
            ps.setString(1,titreN);
            ResultSet rs = ps.executeQuery();
         
              System.out.println(titreN);
            while(rs.next()){
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                 ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));
               

               
                
                list.add(ab);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
   

}
    
    
      public List<Abonnement> ChercherPrix( int price)
             {
             
               List<Abonnement> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM abonnement where prix=?";
             PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE en cours ..");
            ps.setInt(1,price);
            ResultSet rs = ps.executeQuery();
         
              System.out.println(price);
            while(rs.next()){
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                 ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));
               

               
                
                list.add(ab);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
   
             } 
      
      
       public List<Abonnement>trierPrix() {
        List<Abonnement> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM abonnement order by prix desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
              
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                 ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));
                
                list.add(ab);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;
    
              
              
              
}
         public List<Abonnement> trierIDUSER() {
        List<Abonnement> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM abonnement order by id_user_id desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
              
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                 ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));
                
                list.add(ab);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;
    
              
              
              
}
         public void generateExcel() 
         {
           String sql = "select * from abonnement";
        Statement ste;
        try {
       
          ste=cnx.prepareStatement(sql);
               ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("abonnement details ");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Type");
            header.createCell(2).setCellValue("Date debut");
            header.createCell(3).setCellValue("Date fin");
            header.createCell(4).setCellValue("ID User");
            header.createCell(5).setCellValue("Prix");
            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("type"));
                row.createCell(2).setCellValue(rs.getString("dated"));
                row.createCell(3).setCellValue(rs.getString("datef"));
                row.createCell(4).setCellValue(rs.getString("id_user_id"));
                row.createCell(5).setCellValue(rs.getString("prix"));
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\ASUS\\Desktop\\abonnementDetails.ods");
            wb.write(fileOut);
            fileOut.close();
           ste.close();
           rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }

       

    }
         public void  sendMail(String recipient) throws Exception
         {  
         Properties properties =new Properties();
         properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.host", "smtp.gmail.com" );
           properties.put("mail.smtp.port", "587");
           String MyAccountEmail="fadwa.berrich@esprit.tn"; 
           String password ="192JFT4518";
            Session session= Session.getDefaultInstance(properties,new Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication()
           {
           return new PasswordAuthentication(MyAccountEmail,password);}
            
            });
         
               Message message= prepareMessage(session,MyAccountEmail,recipient); 
               Transport.send(message);
               System.out.println("message sent successfully");
         
         }
          private  Message prepareMessage(Session session,String MyAccountEmail, String recipient) {
              
        try {
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress( MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress( recipient) );
            message.setSubject("Notification via votre application desktop");
            message.setText("Bonjour , \n Un nouvel abonnement a été ajouté ! ");
            return message; 
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
           public long Search1() {

        List<Abonnement> Abon = afficherAbonnement();
        return Abon.stream().filter(b -> b.getType().equalsIgnoreCase("VIP")).count();

    }

    public long Search2() {

          List<Abonnement> Abon = afficherAbonnement();
        return Abon.stream().filter(b -> b.getType().equalsIgnoreCase("Silver")).count();

    }

    public long Search3() {

          List<Abonnement> Abon = afficherAbonnement();
        return Abon.stream().filter(b -> b.getType().equalsIgnoreCase("Gold")).count();

    }
    
    
    
    
    
       public int searchByID(String iid) throws SQLException {
       Location loc = new Location();
        String req = "SELECT * FROM abonnement  where id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        System.out.println("RECHERCHE...");
        ps.setString(1, iid);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

         
}

