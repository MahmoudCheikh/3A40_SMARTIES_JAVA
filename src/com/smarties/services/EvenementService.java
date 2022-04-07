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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            ste.setString(2, e.getDate_d());
            ste.setString(3, e.getDate_f());
            ste.setString(4, e.getLieu());
            ste.setString(5, e.getType());
            ste.setInt(6,(int)e.getNb_participants());
            ste.setInt(7,(int)e.getNb_places());
           

            ste.executeUpdate();
            System.out.println("Evenement Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     public List<Evenement> afficherEvenement() {
        List<Evenement> evenements= new ArrayList<>();
        String sql = "select * from evenement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                System.out.println(e.getId());
                e.setNom(rs.getString("nom"));
                e.setDate_d(rs.getString("date_d"));
                e.setDate_f(rs.getString("date_f"));
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
            ps.setString(2, e.getDate_d());
            ps.setString(3, e.getDate_f());
            ps.setString(4, e.getLieu());
            ps.setString(5, e.getType());
            ps.setInt(6,(int) e.getNb_participants()); 
            ps.setInt(7,(int) e.getNb_places());

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
}
