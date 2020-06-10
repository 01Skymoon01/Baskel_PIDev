/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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

/**
 *
 * @author Nizar
 */
public class Mail2 {
    
    
      public static void sendMail2(String recipient,String Subject,String body) throws Exception
    {
        System.out.println("Preparing to send your Email from Baskel ");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
         properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
         properties.put("mail.smtp.port","587");
          properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
           
        
        String myAccountEmail = "nizarbenhmida001@gmail.com";
        String password ="gonhunter001";
        
        
        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(myAccountEmail, password); 
               
            }
        });
        
        Message message =prepareMessage2(session,myAccountEmail, recipient,Subject,body);
        Transport.send(message);
        System.out.println("Your email has been sent sucessfully from Baskel ");
        
        
    }
    
    private static Message prepareMessage2(Session session, String myAccountEmail, String recipient,String subject,String body) 
    {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
           // String htmlCode ="<h4>Big News here !</h4><br><h1>Congratulations ! </h1><br><p>Your event has been added to our platform successfully.you can approach us for further information or book a meeting with our project management head to collaborate on the delivery of your event!Visit our website here!</p>";
            message.setText(body);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
