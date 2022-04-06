/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Favoris;
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
public class FavorisService {
    
     Connection cnx;

    public FavorisService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

public void ajouterFavoris(Favoris f) {
        String query = "insert into favoris(id_produit_id,id_user_id) values(?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, f.getIdProduit());
            ste.setInt(2, f.getIdUser());


            ste.executeUpdate();
            System.out.println("Favoris Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Favoris> afficherFavoris() {
        List<Favoris> favoris = new ArrayList<>();
        String sql = "select * from favoris";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Favoris f = new Favoris();
                f.setId(rs.getInt("id"));
                System.out.println(f.getId());
                f.setIdProduit(rs.getInt("id_produit_id"));
                f.setIdUser(rs.getInt("id_user_id"));
                favoris.add(f);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return favoris;

    }

    public void modifierFavoris(Favoris f) {
        try {
            String req = "UPDATE favoris SET id_produit_id= ?, id_user_id = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, f.getIdProduit());
            ps.setInt(2, f.getIdUser());

            ps.setInt(3, f.getId());
            System.out.println("*** Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerFavoris(int id) {
        try {
            String req = "DELETE FROM favoris WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("*** Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne à été SUPPRIMER dans la table avec succés...");
        } catch (SQLException e) {

        }

    }
    

    
}
