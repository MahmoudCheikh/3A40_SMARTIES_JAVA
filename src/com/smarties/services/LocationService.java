/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Location;
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
 * @author ASUS
 */
public class LocationService {

    Connection cnx;

    public LocationService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterLocation(Location l) {
        String query = "insert into location(id_user_id,id_abonnement_id,date,heure,duree) values(?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setInt(1, l.getIdUser());
            ste.setInt(2, l.getIdAbonnement());
            ste.setDate(3, Date.valueOf(l.getDate()));
            ste.setString(4, l.getHeure());
            ste.setFloat(5, l.getDuree());

            ste.executeUpdate();
            System.out.println("Location Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Location> afficherLocation() {
        List<Location> locations = new ArrayList<>();
        String sql = "select * from location";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Location l = new Location();

                l.setId(rs.getInt("id"));
                l.setIdUser(rs.getInt(1));
                l.setIdAbonnement(rs.getInt(1));
                Date date = rs.getDate("date");
                l.setDate(date.toLocalDate());
                l.setHeure(rs.getString("heure"));
                l.setDuree(rs.getFloat("duree"));
                //l.setNbVues(rs.getInt(7));

                locations.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return locations;

    }

    public void modifierLocation(Location l) {
        try {
            String req = "UPDATE location SET heure= ?, duree = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, l.getHeure());
            ps.setFloat(2, l.getDuree());

            ps.setInt(3, l.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerLocation(int id) {
        try {
            String req = "DELETE FROM location WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
}
