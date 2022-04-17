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

            // ste.setInt(1, (int) l.getId());
            ste.setInt(1, (int) l.getIdUser());
            ste.setInt(2, l.getIdAbonnement());
            ste.setDate(3, Date.valueOf(l.getDate()));
            ste.setString(4, l.getHeure());
            ste.setFloat(5, (int) l.getDuree());

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

                locations.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return locations;

    }

    public void modifierLocation(Location l) {
        try {
            String req = "UPDATE location SET heure= ?, duree = ? ,id_abonnement_id=? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            //ps.setDate(2,l.getDate(date.toLocalDate()));
            ps.setString(1, l.getHeure());
            ps.setFloat(2, l.getDuree());
            ps.setInt(3, l.getIdAbonnement());
            ps.setInt(4, l.getId());

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

    public List<Location> ChercherDuree(int Duree) {

        List<Location> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM location where duree=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE en cours ..");
            ps.setInt(1, Duree);
            ResultSet rs = ps.executeQuery();

            System.out.println(Duree);
            while (rs.next()) {
                Location loc = new Location();
                loc.setId(rs.getInt(1));
                loc.setIdUser(rs.getInt(2));
                loc.setHeure(rs.getString(4));

                loc.setDuree(rs.getInt(5));

                loc.setIdAbonnement(rs.getInt(6));

                list.add(loc);
            }

        } catch (SQLException e) {

        }
        return list;

    }

    public List<Location> ChercherID(int abon) {

        List<Location> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM location where id_abonnement_id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE en cours ..");
            ps.setInt(1, abon);
            ResultSet rs = ps.executeQuery();

            System.out.println(abon);
            while (rs.next()) {
                Location loc = new Location();
                loc.setId(rs.getInt(1));
                loc.setIdUser(rs.getInt(2));
                loc.setHeure(rs.getString(4));

                loc.setDuree(rs.getInt(5));

                loc.setIdAbonnement(rs.getInt(6));

                list.add(loc);
            }

        } catch (SQLException e) {

        }
        return list;

    }

    public List<Location> TrierDuree() {

        List<Location> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM location order by duree desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Location loc = new Location();
                loc.setId(rs.getInt(1));
                loc.setIdUser(rs.getInt(2));
                loc.setHeure(rs.getString(4));

                loc.setDuree(rs.getInt(5));

                loc.setIdAbonnement(rs.getInt(6));

                list.add(loc);

            }

        } catch (SQLException e) {

        }
        return list;
    }
     public List<Location> TrierIdAB() {

        List<Location> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM location order by id_abonnement_id desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Location loc = new Location();
                loc.setId(rs.getInt(1));
                loc.setIdUser(rs.getInt(2));
                loc.setHeure(rs.getString(4));

                loc.setDuree(rs.getInt(5));

                loc.setIdAbonnement(rs.getInt(6));

                list.add(loc);

            }

        } catch (SQLException e) {

        }
        return list;
    }

}
