/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Participation;
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
 * @author Lenovo
 */
public class ParticipationService {
    Connection cnx;

    public ParticipationService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterP(Participation p) {
        String query = "insert into participation(id_user_id,id_event_id) values(?,?)";
        try {
          
            PreparedStatement ste = cnx.prepareStatement(query);
           
            ste.setInt(1, (int) p.getId_user());
            ste.setInt(2, (int) p.getId_event());

            ste.executeUpdate();
            System.out.println("Participation Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Participation> afficherP() {
        List<Participation> participations = new ArrayList<>();
        String sql = "select * from participation";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Participation p = new Participation();
                p.setId(rs.getInt("id"));               
                p.setId_user(rs.getInt("id_user_id"));
                p.setId_event(rs.getInt("id_event_id"));
                participations.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return participations;

    }

    public void modifierP(Participation e) {

        try {
            String req = "UPDATE participation SET id_user_id= ?, id_event_id = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

        
            ps.setInt(1, (int) e.getId_user());
            ps.setInt(2, (int) e.getId_event());

            ps.setInt(3, e.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException ex) {

        }
    }

    public void supprimerP(int id) {
        try {
            String req = "DELETE FROM participation WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
     public List<Participation> Chercher(int id) {
       List<Participation> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM participation where id_event_id=?";
             PreparedStatement ps = cnx.prepareStatement(req);
             System.out.println("RECHERCHE...");
             ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            System.out.println(id);
            while(rs.next()){
                Participation e = new Participation();
                e.setId(rs.getInt(1));
                e.setId_user(rs.getInt(2));
                e.setId_event(rs.getInt(3));
                
                list.add(e);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
    }
}
