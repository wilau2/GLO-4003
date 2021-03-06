package ca.ulaval.glo4003.b6.housematch.messaging;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ca.ulaval.glo4003.b6.housematch.domain.user.MessageBuilder;
import ca.ulaval.glo4003.b6.housematch.domain.user.User;
import ca.ulaval.glo4003.b6.housematch.messaging.exceptions.MessageBuilderException;

public class MailBuilder implements MessageBuilder {

   private static final String FROM = "housematchconfirmation@gmail.com";

   private static final String HOUSE_MATCH_USERNAME = "housematchconfirmation@gmail.com";

   private static final String PASSWORD = "houseglo4003";

   private static final String HOST = "smtp.gmail.com";

   private String recipient;

   private Properties properties;

   private Message message;

   private String username;

   public MailBuilder() {
      this.properties = new Properties();
   }

   public MessageBuilder withRecipient(User user) {
      this.recipient = user.getContactInformation().getEmail();
      this.username = user.getUsername();
      return this;
   }

   public MessageBuilder withMessage() throws MessageBuilderException {

      properties = getMailProperties();

      Session session = initiateSession();
      try {
         this.message = initiateMessage(recipient, username, session);
      } catch (MessagingException e) {
         throw new MessageBuilderException("Problem when building message for user: " + username, e);
      }
      return this;
   }

   public Message build() {

      return this.message;
   }

   private Session initiateSession() {
      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(HOUSE_MATCH_USERNAME, PASSWORD);
         }
      });
      return session;
   }

   private Message initiateMessage(String recipient, String username, Session session)
         throws AddressException, MessagingException {

      String linkToSend = "<p>To confirm your email: <a href=\"http://localhost:8080/" + username + "/confirmation"
                          + "\">Click here!</a></p>";
      Message message = new MimeMessage(session);

      message.setFrom(new InternetAddress(FROM));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
      message.setSubject("Email confirmation from HouseMatch!");
      message.setContent(linkToSend, "text/html");

      return message;
   }

   private Properties getMailProperties() {

      Properties properties = new Properties();
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", HOST);
      properties.put("mail.smtp.port", "587");

      return properties;
   }

}
