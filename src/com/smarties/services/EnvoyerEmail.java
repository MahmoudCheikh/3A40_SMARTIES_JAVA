/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarties.services;

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
InternetAddress.parse("mohamedaziz.jaziri1@esprit.tn"));
message.setSubject("Ca Roule EVENT ");
message.setText("un nouvel Evenement est ajouté a l'application ça Roule GO CHECK IT OUT ! pour plus de details sur l'evenement " +nom+ " consulter notre application  ");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} 
}   
}
