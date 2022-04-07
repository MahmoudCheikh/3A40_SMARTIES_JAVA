/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Users;
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
 * @author user
 */
public class UsersService {

    Connection cnx;

    public UsersService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterUser(Users p) {
        String query = "insert into users(nom,prenom,adresse,email,image,role) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.setString(3, p.getAdresse());
            ste.setString(4, p.getEmail());
            ste.setString(5, p.getImage());
            ste.setString(6, p.getRole());

            ste.executeUpdate();
            System.out.println("Personne Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Users> afficherUser() {
        List<Users> personnes = new ArrayList<>();
        String sql = "select * from users";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Users p = new Users();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString(2));
                p.setAdresse(rs.getString(7));
                p.setImage(rs.getString(8));
                p.setRole(rs.getString(9));

                personnes.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;

    }

    public void modifierUser(Users c) {
        try {
            String req = "UPDATE users SET nom= ?, prenom = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());

            ps.setInt(3, c.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerUser(int id) {
        try {
            String req = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

}
