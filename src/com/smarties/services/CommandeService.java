/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Commande;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CommandeService {

    Connection cnx;

    public CommandeService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterCommande(Commande c) {
        String query = "insert into commande(id,id_user_id,id_produit_id,nb_produits) values(?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1,(int) c.getId());
            ste.setInt(2, (int) c.getIdUser());
            ste.setInt(3,(int) c.getIdProduit());
            ste.setInt(4,(int) c.getNbProduits());
       
            ste.executeUpdate();
            System.out.println("Commande Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Commande> afficherCommande() {
        List<Commande> Commandes = new ArrayList<>();
        String sql = "select * from commande";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Commande p = new Commande();
                p.setId(rs.getInt("id"));
                System.out.println(p.getId());
                p.setNbProduits(rs.getInt(4));
                p.setNbProduits(rs.getInt("nb_produits"));
                Commandes.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Commandes;

    }

    public void modifierCommande(Commande c) {
        try {
            String req = "UPDATE commande SET nb_produits= ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, (int) c.getNbProduits());
         

            ps.setInt(1, c.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerCommande(int id) {
        try {
            String req = "DELETE FROM commande WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

}
