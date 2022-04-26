/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Maintenance;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 */
public class MaintenanceService {

    Connection cnx;

    public MaintenanceService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterMaintenance(Maintenance m) {
        String query = "insert into maintenance(id,id_produit_id,relation_id,reclamation_id,date_debut,date_fin,adresse,etat) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            
            ste.setInt(1, m.getId());
            ste.setInt(2, m.getId_produit_id());
            ste.setInt(3, m.getRelation_id());
            ste.setInt(4, m.getReclamation_id());
            ste.setDate(5, Date.valueOf(m.getDate_debut()));
            ste.setDate(6, Date.valueOf(m.getDate_fin()));
            ste.setString(7, m.getAdresse());
            ste.setString(8, m.getDescription());
            ste.setString(9, m.getEtat());


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
                m.setId_produit_id(rs.getInt("Id_produit_id"));
                System.out.println(m.getId_produit_id());
                m.setRelation_id(rs.getInt("Relation_id"));
                System.out.println(m.getRelation_id());
                m.setReclamation_id(rs.getInt("Reclamation_id"));
                System.out.println(m.getId());
                m.setEtat(rs.getString("Etat"));
                m.setAdresse(rs.getString("Adresse"));
                m.setDescription(rs.getString("Description"));
                Date date_debut = rs.getDate("date_debut");
                m.setDate_debut(date_debut.toLocalDate());
                Date date_fin = rs.getDate("date_fin");
                m.setDate_fin(date_fin.toLocalDate());
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

    public List<Maintenance> Chercher(String titreN) {
        List<Maintenance> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM maintenance where id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE...");
            ps.setString(1, titreN);
            ResultSet rs = ps.executeQuery();

            System.out.println(titreN);
            while (rs.next()) {
                Maintenance e = new Maintenance();
                e.setId(rs.getInt(1));
                e.setId_produit_id(rs.getInt(2));
                e.setRelation_id(rs.getInt(3));
                e.setReclamation_id(rs.getInt(4));
                Date date_debut = rs.getDate("date_debut");
                e.setDate_debut(date_debut.toLocalDate());
                Date date_fin = rs.getDate("date_fin");
                e.setDate_fin(date_fin.toLocalDate());
                e.setAdresse(rs.getString(7));
                e.setEtat(rs.getString(8));
                e.setDescription(rs.getString(8));

                list.add(e);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public List<Maintenance> TriEtat() {
        Comparator<Maintenance> comparator = Comparator.comparing(Maintenance::getEtat);
        List<Maintenance> prd = afficherMaintenance();
        return prd.stream().sorted(comparator).collect(Collectors.toList());
    }

}
