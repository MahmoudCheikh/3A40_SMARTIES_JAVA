/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Stock;
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
public class StockService {

    Connection cnx;

    public StockService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterStock(Stock s) {
        String query = "insert into stock(libelle,id_produit_id,prix,quantite,disponibilite,emplacement_id) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, s.getLibelle());
            ste.setInt(2, s.getIdProduit());
            ste.setFloat(3, s.getPrix());
            ste.setInt(4, s.getQuantite());
            ste.setString(5, s.getDisponibilite());
            ste.setInt(6, s.getEmplacement());

            ste.executeUpdate();
            System.out.println("Stock Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Stock> afficherStock() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "select * from stock";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Stock s = new Stock();
                s.setId(rs.getInt("id"));
                s.setLibelle(rs.getString("libelle"));
                s.setDisponibilite(rs.getString("Disponibilite"));
                stocks.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return stocks;

    }

    public void modifierStock(Stock s) {
        try {
            String req = "UPDATE stock SET libelle= ?, disponibilite = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, s.getLibelle());
            ps.setString(2, s.getDisponibilite());

            ps.setInt(3, s.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerStock(int id) {
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
