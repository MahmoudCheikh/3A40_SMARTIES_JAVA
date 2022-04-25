/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Produit;
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
                p.setLibelle(rs.getString("libelle"));
                p.setImage(rs.getString("image"));
                p.setDescription(rs.getString("description"));
                p.setPrix(rs.getFloat("prix"));
                p.setType(rs.getString("type"));
                produits.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return produits;

    }

    public void modifierProduit(Produit c) {
        try {
            String req = "UPDATE produit SET libelle= ?, description = ?, image= ?, prix = ?, type= ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getLibelle());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getImage());
            ps.setFloat(4, (float) c.getPrix());
            ps.setString(5, c.getType());

            ps.setInt(6, c.getId());
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
            System.out.println("Une ligne SUPPRIMER dans la table where id = ");
            System.out.println(id);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void SupprimerProduit1(int t) {
        PreparedStatement ps;

        String query = "UPDATE `produit` SET `isDelete`=1 WHERE `id`= " + t;

        try {
            ps = cnx.prepareStatement(query);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public List<Produit> RechercheLibelle(String libelle) {

        return afficherProduit().stream().filter(a -> a.getLibelle().equals(libelle)).collect(Collectors.toList());
    }

    public List<Produit> RechercheType(String type) {

        return afficherProduit().stream().filter(a -> a.getType().equals(type)).collect(Collectors.toList());
    }

    public List<Produit> TriPrix() {
        Comparator<Produit> comparator = Comparator.comparing(Produit::getPrix);
        List<Produit> prd = afficherProduit();
        return prd.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Produit> TriType() {
        Comparator<Produit> comparator = Comparator.comparing(Produit::getType);
        List<Produit> prd = afficherProduit();
        return prd.stream().sorted(comparator).collect(Collectors.toList());
    }

    public int searchByLib(String libelle) throws SQLException {
        Produit prod = new Produit();
        String req = "SELECT * FROM produit where libelle=?";
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
            String sql = "SELECT * FROM produit where libelle= ? ";
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

    public long SearchVelo() {
        List<Produit> prod = afficherProduit();
        return prod.stream().filter(b -> b.getType().equalsIgnoreCase("Velo")).count();

    }

    public long SearchPDR() {

        List<Produit> prod = afficherProduit();
        return prod.stream().filter(b -> b.getType().equalsIgnoreCase("Piece de Rechange")).count();

    }

    public long SearchAcc() {

        List<Produit> prod = afficherProduit();
        return prod.stream().filter(b -> b.getType().equalsIgnoreCase("Accesssoire")).count();

    }

    public Produit GetProdbyid(int b) throws SQLException {

        //-------------------- Update ----------//
        Produit pr = new Produit();

        String query = "select * from produit where id = ? ";
        PreparedStatement ps;
        try {
            ps = MaConnexion.getInstance().getCnx().prepareCall(query);
            ps.setInt(1, b);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {
                pr.setId(rest.getInt("id"));
                pr.setLibelle(rest.getString("libelle"));
                pr.setPrix(rest.getFloat("prix"));
                pr.setImage(rest.getString("image"));
                pr.setDescription(rest.getString("description"));
                pr.setType(rest.getString("type"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;

    }

    public void showActual() {
        PreparedStatement ste;
        ResultSet result;
        String sql = "select count (*) from produit  ";
        int i = 0;
        Produit pr = new Produit();
        try {
            ste = cnx.prepareStatement(sql);
            result = ste.executeQuery();
            if (result.next()) {
                i = result.getInt(1);
            }
        } catch (SQLException e) {

        }

        String sql2 = "select * from produit  ";
        try {
            ste = cnx.prepareStatement(sql2);
            result = ste.executeQuery();
            if (result.next()) {
                pr.setId(result.getInt("id"));
                pr.setLibelle(result.getString("libelle"));
                pr.setPrix(result.getFloat("prix"));
                pr.setImage(result.getString("image"));
                pr.setDescription(result.getString("description"));
                pr.setType(result.getString("type"));
            }

        } catch (SQLException ex) {
        }

    }

}
