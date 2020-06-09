/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhma.Utils;
import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nizar
 */
public class Mail {
    
    public static void sendMail(String recipient) throws Exception
    {
        System.out.println("Preparing to send your Email from Baskel ");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
         properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
       //  properties.put("mail.smtp.port","587");
          properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
           
        
        String myAccountEmail = "baskelunseen@gmail.com";
        String password ="unseenbaskel";
        
        
        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(myAccountEmail, password); 
               
            }
        });
        
        Message message =prepareMessage(session,myAccountEmail, recipient);
        Transport.send(message);
        System.out.println("Your email has been sent sucessfully from Baskel ");
        
        
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) 
    {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Reclamation envoyee");
            message.setText("L'equipe Baskel s'excuse pour le derangement Votre reclamation ajoutee avec succees. Merci de patienter HAAAAAAAA ");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
