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
import com.smarties.entities.Commande;
import com.smarties.entities.Produit;
import com.smarties.test.Smarties;
import com.smarties.tools.MaConnexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CommandeService {

    Connection cnx;
    ProduitService produitService = new ProduitService();

    public CommandeService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
//JDBC Native // ORM // Hibernet
    public void ajouterCommande(Commande c) {
        String query = "insert into commande(id_user_id,id_produit_id,nb_produits) values(?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, (int) c.getIdUser());
            ste.setInt(2, (int) c.getIdProduit());
            ste.setInt(3, (int) c.getNbProduits());

            ste.executeUpdate();
            System.out.println("Commande Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Commande> afficherCommande() {
        List<Commande> Commandes = new ArrayList<>();
        String sql = "select * from commande";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Commande p = new Commande();
                p.setId(rs.getInt("id"));
                p.setNbProduits(rs.getInt("nb_produits"));
                p.setIdProduit(rs.getInt("id_produit_id"));
                p.setIdUser(rs.getInt("id_user_id"));

                Commandes.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Commandes;

    }

    public void modifierCommande(Commande c) {
        try {
            String req = "UPDATE commande SET nb_produits=? ,id_produit_id=?, id_user_id=? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, c.getNbProduits());
            ps.setInt(2, (int) c.getIdProduit());
            ps.setInt(3, (int) c.getIdUser());

            ps.setInt(4, c.getId());

            System.out.println(c);
            System.out.println(ps);

            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerCommande(int id) {
        try {
            String req = "DELETE FROM commande WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

    public List<Commande> Rechercher(String titreN) {
        List<Commande> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM commande where id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE...");
            ps.setString(1, titreN);
            ResultSet rs = ps.executeQuery();

            System.out.println(titreN);
            while (rs.next()) {
                Commande c = new Commande();
                c.setId(rs.getInt(1));
                c.setIdUser(rs.getInt(2));
                c.setIdProduit(rs.getInt(3));
                c.setNbProduits(rs.getInt(4));

                list.add(c);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public List<Commande> triercommande() {
        List<Commande> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM commande order by id desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Commande ab = new Commande();
                ab.setId(rs.getInt(1));
                ab.setIdUser(rs.getInt(2));
                ab.setIdProduit(rs.getInt(3));
                ab.setNbProduits(rs.getInt(4));

                list.add(ab);
            }

        } catch (SQLException e) {

        }
        return list;
    }

    public void Gpdf() throws DocumentException {
        Document doc = new Document();
        String sql = "select * from commande";

        try {
            Statement prepared = cnx.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery(sql);
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Commande.pdf"));
            doc.open();
            doc.getHtmlStyleClass();

            Image img = Image.getInstance("C:\\java\\3A40_SMARTIES_JAVA\\src\\com\\smarties\\images\\çaRoule.png");
            img.scaleAbsoluteWidth(300);
            img.scaleAbsoluteHeight(92);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("                                                     Liste des Commande "));
            doc.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            PdfPCell cell;

            /////////////////////////////////////////////////////////////////
            cell = new PdfPCell(new Phrase("Numero Du Commande", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ////
            cell = new PdfPCell(new Phrase("Produit Achetee", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Quantite Du Produit", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);

            ///
            //////////////////////////////////////////////////////////////////////////////
            while (rs.next()) {
                if (rs.getInt("id_user_id") == Smarties.user.getId()) {

                    Produit p = produitService.GetProdbyid(rs.getInt("id_produit_id"));
                    
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
                    ///////
                    cell = new PdfPCell(new Phrase(rs.getString("nb_produits").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GRAY);
                    table.addCell(cell);
                    ////////////

                }
            }

            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File("C:\\Commande.pdf"));

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

    public ArrayList<String> comboComm() {
        ArrayList<String> options = new ArrayList<>();
        String sql = "select * from users";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

    public ArrayList<String> comboCommProd() {
        ArrayList<String> options = new ArrayList<>();
        String sql = "select * from produit";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
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
            message.setSubject("Ca Roule Commande ");
            message.setText("Bonjour Cher Client , \n Votre Commande A été Passe Avec Succes ! ");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //////////////////////////////////////
     public void mailing(String recipient) throws Exception {
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

        Message message = prepareMessag(session, MyAccountEmail, recipient);
        Transport.send(message);
        System.out.println("message sent successfully");

    }

    private Message prepareMessag(Session session, String MyAccountEmail, String recipient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Ca Roule Commande ");
            message.setText("Bonjour Cher Client , \n Votre Commande A été Annulée ! ");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
