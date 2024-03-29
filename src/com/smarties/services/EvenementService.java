/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Evenement;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.smarties.entities.Users;
import com.twilio.Twilio;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Lenovo
 */
public class EvenementService {

    Connection cnx;

    public EvenementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterEvenement(Evenement e) {
        String query = "insert into evenement(nom,date_d,date_f,lieu,type,nb_participants,nb_places) values(?,?,?,?,?,?,?)";
        try {

            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, e.getNom());
            ste.setDate(2, Date.valueOf(e.getDate_d()));
            ste.setDate(3, Date.valueOf(e.getDate_f()));
            ste.setString(4, e.getLieu());
            ste.setString(5, e.getType());
            ste.setInt(6, (int) e.getNb_participants());
            ste.setInt(7, (int) e.getNb_places());

            ste.executeUpdate();
            System.out.println("Evenement Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Evenement> afficherEvenement() {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "select * from evenement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                Date date_d = rs.getDate("date_d");
                e.setDate_d(date_d.toLocalDate());
                Date date_f = rs.getDate("date_f");
                e.setDate_f(date_f.toLocalDate());
                e.setLieu(rs.getString("lieu"));
                e.setType(rs.getString("type"));
                e.setNb_participants(rs.getInt("nb_participants"));
                e.setNb_places(rs.getInt("nb_places"));
                evenements.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return evenements;

    }

    public void modifierEvenement(Evenement e) {

        try {
            String req = "UPDATE evenement SET nom= ?, date_d = ?, date_f = ?, lieu = ?, type = ?, nb_participants = ?, nb_places = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, e.getNom());
            ps.setDate(2, Date.valueOf(e.getDate_d()));
            ps.setDate(3, Date.valueOf(e.getDate_f()));
            ps.setString(4, e.getLieu());
            ps.setString(5, e.getType());
            ps.setInt(6, (int) e.getNb_participants());
            ps.setInt(7, (int) e.getNb_places());

            ps.setInt(8, e.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException ex) {

        }
    }

    public void supprimerEvenement(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

    public List<Evenement> Chercher(String titreN) {
        List<Evenement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM evenement where nom=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE...");
            ps.setString(1, titreN);
            ResultSet rs = ps.executeQuery();
            System.out.println(titreN);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));

                e.setDate_d(rs.getDate(3).toLocalDate());
                e.setDate_f(rs.getDate(4).toLocalDate());
                e.setLieu(rs.getString(5));
                e.setType(rs.getString(6));
                e.setNb_participants(rs.getInt(7));
                e.setNb_places(rs.getInt(8));

                list.add(e);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public List<Evenement> trier() {
        List<Evenement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM evenement order by date_d asc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setDate_d(rs.getDate(3).toLocalDate());
                e.setDate_f(rs.getDate(4).toLocalDate());
                e.setLieu(rs.getString(5));
                e.setType(rs.getString(6));
                e.setNb_participants(rs.getInt(7));
                e.setNb_places(rs.getInt(8));
                list.add(e);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public ArrayList<Integer> getCombo() {
        ArrayList<Integer> options = new ArrayList<>();
        String sql = "select id from evenement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

    public ArrayList<Integer> getCombo1() {
        ArrayList<Integer> options = new ArrayList<>();
        String sql = "select id from users";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

    public Evenement Chercher1(int id) throws SQLException {
        Evenement ess = new Evenement();

        String req = "SELECT nb_participants FROM evenement where id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        System.out.println("RECHERCHE...");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        System.out.println(id);
        while (rs.next()) {

            ess.setNb_participants(rs.getInt(1) - 1);

        }

        return ess;
    }

    public void participer(Evenement c, int u) throws SQLException {
        String req = "insert into participation (id_user_id , id_event_id ) values (?,?)";

        try {

            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1, u);
            pst.setInt(2, c.getId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);

        }
        String req1 = "UPDATE evenement SET nb_participants = ?,nb_places = ? WHERE id=?";
        try {

            PreparedStatement pst1 = cnx.prepareStatement(req1);

            pst1.setInt(1, c.getNb_participants() - 1);
            pst1.setInt(2, c.getNb_places()- 1);
            pst1.setInt(3, c.getId());

            pst1.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
      public boolean verifierparticiper(Evenement e,int u) throws SQLException{
       String req1 = "select * from participation where id_user_id=? AND id_event_id=?";
           PreparedStatement ste = cnx.prepareStatement(req1);
           ste.setInt(1, u);
           ste.setInt(2, e.getId());
           ResultSet rs = ste.executeQuery();
     if(rs.next()){ 
         return true;
        
    
     }
   else{
           return false;
           }
     }
        
      public void supprimerparticipation (Evenement e,int u) throws SQLException {
        
            String req = "DELETE FROM participation WHERE id_user_id =? AND id_event_id=?";
           PreparedStatement ste = cnx.prepareStatement(req);
           ste.setInt(1, u);
           ste.setInt(2, e.getId());
        int executeUpdate = ste.executeUpdate();
      
    }
       private  Message prepareMessage(Session session,String MyAccountEmail, String recipient) {
              
        try {
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress( MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress( recipient) );
            message.setSubject("Notification via votre application desktop");
            message.setText("Bonjour Cher Client , \n Votre Commande A été Passe Avec Succes ! ");
            return message; 
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       public void sendSMS(String nom, String date)
    {
    
       Twilio.init("AC1bfc52d30073068147b27bcfeae02c20", "626f9f30ef57e99f0239c2c46bff15e8");
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                new com.twilio.type.PhoneNumber("+21623251728"),
                new com.twilio.type.PhoneNumber("++17579095719 "),
                "vous participez maintenant a l'evnement "+nom+" qui debute le "+date)
            .create();

        System.out.println(message.getSid());
    
    }

}


