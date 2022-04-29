/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Emplacement;
import com.smarties.entities.Stock;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PC
 */
public class EmplacementService {

    Connection cnx;

    public EmplacementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterEmplacement(Emplacement s) {
        String query = "insert into emplacement(lieu,capacite,stock_id) values(?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, s.getLieu());
            ste.setInt(2, s.getCapacite());
            ste.setInt(3, s.getStock());

            ste.executeUpdate();
            System.out.println("Emplacement Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Emplacement> afficherEmplacement() {
        List<Emplacement> emplacements = new ArrayList<>();
        String sql = "select * from emplacement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Emplacement s = new Emplacement();
                s.setId(rs.getInt("id"));
                s.setLieu(rs.getString("lieu"));
                s.setCapacite(rs.getInt("capacite"));
                s.setStock(rs.getInt("stock_id"));
                emplacements.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return emplacements;

    }

    public void modifierEmplacement(Emplacement s) {
        try {
            String req = "UPDATE emplacement SET lieu= ?, capacite = ?, stock_id=? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, s.getLieu());
            ps.setInt(2, s.getCapacite());
            ps.setInt(3, s.getStock());

            ps.setInt(4, s.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerEmplacement(int id) {
        try {
            String req = "DELETE FROM emplacement WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
    public List<Emplacement> TriLieu() {
        Comparator<Emplacement> comparator = Comparator.comparing(Emplacement::getLieu);
        List<Emplacement> sto = afficherEmplacement();
        return sto.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Emplacement> TriCapacite() {
        Comparator<Emplacement> comparator = Comparator.comparing(Emplacement::getCapacite);
        List<Emplacement> sto = afficherEmplacement();
        return sto.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Emplacement> RechercheLieu(String libelle) {

        return afficherEmplacement().stream().filter(a -> a.getLieu().equals(libelle)).collect(Collectors.toList());
    }
    
        public ArrayList<String> getCombo() {
        ArrayList<String> options = new ArrayList<>();
        String sql = "select * from stock";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }
        
              public void sendMail(String recipient,String lieu,int cap) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String MyAccountEmail = "roulece090@gmail.com";
        String password = "ahmed123456789";
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyAccountEmail, password);
            }

        });

        Message message = prepareMessage(session, MyAccountEmail, recipient,lieu,cap);
        Transport.send(message);
        System.out.println("message sent successfully");

    }

    private Message prepareMessage(Session session, String MyAccountEmail, String recipient,String lieu,int cap) {
//Emplacement emp = new Emplacement();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Les Sites De çaRoule ! ");
            message.setText("Merci pour Votre demande des information , \n 1) le site  est : "+lieu+" \n 2) La Capacité de ce Site est : "+cap+" \n 3) Vous pouvez Consulter la map : https://www.google.com/maps/place/"+lieu+"/ \n Merci Pour Votre confiance ...");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
