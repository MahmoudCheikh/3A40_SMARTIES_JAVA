/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Achat;
import com.smarties.tools.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AchatService {

    Connection cnx;

    public AchatService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterAchat(Achat c) {
        String query = "insert into achat(id_user_id,id_produit_id,date,nom_client,numero_client) values(?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, (int) c.getIdUser());
            ste.setInt(2, (int) c.getIdProduit());
            ste.setDate(3, Date.valueOf(c.getDate()));
            ste.setString(4, c.getNomClient());
            ste.setInt(5, (int) c.getNumeroClient());

            ste.executeUpdate();
            System.out.println("Achat Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Achat> afficherAchat() {
        List<Achat> Achats = new ArrayList<>();
        String sql = "select * from achat";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Achat p = new Achat();
                p.setId(rs.getInt("id"));
                p.setIdUser(rs.getInt("id_user_id"));
                p.setIdProduit(rs.getInt("id_produit_id"));               
               
               // Date date = rs.getDate("date");
                //p.setDate(date.toLocalDate());
                
                p.setNomClient(rs.getString("nom_client"));
                p.setNumeroClient(rs.getInt("numero_client"));

                
                Achats.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Achats;

    }

    public void modifierAchat(Achat c) {
        try {
            String req = "UPDATE achat SET nom_client=?, id_user_id=? , date=? ,numero_client=? ,id_produit_id=? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, c.getNomClient());
            ps.setInt(2,  c.getIdUser());
            ps.setDate(3, Date.valueOf(c.getDate()));
            ps.setInt(4,  c.getNumeroClient());
            ps.setInt(5,  c.getIdProduit());





            ps.setInt(6, c.getId());
            
             System.out.println(c);
            System.out.println(ps);
            
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerAchat(int id) {
        try {
            String req = "DELETE FROM achat WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }
 
            public List<Achat> trierachatid() {
        List<Achat> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM achat order by id desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
              
                Achat ab = new Achat();
                  ab.setId(rs.getInt("id"));
                ab.setIdUser(rs.getInt("id_user_id"));
                ab.setIdProduit(rs.getInt("id_produit_id"));               
               
               // Date date = rs.getDate("date");
                //p.setDate(date.toLocalDate());
                
                ab.setNomClient(rs.getString("nom_client"));
                ab.setNumeroClient(rs.getInt("numero_client"));

                
                list.add(ab);
            }
            
        }
        catch(SQLException e){
            
        }
        return list ;
    
              
    


}


}