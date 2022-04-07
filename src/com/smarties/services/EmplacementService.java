/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Emplacement;
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
                s.setCapacite(rs.getInt("capacite"));
                s.setLieu(rs.getString("lieu"));
                emplacements.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return emplacements;

    }

    public void modifierEmplacement(Emplacement s) {
        try {
            String req = "UPDATE emplacement SET libelle= ?, disponibilite = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, s.getLieu());
            ps.setInt(2, s.getCapacite());

            ps.setInt(3, s.getId());
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

}
