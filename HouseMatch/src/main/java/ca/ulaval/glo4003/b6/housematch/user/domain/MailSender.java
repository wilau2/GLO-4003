package ca.ulaval.glo4003.b6.housematch.user.domain;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
   
   private String recipient;
   private Properties properties; 
   private Session session;
   private Message message;
   
   private final String from = "housematchconfirmation@gmail.com";  
   private final String username = "housematchconfirmation@gmail.com";
   private final String password = "houseglo4003";
   private final String host = "smtp.gmail.com";
   
   public MailSender(){
      this.properties = new Properties();
   }
   
   public void sendMail(String recipient) throws AddressException, MessagingException{
   
      this.recipient  = recipient;
      
      fillProperties();      
      session = initiateSession();
      message = initiateMessage(recipient);
      Transport.send(message);
      
      
   }

   private Message initiateMessage(String recipient) throws AddressException, MessagingException {
      Message message = new MimeMessage(session);

      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO,
      InternetAddress.parse(recipient));
      message.setSubject("Email confirmation from SellEstates!");
      message.setContent("<a>Click this link to confirm your email!</a>", "text/html");
      
      return message;
   }

   private Session initiateSession() {
      Session session = Session.getInstance(properties,
            new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(username, password);
               }
            });
      return session;
   }

   private void fillProperties() {
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "587");    
   }

}
