/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Evenement;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class EvenementService {

   

    Connection cnx;

    public EvenementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterEvenement(Evenement e) {
        String query = "insert into evenement(nom,date_d,date_f,lieu,type,nb_participants,nb_places) values(?,?,?,?,?,?,?)";
        try {
          
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, e.getNom());
            ste.setDate(2, Date.valueOf(e.getDate_d()));
            ste.setDate(3, Date.valueOf(e.getDate_f()));
            ste.setString(4, e.getLieu());
            ste.setString(5, e.getType());
            ste.setInt(6, (int) e.getNb_participants());
            ste.setInt(7, (int) e.getNb_places());

            ste.executeUpdate();
            System.out.println("Evenement Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Evenement> afficherEvenement() {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "select * from evenement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                 Date date_d = rs.getDate("date_d");
                e.setDate_d(date_d.toLocalDate());
               Date date_f = rs.getDate("date_f");
                e.setDate_f(date_f.toLocalDate());             
                e.setLieu(rs.getString("lieu"));
                e.setType(rs.getString("type"));
                e.setNb_participants(rs.getInt("nb_participants"));
                e.setNb_places(rs.getInt("nb_places"));
                evenements.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return evenements;

    }

    public void modifierEvenement(Evenement e) {

        try {
            String req = "UPDATE evenement SET nom= ?, date_d = ?, date_f = ?, lieu = ?, type = ?, nb_participants = ?, nb_places = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, e.getNom());
            ps.setDate(2, Date.valueOf(e.getDate_d()));
            ps.setDate(3, Date.valueOf(e.getDate_f()));
            ps.setString(4, e.getLieu());
            ps.setString(5, e.getType());
            ps.setInt(6, (int) e.getNb_participants());
            ps.setInt(7, (int) e.getNb_places());

            ps.setInt(8, e.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException ex) {

        }
    }

    public void supprimerEvenement(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
    public List<Evenement> Chercher(String titreN) {
       List<Evenement> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM evenement where nom=?";
             PreparedStatement ps = cnx.prepareStatement(req);
             System.out.println("RECHERCHE...");
             ps.setString(1,titreN);
            ResultSet rs = ps.executeQuery();
            System.out.println(titreN);
            while(rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));
        
                e.setDate_d(rs.getDate(3).toLocalDate());
                e.setDate_f(rs.getDate(4).toLocalDate());
                e.setLieu(rs.getString(5));
                e.setType(rs.getString(6));
                e.setNb_participants(rs.getInt(7));
                e.setNb_places(rs.getInt(8));
                
                list.add(e);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;   
    }
     public List<Evenement> trier() {
        List<Evenement> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM evenement order by date_d asc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setDate_d(rs.getDate(3).toLocalDate());
                e.setDate_f(rs.getDate(4).toLocalDate()); 
                e.setLieu(rs.getString(5));
                e.setType(rs.getString(6));
                e.setNb_participants(rs.getInt(7));
                e.setNb_places(rs.getInt(8));
                list.add(e);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;
    }
   
      public ArrayList<Integer> getCombo() {
        ArrayList<Integer> options = new ArrayList<>();
        String sql = "select id from evenement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }
      public ArrayList<Integer> getCombo1() {
        ArrayList<Integer> options = new ArrayList<>();
        String sql = "select id from users";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }
       public Evenement Chercher1 (int id) throws SQLException {
  Evenement ess = new Evenement();
    
            String req = "SELECT nb_participants FROM evenement where id=?";
             PreparedStatement ps = cnx.prepareStatement(req);
             System.out.println("RECHERCHE...");
             ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            System.out.println(id);
            while(rs.next()){
               
                ess.setNb_participants(rs.getInt(1)-1);
               
               
            }
      
      return ess ;  
    }
}
