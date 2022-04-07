/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Abonnement;
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
 * @author ASUS
 */
public class AbonnementService {

    Connection cnx;

    public AbonnementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterAbonnement(Abonnement a) {

        String query = "insert into abonnement(id,id_user_id,type,dateD,dateF,prix) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, (int) a.getId());
            ste.setInt(2, (int) a.getId_user_id());
            ste.setString(3, a.getType());
            ste.setString(4, a.getDateD());
            ste.setString(5, a.getDateF());
            ste.setInt(6, (int) a.getPrix());
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
                a.setDateD(rs.getString("dated"));
                a.setDateF(rs.getString("datef"));
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
            ps.setString(2, a.getDateD());
            ps.setString(3, a.getDateF());
            ps.setInt(4, a.getPrix());
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

}
