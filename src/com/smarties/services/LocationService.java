/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.smarties.entities.Location;
import com.smarties.tools.MaConnexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.C;

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
                l.setIdUser(rs.getInt("id_user_id"));
                l.setIdAbonnement(rs.getInt("id_abonnement_id"));
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

    public List<Location> ChercherDuree(float Duree) {

        List<Location> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM location where duree=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE en cours ..");
            ps.setFloat(1, Duree);
            ResultSet rs = ps.executeQuery();

            System.out.println(Duree);
            while (rs.next()) {
                Location loc = new Location();
                loc.setId(rs.getInt(1));
                loc.setIdUser(rs.getInt(2));
                loc.setHeure(rs.getString(5));

                loc.setDuree(rs.getFloat(6));

                loc.setIdAbonnement(rs.getInt(3));

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
                loc.setHeure(rs.getString(5));

                loc.setDuree(rs.getInt(6));

                loc.setIdAbonnement(rs.getInt(3));

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
                loc.setHeure(rs.getString(5));

                loc.setDuree(rs.getInt(6));

                loc.setIdAbonnement(rs.getInt(3));

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
                loc.setHeure(rs.getString(5));

                loc.setDuree(rs.getInt(6));

                loc.setIdAbonnement(rs.getInt(3));

                list.add(loc);

            }

        } catch (SQLException e) {

        }
        return list;
    }

    /* public String getCaptcha()
     {
         char data []= {  'a', 'b','c','d','e','f', 'g','h','i','j', 'k', 'l','m','n','o','p', 'q','r','s','t','u', 'v','w','x','y','z','A', 'B','C','D','E','F', 'G','H','I','J', 'K', 'L','M','N','O','P', 'Q','R','S','T','U', 'V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9', '#','!','@','$','%','&','*'};
     
     char  index[]=new char[6];
     Random r=new Random();
     int i =0;
     for(i=0;i<(index.length);i++)
     {
         int ran= r.nextInt(data.length);
         index[i]=data[ran];
     }
     return new String(index);
     } */
    public void GeneratePDF() throws DocumentException {
        Document doc = new Document();
        String sql = "select* from location";

        try {
            Statement prepared = cnx.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery(sql);
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\ASUS\\Desktop\\Location.pdf"));
            doc.open();
            doc.getHtmlStyleClass();

            Image img = Image.getInstance("C:\\Users\\ASUS\\Desktop\\çaRoule.png");
            img.scaleAbsoluteWidth(300);
            img.scaleAbsoluteHeight(92);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("                                                     Liste des locations "));
            doc.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            PdfPCell cell;

            /////////////////////////////////////////////////////////////////
            cell = new PdfPCell(new Phrase("ID Location", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ////
            cell = new PdfPCell(new Phrase("ID User", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Date", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Heure", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Durée", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("ID Abonnements", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            //////////////////////////////////////////////////////////////////////////////
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("id").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                //////
                cell = new PdfPCell(new Phrase(rs.getString("id_user_id").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                ///////
                cell = new PdfPCell(new Phrase(rs.getString("date").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                ///////
                cell = new PdfPCell(new Phrase(rs.getString("heure").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                ////////////
                cell = new PdfPCell(new Phrase(rs.getString("duree").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);
                ///////
                cell = new PdfPCell(new Phrase(rs.getString("id_abonnement_id").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);

            }

            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File("C:\\Users\\ASUS\\Desktop\\Location.pdf"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
