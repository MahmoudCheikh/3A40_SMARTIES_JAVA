/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Activite;
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
public class ActiviteService {
    
    Connection cnx;

    public ActiviteService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
     public void ajouterActivite(Activite e) {
        String query = "insert into Activite(nom,description,image,id_evenement_id) values(?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, e.getNom());
            ste.setString(2, e.getDescription());
            ste.setString(3, e.getImage());
            ste.setInt(4,(int) e.getId_event());
            
           

            ste.executeUpdate();
            System.out.println("Activite Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     public List<Activite> afficherActivite() {
        List<Activite> activites= new ArrayList<>();
        String sql = "select * from activite";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Activite e = new Activite();
                e.setId(rs.getInt("id"));
                System.out.println(e.getId());
                e.setNom(rs.getString("nom"));
                e.setDescription(rs.getString("description"));
                e.setImage(rs.getString("image")); 
                e.setId_event(rs.getInt("id_evenement_id"));
                
                activites.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return activites;

    }
     
    public void modifierActivite(Activite e) {
        
        try {
            String req = "UPDATE activite SET nom= ?, description = ?, image = ?, id_evenement_id = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, e.getNom());
            ps.setString(2, e.getDescription());
            ps.setString(3, e.getImage());
            ps.setInt(4,(int) e.getId_event()); 
        

            ps.setInt(5, e.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException ex) {
            
        }
  }
        public void supprimerActivite(int id) {
        try {
            String req = "DELETE FROM activite WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
}
