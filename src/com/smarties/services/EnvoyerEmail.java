/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

import com.smarties.entities.Evenement;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Lenovo
 */
public class EnvoyerEmail { 
   
private String username = "mohamedaziz.jaziri1@esprit.tn";
private String password = "Radiajaziri2000";
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
"                        <br> Ca Roule Event ! \n" +
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
"                      un nouvel Evenement est ajouté a l'application ça Roule GO CHECK IT OUT ! pour plus de details sur l'evenement consulter notre application \n" +
"                    </h2>\n" +                  
"                </td>\n" +
"            </tr>\n" +
"        </tbody>\n" +
"    </table>\n" +
"</body>";

public void envoyer(String nom) {
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","25");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("mohamedaziz.jaziri1@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("mahmoud.cheikh@esprit.tn"));
message.setSubject("Ca Roule EVENT "+nom+"");
message.setContent(text,"text/html");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} 
}   
}
