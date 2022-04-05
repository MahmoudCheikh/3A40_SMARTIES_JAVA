/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Produit;
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
public class ProduitService {
    
    Connection cnx;

    public ProduitService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterProduit(Produit p) {
        String query = "insert into produit(libelle,image,description,prix,type) values(?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, p.getLibelle());
            ste.setString(2, p.getImage());
            ste.setString(3, p.getDescription());
            ste.setFloat(4, (float) p.getPrix());
            ste.setString(5, p.getType());

            ste.executeUpdate();
            System.out.println("Produit Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Produit> afficherProduit() {
        List<Produit> produits = new ArrayList<>();
        String sql = "select * from produit";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                System.out.println(p.getId());
                p.setLibelle(rs.getString("libelle"));
                p.setDescription(rs.getString("description"));
                produits.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return produits;

    }

    public void modifierProduit(Produit c) {
        try {
            String req = "UPDATE produit SET libelle= ?, description = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getLibelle());
            ps.setString(2, c.getDescription());

            ps.setInt(3, c.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerProduit(int id) {
        try {
            String req = "DELETE FROM produit WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
    
}
