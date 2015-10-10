package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class MailSender implements MessageSender{

   public void sendMessage(Message message) throws MessageCantBeSentException{
      try {
         Transport.send(message);
      } catch (MessagingException e) {
        throw new MessageCantBeSentException("Email could not be sent!",e);
      }
      
   }
  
}
