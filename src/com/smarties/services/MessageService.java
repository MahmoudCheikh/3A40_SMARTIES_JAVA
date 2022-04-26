/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Message;
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
 * @author user
 */
public class MessageService {

    Connection cnx;

    public MessageService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterMessage(Message p) {
        String query = "insert into message(id_user_id,id_sujet_id,date,contenu) values(?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setInt(1, p.getIdUser());
            ste.setInt(2, p.getIdSujet());
            ste.setDate(3, Date.valueOf(p.getDate()));
            ste.setString(4, p.getContenu());

            ste.executeUpdate();
            System.out.println("Message Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Message> afficherMessage() {
        List<Message> sujets = new ArrayList<>();
        String sql = "select * from message";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Message p = new Message();

                p.setId(rs.getInt("id"));
                p.setIdSujet(rs.getInt(3));
                p.setIdUser(rs.getInt(2));
                Date date = rs.getDate("date");
                p.setDate(date.toLocalDate());
                p.setContenu(rs.getString("contenu"));

                sujets.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sujets;

    }

    public void modifierMessage(Message c) {
        try {
            String req = "UPDATE message SET contenu=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getContenu());

            ps.setInt(2, c.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerMessage(int id) {
        try {
            String req = "DELETE FROM message WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

    public ArrayList<Integer> getCombo() {
        ArrayList<Integer> options = new ArrayList<>();
        String sql = "select * from message";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

    public List<Message> searchByUser(int id) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "select * from message where id_user_id=?";
        PreparedStatement pre = cnx.prepareStatement(sql);
        try {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Message p = new Message();

                p.setId(rs.getInt("id"));
                p.setIdUser(rs.getInt(2));
                p.setIdSujet(rs.getInt(3));
                Date date = rs.getDate("date");
                p.setDate(date.toLocalDate());
                p.setContenu(rs.getString("contenu"));

                messages.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return messages;

    }

    public List<Message> searchBySujet(int id) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "select * from message where id_sujet_id=?";
        PreparedStatement pre = cnx.prepareStatement(sql);
        try {
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Message p = new Message();

                p.setId(rs.getInt("id"));
                p.setIdUser(rs.getInt(2));
                p.setIdSujet(rs.getInt(3));
                Date date = rs.getDate("date");
                p.setDate(date.toLocalDate());
                p.setContenu(rs.getString("contenu"));

                messages.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return messages;

    }
}
