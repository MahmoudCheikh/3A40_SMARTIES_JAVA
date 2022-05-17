/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import com.smarties.entities.Abonnement;
import com.smarties.entities.Location;
import com.smarties.tools.MaConnexion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.net.PasswordAuthentication;
import javax.mail.Authenticator;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class AbonnementService {

    private String text = "<body style=\"background-color:black\">\n"
            + "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
            + "           width=\"550\" bgcolor=\"white\" style=\"border:2px solid black\">\n"
            + "        <tbody>\n"
            + "            <tr>\n"
            + "                <td align=\"center\">\n"
            + "                    <table align=\"center\" border=\"0\" cellpadding=\"0\" \n"
            + "                           cellspacing=\"0\" class=\"col-550\" width=\"550\">\n"
            + "                        <tbody>\n"
            + "                            <tr>\n"
            + "                                <td align=\"center\" style=\"background-color:#ff7f50;\n"
            + "                                           height: 50px;\">\n"
            + "  \n"
            + "                                    <a href=\"#\" style=\"text-decoration: none;\">\n"
            + "                                        <p style=\"color:white;\n"
            + "                                                  font-weight:bold;\">\n"
            + "                                            Ca Roule\n"
            + "                                        </p>\n"
            + "                                    </a>\n"
            + "                                </td>\n"
            + "                            </tr>\n"
            + "                        </tbody>\n"
            + "                    </table>\n"
            + "                </td>\n"
            + "            </tr>\n"
            + "            <tr style=\"height: 300px;\">\n"
            + "                <td align=\"center\" style=\"border: none;\n"
            + "                           border-bottom: 2px solid #4cb96b; \n"
            + "                           padding-right: 20px;padding-left:20px\">\n"
            + "  \n"
            + "                    <p style=\"font-weight: bolder;font-size: 42px;\n"
            + "                              letter-spacing: 0.025em;\n"
            + "                              color:black;\">\n"
            + "                        Bonjour !\n"
            + "                        <br> Ca Roule  Abonnements ! \n"
            + "                    </p>\n"
            + "                </td>\n"
            + "            </tr>\n"
            + "  \n"
            + "            <tr style=\"display: inline-block;\">\n"
            + "                <td style=\"height: 150px;\n"
            + "                           padding: 20px;\n"
            + "                           border: none; \n"
            + "                           border-bottom: 2px solid #361B0E;\n"
            + "                           background-color: white;\">\n"
            + "                    \n"
            + "                    <h2 class=\"data\" \n"
            + "                       style=\"text-align: justify-all;\n"
            + "                              align-items: center; \n"
            + "                              font-size: 15px;\n"
            + "                              padding-bottom: 12px;\">\n"
            + "                      Nous voulons vous notifier qu'un nouvel Abonnement a été ajouté a l'application ça Roule  \n"
            + "                    </h2>\n"
            + "                </td>\n"
            + "            </tr>\n"
            + "        </tbody>\n"
            + "    </table>\n"
            + "</body>";

    Connection cnx;

    public AbonnementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public void ajouterAbonnement(Abonnement a) {

        String query = "insert into abonnement(id_user_id,type,dateD,dateF,prix) values(?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            // ste.setInt(1, (int) a.getId());
            ste.setInt(1, (int) a.getId_user_id());
            ste.setString(2, a.getType());
            // ste.setDate(2, Date.valueOf(e.getDate_d()));
            ste.setDate(3, Date.valueOf(a.getDateD()));
            ste.setDate(4, Date.valueOf(a.getDateF()));
            ste.setInt(5, (int) a.getPrix());
            ste.executeUpdate();
            System.out.println("Abonnement Ajouté !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Abonnement> afficherAbonnement() {

        List<Abonnement> abonnements = new ArrayList<>();
        String sql = "select * from abonnement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Abonnement a = new Abonnement();
                a.setId(rs.getInt("id"));
                a.setId_user_id(rs.getInt("id_user_id"));
                a.setType(rs.getString("type"));
                Date dateD = rs.getDate("dated");
                a.setDateD(dateD.toLocalDate());
                Date dateF = rs.getDate("datef");
                a.setDateF(dateF.toLocalDate());

                a.setPrix(rs.getInt("prix"));
                abonnements.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return abonnements;

    }

    public void modifierAbonnement(Abonnement a) {
        try {
            String req = "UPDATE abonnement SET type= ?, dateD = ?,dateF = ?,prix = ? WHERE id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a.getType());
            ps.setDate(2, Date.valueOf(a.getDateD()));
            ps.setDate(3, Date.valueOf(a.getDateF()));

            ps.setInt(4, (int) a.getPrix());
            ps.setInt(5, (int) a.getId());
            System.out.println("Modification...");
            ps.executeUpdate();

            System.out.println("Une ligne modifiée dans la table...");
        } catch (SQLException e) {

        }

    }

    public void supprimerAbonnement(int id) {
        try {
            String req = "DELETE FROM abonnement WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Une ligne supprimée dans la table...");
        } catch (SQLException e) {

        }

    }

    public List<Abonnement> ChercherType(String titreN) {
        List<Abonnement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM abonnement where type=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE en cours ..");
            ps.setString(1, titreN);
            ResultSet rs = ps.executeQuery();

            System.out.println(titreN);
            while (rs.next()) {
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));

                list.add(ab);
            }

        } catch (SQLException e) {

        }
        return list;

    }

    public List<Abonnement> ChercherPrix(float price) {

        List<Abonnement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM abonnement where prix=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("RECHERCHE en cours ..");
            ps.setFloat(1, price);
            ResultSet rs = ps.executeQuery();

            System.out.println(price);
            while (rs.next()) {
                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));

                list.add(ab);
            }

        } catch (SQLException e) {

        }
        return list;

    }

    public List<Abonnement> trierPrix() {
        List<Abonnement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM abonnement order by prix desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));

                list.add(ab);
            }

        } catch (SQLException e) {

        }
        return list;

    }

    public List<Abonnement> trierIDUSER() {
        List<Abonnement> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM abonnement order by id_user_id desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Abonnement ab = new Abonnement();
                ab.setId(rs.getInt(1));
                ab.setType(rs.getString(3));
                ab.setId_user_id(rs.getInt(2));
                ab.setPrix(rs.getInt(6));

                list.add(ab);
            }

        } catch (SQLException e) {

        }
        return list;

    }

    public void generateExcel() {
        String sql = "select * from abonnement";
        Statement ste;
        try {

            ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("abonnement details ");
            HSSFRow header = sheet.createRow(0);
           

           

            //cell.setCellStyle(style);
           
            header.createCell(0).setCellValue("Type");
            header.createCell(1).setCellValue("Date debut");
            header.createCell(2).setCellValue("Date fin");
            header.createCell(3).setCellValue("ID User");
            header.createCell(4).setCellValue("Prix");
            int index = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(index);
                
                
                
                row.createCell(0).setCellValue(rs.getString("type"));
                row.createCell(1).setCellValue(rs.getString("dated"));
                row.createCell(2).setCellValue(rs.getString("datef"));
                row.createCell(3).setCellValue(rs.getString("id_user_id"));
                row.createCell(4).setCellValue(rs.getString("prix"));
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\abonnementDetails.ods");
            wb.write(fileOut);
            fileOut.close();
            ste.close();
            rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            message.setSubject("Notification via votre application desktop");
            message.setContent(text, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public long Search1() {

        List<Abonnement> Abon = afficherAbonnement();
        return Abon.stream().filter(b -> b.getType().equalsIgnoreCase("VIP")).count();

    }

    public long Search2() {

        List<Abonnement> Abon = afficherAbonnement();
        return Abon.stream().filter(b -> b.getType().equalsIgnoreCase("Silver")).count();

    }

    public long Search3() {

        List<Abonnement> Abon = afficherAbonnement();
        return Abon.stream().filter(b -> b.getType().equalsIgnoreCase("Gold")).count();

    }

    public long Recherche1() {

        List<Abonnement> abon = afficherAbonnement();
        return abon.stream().filter(b -> b.getPrix() >= 50).filter(b -> b.getPrix() <= 100).count();

    }

    public long Recherche2() {

        List<Abonnement> abon = afficherAbonnement();
        return abon.stream().filter(b -> b.getPrix() >= 100).count();

    }

    public long Recherche3() {

        List<Abonnement> abon = afficherAbonnement();
        return abon.stream().filter(b -> b.getPrix() <= 50).count();

    }

    public int searchByID(String iid) throws SQLException {
        Location loc = new Location();
        String req = "SELECT * FROM abonnement  where id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        System.out.println("RECHERCHE...");
        ps.setString(1, iid);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public ArrayList<String> getCombo1() {
        ArrayList<String> options = new ArrayList<>();
        String sql = "select * from users";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                options.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return options;
    }

    public void playHitSound(String fileName) {
        MediaPlayer mediaPlayer;
        String path = getClass().getResource(fileName).getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public List<Abonnement> getidabonnement() {

        List<Abonnement> abonnements = new ArrayList<>();
        String sql = "select id from abonnement";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Abonnement a = new Abonnement();
                a.setId(rs.getInt("id"));
                a.setId_user_id(rs.getInt("id_user_id"));
                a.setType(rs.getString("type"));
                Date dateD = rs.getDate("dated");
                a.setDateD(dateD.toLocalDate());
                Date dateF = rs.getDate("datef");
                a.setDateF(dateF.toLocalDate());

                a.setPrix(rs.getInt("prix"));
                abonnements.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return abonnements;

    }
}

/*




    MediaPlayer mediaPlayer;

    @FXML
    void play(MouseEvent event) {
        String fileName = "SurvivetheMontageRKVC.mp3";
        playHitSound(fileName);
    }


    private void playHitSound(String fileName){
        String path = getClass().getResource(fileName).getPath();
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

}*/
