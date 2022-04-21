/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Sujet;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class SujetService {

    Connection cnx;

    public SujetService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterSujet(Sujet p) {
        String query = "insert into sujet(id_user_id,nb_reponses,nb_vues,date,titre,contenu) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setInt(1, p.getUserId());
            ste.setInt(2, p.getNbReponses());
            ste.setInt(3, p.getNbVues());
            ste.setDate(4, Date.valueOf(p.getDate()));
            ste.setString(5, p.getTitre());
            ste.setString(6, p.getContenu());

            ste.executeUpdate();
            System.out.println("Sujet Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Sujet> afficherSujet() {
        List<Sujet> sujets = new ArrayList<>();
        String sql = "select * from sujet";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Sujet p = new Sujet();

                p.setId(rs.getInt("id"));
                p.setUserId(rs.getInt(2));
                p.setNbReponses(rs.getInt(3));
                Date date = rs.getDate("date");
                p.setDate(date.toLocalDate());
                p.setTitre(rs.getString("titre"));
                p.setContenu(rs.getString("contenu"));
                p.setNbVues(rs.getInt(7));

                sujets.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sujets;

    }

    public void modifierSujet(Sujet c) {
        try {
            String req = "UPDATE sujet SET titre= ?, contenu = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getTitre());
            ps.setString(2, c.getContenu());

            ps.setInt(3, c.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerSujet(int id) {
        try {
            String req = "DELETE FROM sujet WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

    public ArrayList<Integer> getCombo() {
        ArrayList<Integer> options = new ArrayList<>();
        String sql = "select * from sujet";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

}
