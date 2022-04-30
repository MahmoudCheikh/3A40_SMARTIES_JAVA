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
import com.smarties.entities.Maintenance;
import com.smarties.test.Smarties;
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
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

    public void sendMail(String recipient) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String MyAccountEmail = "roulece090@gmail.com";
        String password = "ahmed123456789";
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MyAccountEmail, password);
            }

        });

        Message message = prepareMessage(session, MyAccountEmail, recipient);
        Transport.send(message);
        System.out.println("message sent successfully");

    }

    private Message prepareMessage(Session session, String MyAccountEmail, String recipient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Ca Roule Maintenance ");
            message.setText("Bonjour Cher Client , \nVotre maintenance A été Passe Avec Succes ! ");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /*  
    public void Gpdf() throws DocumentException {
        Document doc = new Document();
        String sql = "select * from commande";

        try {
            Statement prepared = cnx.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery(sql);
            PdfWriter.getInstance(doc, new FileOutputStream("F:\\Users\\\\Administrator\\Documents\\hazem Pdf\\maintenance.pdf"));
            doc.open();
            doc.getHtmlStyleClass();

            Image img = Image.getInstance("F:\\JAVA\\3A40_SMARTIES_JAVA\\src\\com\\smarties\\images\\çaRoule.png");
            img.scaleAbsoluteWidth(300);
            img.scaleAbsoluteHeight(92);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("                                                     Liste des maintenances "));
            doc.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            PdfPCell cell;

            /////////////////////////////////////////////////////////////////
            cell = new PdfPCell(new Phrase("ID maintenance", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ////
            cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Etat", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            //////////////////////////////////////////////////////////////////////////////
            while (rs.next()) {
                if (rs.getInt("id_user_id") == Smarties.user.getId()) {

                    Maintenance m = MaintenanceService.getReclamation_id(m.getInt("id_produit_id"));

                    cell = new PdfPCell(new Phrase(rs.getString("id").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GRAY);
                    table.addCell(cell);
                    //////
                    cell = new PdfPCell(new Phrase(p.getLibelle(), FontFactory.getFont("Comic Sans MS", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GRAY);
                    table.addCell(cell);
                    ///////
                    cell = new PdfPCell(new Phrase(Float.toString(p.getPrix()), FontFactory.getFont("Comic Sans MS", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GRAY);
                    table.addCell(cell);
                    ////////////

                }
            }

            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File("F:\\Users\\\\Administrator\\Documents\\hazem Pdf\\maintenance.pdf"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     */

}
