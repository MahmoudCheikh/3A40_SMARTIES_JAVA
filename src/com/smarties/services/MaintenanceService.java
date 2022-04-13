/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Maintenance;
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
public class MaintenanceService {
    
      Connection cnx;

    public MaintenanceService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterMaintenance (Maintenance m) {
        String query = "insert into maintenance(id_produit_id,relation_id,reclamation_id,date_debut,date_fin,adresse,etat,description) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
   
            ste.setInt(1, m.getId_produit_id());
            ste.setInt(2, m.getRelation_id());
            ste.setInt(3, m.getReclamation_id());
            ste.setString(4, m.getDate_debut());
            ste.setString(5, m.getDate_fin());
            ste.setString(6, m.getAdresse());
            ste.setString(7, m.getEtat());
            ste.setString(8, m.getDescription());

            ste.executeUpdate();
            System.out.println("Maintenance Ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
        public List<Maintenance> afficherMaintenance() {
        List<Maintenance> Maintenances = new ArrayList<>();
        String sql = "select * from maintenance";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Maintenance m = new Maintenance();
                m.setId(rs.getInt("Id"));
                System.out.println(m.getId());
                m.setEtat(rs.getString("Etat"));
                m.setDescription(rs.getString("Description"));
                m.setDate_debut(rs.getString("Date_debut"));
                m.setDate_fin(rs.getString("Date_fin"));
                Maintenances.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Maintenances;

    }
        
        
            public void modifierMaintenance(Maintenance n) {
        try {
            String req = "UPDATE maintenance SET etat= ? WHERE id= ?";
            PreparedStatement ps1 = cnx.prepareStatement(req);
            ps1.setString(1, n.getEtat());
            ps1.setInt(2, n.getId());
            System.out.println("Modification...");
            ps1.executeUpdate();

            System.out.println("Une maintenance modifiée dans la table...");
        } catch (SQLException e) {

        }

    }
        
            public void supprimerMaintenance(int id) {
        try {
            String req = "DELETE FROM maintenance WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Une maintenance SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
    
}
