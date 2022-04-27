/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Produit;
import com.smarties.entities.Stock;
import com.smarties.entities.Users;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        String query = "insert into stock(libelle,id_produit_id,prix,quantite,disponibilite) values(?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, s.getLibelle());
            ste.setInt(2, s.getIdProduit());
            ste.setFloat(3, s.getPrix());
            ste.setInt(4, s.getQuantite());
            ste.setString(5, s.getDisponibilite());

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
                s.setDisponibilite(rs.getString("disponibilite"));
                s.setPrix(rs.getInt("prix"));
                s.setQuantite(rs.getInt("quantite"));
                s.setIdProduit(rs.getInt("id_produit_id"));
                stocks.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return stocks;

    }

    public void modifierStock(Stock s) {
        try {
            String req = "UPDATE stock SET libelle = ?,id_produit_id = ?,prix = ?,quantite = ?,disponibilite = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, s.getLibelle());
            ps.setInt(2, s.getIdProduit());
            ps.setInt(3, s.getPrix());
            ps.setInt(4, s.getQuantite());
            ps.setString(5, s.getDisponibilite());

            ps.setInt(6, s.getId());

            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerStock(int id) {
        try {
            String req = "DELETE FROM stock WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

    public List<Stock> TriQuantite() {
        Comparator<Stock> comparator = Comparator.comparing(Stock::getQuantite);
        List<Stock> sto = afficherStock();
        return sto.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Stock> TriDisponibilite() {
        Comparator<Stock> comparator = Comparator.comparing(Stock::getDisponibilite);
        List<Stock> sto = afficherStock();
        return sto.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Stock> RechercheLibelle(String libelle) {

        return afficherStock().stream().filter(a -> a.getLibelle().equals(libelle)).collect(Collectors.toList());
    }

    public long Recherche1() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() > 20).filter(b -> b.getQuantite() < 50).count();

    }

    public long Recherche2() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() > 50).filter(b -> b.getQuantite() < 70).count();

    }

    public long Recherche3() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() < 20).count();

    }

    public long Recherche4() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() > 70).filter(b -> b.getQuantite() < 100).count();
    }

    public ArrayList<String> getCombo() {
        ArrayList<String> options = new ArrayList<>();
        String sql = "select * from produit";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

    public int searchByLibS(String libelle) throws SQLException {
        Stock sto = new Stock();
        String req = "SELECT * FROM stock where id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        System.out.println("RECHERCHE...");
        ps.setString(1, libelle);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public boolean getIgnoreRepetetion(String libelle) {
        boolean exist = false;

        try {
            String sql = "SELECT * FROM stock where libelle= ? ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, libelle);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exist;

    }

    public Stock GetStockbyid(int b) throws SQLException {

        //-------------------- Update ----------//
        Stock s = new Stock();

        String query = "select * from stock where id = ? ";
        PreparedStatement ps;
        try {
            ps = MaConnexion.getInstance().getCnx().prepareCall(query);
            ps.setInt(1, b);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {

                s.setId(rest.getInt("id"));
                s.setLibelle(rest.getString("libelle"));
                s.setDisponibilite(rest.getString("disponibilite"));
                s.setPrix(rest.getInt("prix"));
                s.setQuantite(rest.getInt("quantite"));
                s.setIdProduit(rest.getInt("id_produit_id"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
}
