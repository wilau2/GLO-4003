package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface MessageSender {
   
   public void sendMessage(Message message) throws MessageCantBeSentException;

}
