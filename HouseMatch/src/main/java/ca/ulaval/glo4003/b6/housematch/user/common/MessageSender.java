package ca.ulaval.glo4003.b6.housematch.user.common;

import javax.mail.Message;

public interface MessageSender {

   void sendMessage(Message message) throws MessageCantBeSentException;

}
