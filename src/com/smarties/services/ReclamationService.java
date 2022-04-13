/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Reclamation;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 */
public class ReclamationService {
    
        Connection cnx;

    public ReclamationService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterReclamation (Reclamation r) {
        String query = "insert into reclamation (id_user_id ,description,date,objet) values(?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, r.getId_user_id());
            ste.setString(2, r.getDescription());
            ste.setString(3, r.getDate());
            ste.setString(4, r.getObjet());
            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
        public List<Reclamation> afficherReclamation() {
        List<Reclamation> reclamations = new ArrayList<>();
        String sql = "select * from reclamation";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                System.out.println(r.getId());
                r.setDate(rs.getString("date"));
                r.setDescription(rs.getString("description"));
                reclamations.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reclamations;

    }
    
    
        public void modifierReclamation(Reclamation t) {
        try {
            String req = "UPDATE reclamation SET objet= ?, description = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getObjet());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une reclamation modifiée dans la table...");
        } catch (SQLException e) {

        }

    }
    
        public void supprimerReclamation(int id) {
        try {
            String req = "DELETE FROM reclamation WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Une reclamation SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
}