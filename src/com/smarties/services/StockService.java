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
import com.smarties.entities.Produit;
import com.smarties.entities.Stock;
import com.smarties.entities.Users;
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
 * @author PC
 */
public class StockService {

    Connection cnx;
    ProduitService pr = new ProduitService();
    public StockService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
// mail template
    private String text="<body style=\"background-color:black\">\n" +
"    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
"           width=\"550\" bgcolor=\"white\" style=\"border:2px solid black\">\n" +
"        <tbody>\n" +
"            <tr>\n" +
"                <td align=\"center\">\n" +
"                    <table align=\"center\" border=\"0\" cellpadding=\"0\" \n" +
"                           cellspacing=\"0\" class=\"col-550\" width=\"550\">\n" +
"                        <tbody>\n" +
"                            <tr>\n" +
"                                <td align=\"center\" style=\"background-color:#ff7f50;\n" +
"                                           height: 50px;\">\n" +
"  \n" +
"                                    <a href=\"#\" style=\"text-decoration: none;\">\n" +
"                                        <p style=\"color:white;\n" +
"                                                  font-weight:bold;\">\n" +
"                                            Ca Roule\n" +
"                                        </p>\n" +
"                                    </a>\n" +
"                                </td>\n" +
"                            </tr>\n" +
"                        </tbody>\n" +
"                    </table>\n" +
"                </td>\n" +
"            </tr>\n" +      
"            <tr style=\"height: 300px;\">\n" +
"                <td align=\"center\" style=\"border: none;\n" +
"                           border-bottom: 2px solid #4cb96b; \n" +
"                           padding-right: 20px;padding-left:20px\">\n" +
"  \n" +
"                    <p style=\"font-weight: bolder;font-size: 42px;\n" +
"                              letter-spacing: 0.025em;\n" +
"                              color:black;\">\n" +
"                        Hello !\n" +
"                        <br> Ça Roule application Desktop ! \n" +
"                    </p>\n" +
"                </td>\n" +
"            </tr>\n" +
"  \n" +
"            <tr style=\"display: inline-block;\">\n" +
"                <td style=\"height: 150px;\n" +
"                           padding: 20px;\n" +
"                           border: none; \n" +
"                           border-bottom: 2px solid #361B0E;\n" +
"                           background-color: white;\">\n" +
"                    \n" +
"                    <h2 class=\"data\" \n" +
"                       style=\"text-align: justify-all;\n" +
"                              align-items: center; \n" +
"                              font-size: 15px;\n" +
"                              padding-bottom: 12px;\">\n" +
"                      Hey Admine , \n Stock Limité Svp Contacter Votre Fournisseur ! pour plus de details sur l'état du stock consulter la section stock dans l'application \n" +
"                    </h2>\n" +                  
"                </td>\n" +
"            </tr>\n" +
"        </tbody>\n" +
"    </table>\n" +
"</body>";
// end mail template
    public void ajouterStock(Stock s) {
        String query = "insert into stock(libelle,id_produit_id,prix,quantite,disponibilite) values(?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, s.getLibelle());
            ste.setInt(2, s.getIdProduit());
            ste.setFloat(3, s.getPrix());
            ste.setInt(4, s.getQuantite());
            ste.setString(5, s.getDisponibilite());

            ste.executeUpdate();
            System.out.println("Stock Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Stock> afficherStock() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "select * from stock";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Stock s = new Stock();
                s.setId(rs.getInt("id"));
                s.setLibelle(rs.getString("libelle"));
                s.setDisponibilite(rs.getString("disponibilite"));
                s.setPrix(rs.getInt("prix"));
                s.setQuantite(rs.getInt("quantite"));
                s.setIdProduit(rs.getInt("id_produit_id"));
                stocks.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return stocks;

    }

    public void modifierStock(Stock s) {
        try {
            String req = "UPDATE stock SET libelle = ?,id_produit_id = ?,prix = ?,quantite = ?,disponibilite = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, s.getLibelle());
            ps.setInt(2, s.getIdProduit());
            ps.setInt(3, s.getPrix());
            ps.setInt(4, s.getQuantite());
            ps.setString(5, s.getDisponibilite());

            ps.setInt(6, s.getId());

            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerStock(int id) {
        try {
            String req = "DELETE FROM stock WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
        } catch (SQLException e) {

        }

    }

    public List<Stock> TriQuantite() {
        Comparator<Stock> comparator = Comparator.comparing(Stock::getQuantite);
        List<Stock> sto = afficherStock();
        return sto.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Stock> TriDisponibilite() {
        Comparator<Stock> comparator = Comparator.comparing(Stock::getDisponibilite);
        List<Stock> sto = afficherStock();
        return sto.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<Stock> RechercheLibelle(String libelle) {

        return afficherStock().stream().filter(a -> a.getLibelle().equals(libelle)).collect(Collectors.toList());
    }

    public long Recherche1() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() > 20).filter(b -> b.getQuantite() < 50).count();

    }

    public long Recherche2() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() > 50).filter(b -> b.getQuantite() < 70).count();

    }

    public long Recherche3() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() < 20).count();

    }

    public long Recherche4() {

        List<Stock> Stock = afficherStock();
        return Stock.stream().filter(b -> b.getQuantite() > 70).filter(b -> b.getQuantite() < 100).count();
    }

    public ArrayList<String> getCombo() {
        ArrayList<String> options = new ArrayList<>();
        String sql = "select * from produit";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

    public int searchByLibS(String libelle) throws SQLException {
        Stock sto = new Stock();
        String req = "SELECT * FROM stock where id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        System.out.println("RECHERCHE...");
        ps.setString(1, libelle);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public boolean getIgnoreRepetetion(String libelle) {
        boolean exist = false;

        try {
            String sql = "SELECT * FROM stock where libelle= ? ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, libelle);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exist;

    }

    public Stock GetStockbyid(int b) throws SQLException {

        //-------------------- Update ----------//
        Stock s = new Stock();

        String query = "select * from stock where id = ? ";
        PreparedStatement ps;
        try {
            ps = MaConnexion.getInstance().getCnx().prepareCall(query);
            ps.setInt(1, b);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {

                s.setId(rest.getInt("id"));
                s.setLibelle(rest.getString("libelle"));
                s.setDisponibilite(rest.getString("disponibilite"));
                s.setPrix(rest.getInt("prix"));
                s.setQuantite(rest.getInt("quantite"));
                s.setIdProduit(rest.getInt("id_produit_id"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;

    }
      public void sendMail(String recipient) throws Exception {
		  recipient = "mahmoud.cheikh@esprit.tn";
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
            message.setSubject("Ca Roule stock epuisé ");
          //  message.setText("Hey Admine , \n Stock Limite Svp Contacter Votre Fournisseur ! ");
            message.setContent(text,"text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       public void GeneratePDFStock() throws DocumentException {
        Document doc = new Document();
        String sql = "select* from stock";

        try {
            Statement prepared = cnx.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery(sql);
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Stock.pdf"));
            doc.open();
            doc.getHtmlStyleClass();

            Image img = Image.getInstance("C:\\çaRoule.png");
            img.scaleAbsoluteWidth(300);
            img.scaleAbsoluteHeight(92);
            img.setAlignment(Image.ALIGN_CENTER);
            doc.add(img);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("                                                     Liste des Stock "));
            doc.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            PdfPCell cell;

            /////////////////////////////////////////////////////////////////
           /* cell = new PdfPCell(new Phrase("ID Location", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);*/
            ////
            cell = new PdfPCell(new Phrase("Nom du Produit Stocké", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Nom Du Stock", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Prix du Stock", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
            cell = new PdfPCell(new Phrase("Disponibilté du Stock", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);
            ///
           /* cell = new PdfPCell(new Phrase("ID Abonnements", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(cell);*/
            //////////////////////////////////////////////////////////////////////////////
            while (rs.next()) {
              
                /*cell = new PdfPCell(new Phrase(rs.getString("id_produit_id").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);*/
                ///////
                cell = new PdfPCell(new Phrase(rs.getString("libelle").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                ///////
                cell = new PdfPCell(new Phrase(rs.getString("prix").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                ////////////
                cell = new PdfPCell(new Phrase(rs.getString("disponibilite").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
                ///////
              /*  cell = new PdfPCell(new Phrase(rs.getString("id_abonnement_id").toString(), FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(cell);*/

            }

            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File("C:\\Stock.pdf"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LocationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
